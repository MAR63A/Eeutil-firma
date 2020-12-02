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

package es.mpt.dsic.inside.convert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import es.mpt.dsic.eeutil.csvbroker.admin.model.AltaAplicacionResponse;
import es.mpt.dsic.eeutil.csvbroker.admin.model.Aplicacion;
import es.mpt.dsic.eeutil.csvbroker.admin.model.BajaAplicacionResponse;
import es.mpt.dsic.eeutil.csvbroker.admin.model.InfAdicional;
import es.mpt.dsic.eeutil.csvbroker.admin.model.InfAdicionalAltaAppResponse;
import es.mpt.dsic.eeutil.csvbroker.admin.model.ListaAplicacionesResponse;
import es.mpt.dsic.inside.model.aplicacion.AplicacionObject;



public class ConvertDataCsvBroker {

  public static Map<String, String> convertInfAdicional(InfAdicionalAltaAppResponse data) {
    Map<String, String> retorno = new HashMap<String, String>();
    if (CollectionUtils.isNotEmpty(data.getInformacionAdicionalAltaApp())) {
      for (InfAdicional infAdicional : data.getInformacionAdicionalAltaApp()) {
        retorno.put(infAdicional.getKey(), infAdicional.getValue());
      }
    }
    return retorno;
  }

  public static Map<String, String> convertInfAdicional(Aplicacion data) {
    Map<String, String> retorno = new HashMap<String, String>();
    return retorno;
  }

  public static List<AplicacionObject> convertData(ListaAplicacionesResponse data) {
    List<AplicacionObject> retorno = new ArrayList<AplicacionObject>();
    if (CollectionUtils.isNotEmpty(data.getAplicacion())) {
      for (Aplicacion app : data.getAplicacion()) {
        retorno.add(convertData(app));
      }
    }

    return retorno;
  }

  public static AplicacionObject convertData(Aplicacion data) {
    AplicacionObject retorno = new AplicacionObject();
    retorno.setActivo(data.isActivo());
    retorno.setEmail(data.getEmail());
    retorno.setIdentificador(data.getIdAplicacion());
    retorno.setResponsable(data.getResponsable());
    retorno.setTelefono(data.getTelefono());
    retorno.setAditionalData(convertInfAdicional(data));
    retorno.setCodigoUnidadOrganica(data.getCodigoUnidadOrganica());
    retorno.setSerialNumberCertificado(data.getSerialNumberCertificado());
    return retorno;
  }

  public static AplicacionObject convertData(AltaAplicacionResponse data) {
    return convertData(data.getAplicacion());
  }

  public static AplicacionObject convertData(BajaAplicacionResponse data) {
    return convertData(data.getAplicacion());
  }

  public static Aplicacion convertData(AplicacionObject data) {
    Aplicacion retorno = new Aplicacion();
    retorno.setActivo(data.isActivo());
    retorno.setEmail(data.getEmail());
    retorno.setPassword(data.getPassword());
    retorno.setIdAplicacion(data.getIdentificador());
    retorno.setResponsable(data.getResponsable());
    retorno.setTelefono(data.getTelefono());
    retorno.setCodigoUnidadOrganica(data.getCodigoUnidadOrganica());
    retorno.setSerialNumberCertificado(data.getSerialNumberCertificado());
    return retorno;
  }

}
