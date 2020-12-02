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

package es.mpt.dsic.loadTables.handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import es.mpt.dsic.loadTables.exception.UnidadOrganicaException;
import es.mpt.dsic.loadTables.objects.DatosDependencia;
import es.mpt.dsic.loadTables.objects.DatosIdentificativos;
import es.mpt.dsic.loadTables.objects.DatosVigencia;
import es.mpt.dsic.loadTables.objects.Organismo;
import es.mpt.dsic.loadTables.utils.Constantes;

public class HandlerOficina extends DefaultHandler {

  private static Logger logger = Logger.getLogger(HandlerOficina.class);

  private List<Organismo> organimos;
  private Organismo organismo;
  private DatosIdentificativos datosIdentificativos;
  private DatosDependencia datosDependencia;
  private DatosVigencia datosVigencia;
  private StringBuilder value;

  public HandlerOficina(String file) throws UnidadOrganicaException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser parser;
    try {
      organimos = new ArrayList<Organismo>();
      parser = factory.newSAXParser();
      parser.parse(file, this);
    } catch (Exception e) {
      throw new UnidadOrganicaException("Error al parsear", e);
    }

  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes)
      throws SAXException {
    value = new StringBuilder();
    if (qName.equals(Constantes.ETIQUETA_OFICINA)) {
      organismo = new Organismo();
      datosIdentificativos = new DatosIdentificativos();
      datosDependencia = new DatosDependencia();
      datosVigencia = new DatosVigencia();
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    try {
      if (qName.equals(Constantes.ETIQUETA_OFICINA)) {
        organismo.setDatosIdentificativos(datosIdentificativos);
        organismo.setDatosDependencia(datosDependencia);
        organismo.setDatosVigencia(datosVigencia);
        logger.debug(
            "Nueva Oficina:" + organismo.getDatosIdentificativos().getUnidadOrganica().getCodigo());
        organimos.add(organismo);
      }


      if (value != null && !"".equals(value.toString().trim())) {

        // inicializamoos valores que deben ir informados para poder meterlos en la tabla de
        // unidadorganica
        // luego si vienen informados se actualizan
        if (datosDependencia.getUnidadOrganicaRaiz().getCodigo() == null)
          datosDependencia.setUnidadOrganicaRaizCodigo("");
        if (datosDependencia.getUnidadOrganicaRaiz().getDescripcion() == null)
          datosDependencia.setUnidadOrganicaRaizDescripcion("");



        if (qName.equals(Constantes.ETIQUETA_CODIGO_OFICINA)) {
          datosIdentificativos.setUnidadOrganicaCodigo(value.toString());
        }
        if (qName.equals(Constantes.ETIQUETA_NOMBRE_OFICINA)) {
          datosIdentificativos.setUnidadOrganicaDescripcion(value.toString());
        }
        if (qName.equals(Constantes.ETIQUETA_NIVEL_ADMINISTRACION_OFICINA)) {
          datosIdentificativos.setNivelAdministracion(Byte.parseByte("1"));
        }

        datosIdentificativos.setIndicadorEntidadDerechoPublico("");

        if (qName.equals(Constantes.ETIQUETA_CODIGO_EXTERNO)) {
          datosIdentificativos.setCodigoExterno("");
        }



        if (qName.equals(Constantes.ETIQUETA_CODIGO_UNIDAD_SUPERIOR_JERARQUICA_OFICINA)) {
          datosDependencia.setUnidadSuperiorJerarquicaCodigo(value.toString());
        }
        if (qName.equals(Constantes.ETIQUETA_DENOMINACION_UNIDAD_SUPERIOR_JERARQUICA_OFICINA)) {
          datosDependencia.setUnidadSuperiorJerarquicaDescripcion(value.toString());
        }
        if (qName.equals(Constantes.ETIQUETA_CODIGO_OFICINA_RAIZ)) {
          datosDependencia.setUnidadOrganicaRaizCodigo(value.toString());
        }
        if (qName.equals(Constantes.ETIQUETA_DENOMINACION_OFICINA_RAIZ)) {
          datosDependencia.setUnidadOrganicaRaizDescripcion(value.toString());
        }

        datosDependencia.setUnidadRaizEntidadDerechoPublicoCodigo("");
        datosDependencia.setUnidadRaizEntidadDerechoPublicoDescripcion("");

        if (qName.equals(Constantes.ETIQUETA_NIVEL_JERARQUICO)) {
          datosDependencia.setNivelJerarquico(Byte.parseByte("10"));
        }



        if (qName.equals(Constantes.ETIQUETA_ESTADO_OFICINA)) {
          datosVigencia.setEstado(value.toString());
        }
        if (qName.equals(Constantes.ETIQUETA_FECHA_ALTA_OFICINA)) {
          SimpleDateFormat tmpFormat = new SimpleDateFormat(Constantes.WS_FORMATO_FECHA);
          datosVigencia.setFechaAlta(tmpFormat.parse(value.toString()));
        }



        if (qName.equals(Constantes.ETIQUETA_CODIGO_TIPO_ENT_PUBLICA)) {
          organismo.setCodTipoEntPublica("");
        }
        if (qName.equals(Constantes.ETIQUETA_CODIGO_TIPO_UNIDAD)) {
          organismo.setCodTipoUnidad("");
        }
        if (qName.equals(Constantes.ETIQUETA_CODIGO_AMB_TERRITORIAL)) {
          organismo.setCodAmbTerritorial("");
        }
        if (qName.equals(Constantes.ETIQUETA_CODIGO_AMB_ENT_GEOGRAFICA)) {
          organismo.setCodAmbEntGeografica("");
        }



      }
    } catch (ParseException e) {
      logger.error("Error al parsear xml: " + e.getMessage());
    }
  }

  @Override
  public void characters(char[] ch, int start, int length) {
    if (length == 0)
      return;
    int end = (start + length) - 1;
    while (ch[start] <= '\u0020') {
      if (start == end)
        return;
      start++;
      length--;
    }
    while (ch[end] <= '\u0020') {
      if (end == start)
        return;
      length--;
      end--;
    }
    value.append(ch, start, length);
  }

  public List<Organismo> getOrganimos() {
    return organimos;
  }

  public void setOrganimos(List<Organismo> organimos) {
    this.organimos = organimos;
  }
}
