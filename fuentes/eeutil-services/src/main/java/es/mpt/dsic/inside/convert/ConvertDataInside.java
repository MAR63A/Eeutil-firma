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
import org.apache.commons.lang.StringUtils;
import es.mpt.dsic.eeutil.inside.admin.model.Aplicacion;
import es.mpt.dsic.eeutil.inside.admin.model.CredencialEeutil;
import es.mpt.dsic.eeutil.inside.admin.model.InfAdicional;
import es.mpt.dsic.eeutil.inside.admin.model.Roles;
import es.mpt.dsic.inside.model.aplicacion.AplicacionObject;

public class ConvertDataInside {

  public static AplicacionObject converDataToInside(Aplicacion data) {
    AplicacionObject retorno = new AplicacionObject();
    retorno.setIdentificador(data.getNombre());
    retorno.setActivo(data.isActivo());
    retorno.setAditionalData(convertRolesInside(data.getRoles(), data.getCredencialEeutil()));
    retorno.setEmail(data.getEmail());
    retorno.setResponsable(data.getResponsable());
    retorno.setTelefono(data.getTelefono());
    retorno.setCodigoUnidadOrganica(data.getCodigoUnidadOrganica());
    retorno.setSerialNumberCertificado(data.getSerialNumberCertificado());
    retorno.setNumeroProcedimiento(data.getNumeroProcedimiento());
    return retorno;
  }

  public static Aplicacion converDataFromInside(AplicacionObject data) {
    Aplicacion retorno = new Aplicacion();
    retorno.setNombre(data.getIdentificador());
    retorno.setPassword(data.getPassword());
    retorno.setRoles(ConvertDataInside.convertRolesInside(data.getAditionalData()));
    retorno.setTelefono(data.getTelefono());
    retorno.setEmail(data.getEmail());
    retorno.setResponsable(data.getResponsable());
    retorno.setCodigoUnidadOrganica(data.getCodigoUnidadOrganica());
    CredencialEeutil credencialEeutil = new CredencialEeutil();
    credencialEeutil.setAplicacion(data.getAditionalData().get("EEUTIL_IDENTIFICADOR"));
    credencialEeutil.setPassword(data.getAditionalData().get("EEUTIL_PASSWORD"));
    retorno.setCredencialEeutil(credencialEeutil);
    retorno.setSerialNumberCertificado(data.getSerialNumberCertificado());
    retorno.setNumeroProcedimiento(data.getNumeroProcedimiento());
    return retorno;
  }



  public static List<AplicacionObject> converDataInside(List<Aplicacion> data) {
    List<AplicacionObject> retorno = new ArrayList<AplicacionObject>();
    if (CollectionUtils.isNotEmpty(data)) {
      for (Aplicacion app : data) {
        retorno.add(converDataToInside(app));
      }
    }
    return retorno;
  }

  public static Map<String, String> convertRolesInside(Roles data,
      CredencialEeutil eeutilCredential) {
    Map<String, String> retorno = new HashMap<String, String>();
    if (data.isAdministrarPermisos()) {
      retorno.put("ROLE_ADMINISTRAR_PERMISOS", "true");
    } else {
      retorno.put("ROLE_ADMINISTRAR_PERMISOS", "false");
    }

    if (data.isAltaExpediente()) {
      retorno.put("ROLE_ALTA_EXPEDIENTE", "true");
    } else {
      retorno.put("ROLE_ALTA_EXPEDIENTE", "false");
    }

    if (data.isModificarExpediente()) {
      retorno.put("ROLE_MODIFICA_EXPEDIENTE", "true");
    } else {
      retorno.put("ROLE_MODIFICA_EXPEDIENTE", "false");
    }

    if (data.isLeerExpediente()) {
      retorno.put("ROLE_LEER_EXPEDIENTE", "true");
    } else {
      retorno.put("ROLE_LEER_EXPEDIENTE", "false");
    }

    if (data.isAltaDocumento()) {
      retorno.put("ROLE_ALTA_DOCUMENTO", "true");
    } else {
      retorno.put("ROLE_ALTA_DOCUMENTO", "false");
    }

    if (data.isModificarDocumento()) {
      retorno.put("ROLE_MODIFICA_DOCUMENTO", "true");
    } else {
      retorno.put("ROLE_MODIFICA_DOCUMENTO", "false");
    }

    if (data.isLeerDocumento()) {
      retorno.put("ROLE_LEER_DOCUMENTO", "true");
    } else {
      retorno.put("ROLE_LEER_DOCUMENTO", "false");
    }
    if (eeutilCredential != null && StringUtils.isNotEmpty(eeutilCredential.getAplicacion())) {
      retorno.put("EEUTIL_IDENTIFICADOR", eeutilCredential.getAplicacion());
    }
    if (eeutilCredential != null && StringUtils.isNotEmpty(eeutilCredential.getPassword())) {
      retorno.put("EEUTIL_PASSWORD", eeutilCredential.getPassword());
    }
    return retorno;
  }

