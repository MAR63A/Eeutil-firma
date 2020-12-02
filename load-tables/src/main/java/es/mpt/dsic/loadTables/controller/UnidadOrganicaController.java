/*
 * Copyright (C) 2012-13 MINHAP, Gobierno de España This program is licensed and may be used,
 * modified and redistributed under the terms of the European Public License (EUPL), either version
 * 1.1 or (at your option) any later version as soon as they are approved by the European
 * Commission. Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * more details. You should have received a copy of the EUPL1.1 license along with this program; if
 * not, you may find it at http://joinup.ec.europa.eu/software/page/eupl/licence-eupl
 */

package es.mpt.dsic.loadTables.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import es.mpt.dsic.loadTables.exception.UnidadOrganicaException;
import es.mpt.dsic.loadTables.handler.HandlerOficina;
import es.mpt.dsic.loadTables.handler.HandlerUnidadOrganica;
import es.mpt.dsic.loadTables.hibernate.service.impl.UnidadOrganicaServiceImpl;
import es.mpt.dsic.loadTables.objects.Organismo;
import es.mpt.dsic.loadTables.service.impl.ConsumidorDir;
import es.mpt.dsic.loadTables.service.impl.ConsumidorOficina;
import es.mpt.dsic.loadTables.utils.Constantes;
import es.mpt.dsic.loadTables.utils.Utils;

public class UnidadOrganicaController extends QuartzJobBean {

  protected static final Log logger = LogFactory.getLog(UnidadOrganicaController.class);

  @Autowired
  private UnidadOrganicaServiceImpl unidadOrganicaService;

  @Autowired
  private ConsumidorDir consumerUnidades;

  @Autowired
  private ConsumidorOficina consumerOficinas;

  private String rutaTemp;
  private String ficheroTxtTemporal;
  private String ficheroZipTemporal;
  private String pathDescompresion;

