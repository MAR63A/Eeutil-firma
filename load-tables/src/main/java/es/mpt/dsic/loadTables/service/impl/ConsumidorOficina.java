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

package es.mpt.dsic.loadTables.service.impl;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import es.mpt.dsic.loadTables.service.oficinas.model.Excluidos;
import es.mpt.dsic.loadTables.service.oficinas.model.FormatoFichero;
import es.mpt.dsic.loadTables.service.oficinas.model.OficinasWs;
import es.mpt.dsic.loadTables.service.oficinas.model.RespuestaWS;
import es.mpt.dsic.loadTables.service.oficinas.model.SD02OFDescargaOficinas;
import es.mpt.dsic.loadTables.service.oficinas.model.SD02OFDescargaOficinasService;
import es.mpt.dsic.loadTables.service.oficinas.model.TipoConsultaOF;
import es.mpt.dsic.loadTables.utils.Constantes;

public class ConsumidorOficina {

  private String user;
  private String password;
  private String url;
  private SD02OFDescargaOficinas sd02;

  protected static final Log logger = LogFactory.getLog(ConsumidorOficina.class);

  private Boolean configured = Boolean.FALSE;

  public Boolean configure() {
    if (!configured) {
      URL urlsc02 = null;
      try {
        logger.debug(String.format("El WS de Consumidor se encuentra en %s", url));
        urlsc02 = new URL(url);

        SD02OFDescargaOficinasService service2 = new SD02OFDescargaOficinasService(urlsc02);
        sd02 = service2.getSD02OFDescargaOficinas();
        configured = true;
      } catch (Exception me) {
        logger.error("No se puede crear la URL del servicio de DIR3 " + url, me);
      }
    }
    return configured;
  }

  public String volcadoDatosBasicos() throws IOException {

    OficinasWs oficinasRequest = new OficinasWs();
    oficinasRequest.setUsuario(user);
    oficinasRequest.setClave(password);
    oficinasRequest.setFormatoFichero(FormatoFichero.XML);
    oficinasRequest.setTipoConsulta(TipoConsultaOF.OFICINAS);
    Excluidos ex = new Excluidos();
    ex.getItem().add("V");
    oficinasRequest.setEstados(ex);
    RespuestaWS datos = sd02.exportar(oficinasRequest);

    return datos.getFichero();
  }

  public String incrementalDatosBasicos(Date fecha) throws IOException {
    SimpleDateFormat format = new SimpleDateFormat(Constantes.WS_FORMATO_FECHA);
    OficinasWs oficinasRequest = new OficinasWs();
    oficinasRequest.setUsuario(user);
    oficinasRequest.setClave(password);
    oficinasRequest.setFormatoFichero(FormatoFichero.XML);
    oficinasRequest.setTipoConsulta(TipoConsultaOF.OFICINAS);
    Excluidos ex = new Excluidos();
    ex.getItem().add("V");
    oficinasRequest.setEstados(ex);
    oficinasRequest.setFechaInicio(format.format(fecha));

    RespuestaWS datos = sd02.exportar(oficinasRequest);

    return datos.getFichero();
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}