  public static Roles convertRolesInside(Map<String, String> data) {
    Roles retorno = new Roles();
    retorno.setAdministrarPermisos(false);
    if (data != null && data.containsKey("ROLE_ALTA_EXPEDIENTE")
        && StringUtils.isNotEmpty(data.get("ROLE_ALTA_EXPEDIENTE"))
        && Boolean.parseBoolean(data.get("ROLE_ALTA_EXPEDIENTE"))) {
      retorno.setAltaExpediente(true);
    } else {
      retorno.setAltaExpediente(false);
    }
    if (data != null && data.containsKey("ROLE_MODIFICA_EXPEDIENTE")
        && StringUtils.isNotEmpty(data.get("ROLE_MODIFICA_EXPEDIENTE"))
        && Boolean.parseBoolean(data.get("ROLE_MODIFICA_EXPEDIENTE"))) {
      retorno.setModificarExpediente(true);
    } else {
      retorno.setModificarExpediente(false);
    }
    if (data != null && data.containsKey("ROLE_LEER_EXPEDIENTE")
        && StringUtils.isNotEmpty(data.get("ROLE_LEER_EXPEDIENTE"))
        && Boolean.parseBoolean(data.get("ROLE_LEER_EXPEDIENTE"))) {
      retorno.setLeerExpediente(true);
    } else {
      retorno.setLeerExpediente(false);
    }
    if (data != null && data.containsKey("ROLE_ALTA_DOCUMENTO")
        && StringUtils.isNotEmpty(data.get("ROLE_ALTA_DOCUMENTO"))
        && Boolean.parseBoolean(data.get("ROLE_ALTA_DOCUMENTO"))) {
      retorno.setAltaDocumento(true);
    } else {
      retorno.setAltaDocumento(false);
    }
    if (data != null && data.containsKey("ROLE_MODIFICA_DOCUMENTO")
        && StringUtils.isNotEmpty(data.get("ROLE_MODIFICA_DOCUMENTO"))
        && Boolean.parseBoolean(data.get("ROLE_MODIFICA_DOCUMENTO"))) {
      retorno.setModificarDocumento(true);
    } else {
      retorno.setModificarDocumento(false);
    }
    if (data != null && data.containsKey("ROLE_LEER_DOCUMENTO")
        && StringUtils.isNotEmpty(data.get("ROLE_LEER_DOCUMENTO"))
        && Boolean.parseBoolean(data.get("ROLE_LEER_DOCUMENTO"))) {
      retorno.setLeerDocumento(true);
    } else {
      retorno.setLeerDocumento(false);
    }
    return retorno;
  }

  public static Map<String, String> convertIndAdicionalInside(List<InfAdicional> datas) {
    Map<String, String> retorno = new HashMap<String, String>();
    if (CollectionUtils.isNotEmpty(datas)) {
      for (InfAdicional data : datas) {
        retorno.put(data.getKey(), data.getValue());
      }
    }
    return retorno;
  }

}