  private String ficheroOficinasTxtTemporal;
  private String ficheroOficinasZipTemporal;
  private String pathDescompresionOficinas;

  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    try {
      SchedulerContext schedCtx = context.getScheduler().getContext();

      ApplicationContext appCtx = (ApplicationContext) schedCtx.get("applicationContext");

      UnidadOrganicaController controller =
          (UnidadOrganicaController) appCtx.getBean("unidadOrganicaController");
      controller.loadUnidadOrganica();
      controller.loadOficinas();
    } catch (SchedulerException e) {
      logger.error("Error al cargar datos");
    }
  }

  public void loadUnidadOrganica() {
    try {
      logger.warn("Inicio Load Tablas UnidadOrganica");

      if (consumerUnidades.configure()) {
        // Comprueba que finalice en barra, si no, se la pone
        if (!rutaTemp.endsWith(Constantes.FILE_SEPARATOR))
          rutaTemp = rutaTemp + Constantes.FILE_SEPARATOR;

        // obtener fecha ultima sincronizacion
        Date unidadOrganicaSyncDate = unidadOrganicaService.geLastSync();

        String base64 = "";
        String file = rutaTemp + pathDescompresion + Constantes.FILE_SEPARATOR + "Unidades.xml";
        if (unidadOrganicaSyncDate == null) {
          base64 = consumerUnidades.volcadoDatosBasicos();
        } else {
          base64 = consumerUnidades.incrementalDatosBasicos(unidadOrganicaSyncDate);
        }

        // si es distinto de null y vacio
        if (!StringUtils.isEmpty(base64)) {
          inserData(file, base64);
        }
      } else {
        logger.warn("No se han podido actualizar las unidades organicas");
      }

    } catch (FileNotFoundException e) {
      logger.error("Error al cargar datos: " + e.getMessage());
      e.printStackTrace();
    } catch (IOException e) {
      logger.error("Error al cargar datos: " + e.getMessage());
      e.printStackTrace();
    } catch (UnidadOrganicaException e) {
      logger.error("Error al cargar datos: " + e.getMessage());
      e.printStackTrace();
    }
  }



  public void loadOficinas() {
    try {
      logger.warn("Inicio Load las oficinas en la Tabla UnidadOrganica");

      if (consumerOficinas.configure()) {
        // Comprueba que finalice en barra, si no, se la pone
        if (!rutaTemp.endsWith(Constantes.FILE_SEPARATOR))
          rutaTemp = rutaTemp + Constantes.FILE_SEPARATOR;

        // obtener fecha ultima sincronizacion. la anulamos porque esta es la fecha de creacion de
        // registro de unidadorganica
        Date unidadOrganicaSyncDate = unidadOrganicaService.geLastSync();
        // Date unidadOrganicaSyncDate = null;

        String base64 = "";
        String file =
            rutaTemp + pathDescompresionOficinas + Constantes.FILE_SEPARATOR + "Oficinas.xml";
        if (unidadOrganicaSyncDate == null) {
          base64 = consumerOficinas.volcadoDatosBasicos();
        } else {
          base64 = consumerOficinas.incrementalDatosBasicos(unidadOrganicaSyncDate);
        }

        // si es distinto de null y vacio
        if (!StringUtils.isEmpty(base64)) {
          inserDataOficinas(file, base64);
        }
      } else {
        logger.warn("No se han podido actualizar las oficinas");
      }

    } catch (FileNotFoundException e) {
      logger.error("Error al cargar datos: " + e.getMessage());
      e.printStackTrace();
    } catch (IOException e) {
      logger.error("Error al cargar datos: " + e.getMessage());
      e.printStackTrace();
    } catch (UnidadOrganicaException e) {
      logger.error("Error al cargar datos: " + e.getMessage());
      e.printStackTrace();
    }
  }



  private void inserData(String file, String base64) throws IOException, UnidadOrganicaException {
    // borrado de datos anteriores
    Utils.deleteBefore(rutaTemp, ficheroTxtTemporal, ficheroZipTemporal, pathDescompresion);

    // relleno fichero txt con base 64
    Utils.writeDataBase64(base64, rutaTemp, ficheroTxtTemporal);

    // convertir fichero txt base64 a zip
    Utils.getUnZippedFile(new FileInputStream(rutaTemp + ficheroTxtTemporal),
        rutaTemp + ficheroZipTemporal);

    // descomprimir fichero zip
    Utils.unZipFile(rutaTemp + ficheroZipTemporal, rutaTemp + pathDescompresion);

    logger.debug("Fichero a cargar: " + file);

    // paseo de ficheros
    HandlerUnidadOrganica handler = new HandlerUnidadOrganica(file);
    List<Organismo> organismos = handler.getOrganimos();

    // guardado de datos
    unidadOrganicaService.saveList(organismos, new Date());
  }


  private void inserDataOficinas(String file, String base64)
      throws IOException, UnidadOrganicaException {

    // borrado de datos anteriores
    Utils.deleteBefore(rutaTemp, ficheroOficinasTxtTemporal, ficheroOficinasZipTemporal,
        pathDescompresionOficinas);

    // relleno fichero txt con base 64
    Utils.writeDataBase64(base64, rutaTemp, ficheroOficinasTxtTemporal);

    // convertir fichero txt base64 a zip
    Utils.getUnZippedFile(new FileInputStream(rutaTemp + ficheroOficinasTxtTemporal),
        rutaTemp + ficheroOficinasZipTemporal);

    // descomprimir fichero zip
    Utils.unZipFile(rutaTemp + ficheroOficinasZipTemporal, rutaTemp + pathDescompresionOficinas);

    logger.debug("Fichero a cargar: " + file);


    // se hace un handleroficinas igual que el handlerunidadesorganicas y simulando un organismo
    // pero metiendo los datos de oficina para
    // poder cargarlo en la misma tabla porque solo queremos para validar, es decir, saber que
    // existe

    HandlerOficina handler = new HandlerOficina(file);
    List<Organismo> organismos = handler.getOrganimos();

    // guardado de datos
    unidadOrganicaService.saveList(organismos, new Date());

  }


  public UnidadOrganicaServiceImpl getUnidadOrganicaService() {
    return unidadOrganicaService;
  }

  public void setUnidadOrganicaService(UnidadOrganicaServiceImpl unidadOrganicaService) {
    this.unidadOrganicaService = unidadOrganicaService;
  }

  public String getRutaTemp() {
    return rutaTemp;
  }

  public void setRutaTemp(String rutaTemp) {
    this.rutaTemp = rutaTemp;
  }

  public String getFicheroTxtTemporal() {
    return ficheroTxtTemporal;
  }

  public void setFicheroTxtTemporal(String ficheroTxtTemporal) {
    this.ficheroTxtTemporal = ficheroTxtTemporal;
  }

  public String getFicheroZipTemporal() {
    return ficheroZipTemporal;
  }

  public void setFicheroZipTemporal(String ficheroZipTemporal) {
    this.ficheroZipTemporal = ficheroZipTemporal;
  }

  public String getPathDescompresion() {
    return pathDescompresion;
  }

  public void setPathDescompresion(String pathDescompresion) {
    this.pathDescompresion = pathDescompresion;
  }

  public String getFicheroOficinasTxtTemporal() {
    return ficheroOficinasTxtTemporal;
  }

  public void setFicheroOficinasTxtTemporal(String ficheroOficinasTxtTemporal) {
    this.ficheroOficinasTxtTemporal = ficheroOficinasTxtTemporal;
  }

  public String getFicheroOficinasZipTemporal() {
    return ficheroOficinasZipTemporal;
  }

  public void setFicheroOficinasZipTemporal(String ficheroOficinasZipTemporal) {
    this.ficheroOficinasZipTemporal = ficheroOficinasZipTemporal;
  }

  public String getPathDescompresionOficinas() {
    return pathDescompresionOficinas;
  }

  public void setPathDescompresionOficinas(String pathDescompresionOficinas) {
    this.pathDescompresionOficinas = pathDescompresionOficinas;
  }

  public ConsumidorDir getConsumerUnidades() {
    return consumerUnidades;
  }

  public void setConsumerUnidades(ConsumidorDir consumerUnidades) {
    this.consumerUnidades = consumerUnidades;
  }

  public ConsumidorOficina getConsumerOficinas() {
    return consumerOficinas;
  }

  public void setConsumerOficinas(ConsumidorOficina consumerOficinas) {
    this.consumerOficinas = consumerOficinas;
  }



}
