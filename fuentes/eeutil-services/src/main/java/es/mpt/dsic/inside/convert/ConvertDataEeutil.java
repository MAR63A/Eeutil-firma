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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import es.mpt.dsic.inside.model.EeutilAplicacion;
import es.mpt.dsic.inside.model.EeutilAplicacionOperacion;
import es.mpt.dsic.inside.model.EeutilAplicacionPropiedad;
import es.mpt.dsic.inside.model.EeutilAplicacionPropiedadId;
import es.mpt.dsic.inside.model.EeutilPeticion;
import es.mpt.dsic.inside.model.aplicacion.AplicacionObject;
import es.mpt.dsic.inside.model.aplicacionPeticion.AplicacionOperacionObject;
import es.mpt.dsic.inside.model.peticion.PeticionObject;

public class ConvertDataEeutil {

  public static List<AplicacionObject> converDataEeutil(List<EeutilAplicacion> data) {
    List<AplicacionObject> retorno = new ArrayList<AplicacionObject>();

    // agrupaci�n de datos
    Map<String, EeutilAplicacion> agrupados = new HashMap<String, EeutilAplicacion>();
    if (CollectionUtils.isNotEmpty(data)) {
      for (EeutilAplicacion app : data) {
        if (!agrupados.containsKey(app.getIdaplicacion())) {
          agrupados.put(app.getIdaplicacion(), app);
        }
      }
    }

    if (CollectionUtils.isNotEmpty(agrupados.entrySet())) {
      for (Entry<String, EeutilAplicacion> app : agrupados.entrySet()) {
        retorno.add(converDataEeutil(app.getValue()));
      }
    }
    return retorno;
  }

  public static AplicacionObject converDataEeutil(EeutilAplicacion data) {
    AplicacionObject retorno = new AplicacionObject();
    retorno.setIdentificador(data.getIdaplicacion());
    retorno.setActivo(data.isActiva());
    retorno.setAditionalData(convertAditionalData(data));
    retorno.setEmail(data.getEmail());
    retorno.setResponsable(data.getResponsable());
    retorno.setTelefono(data.getTelefono());
    retorno.setCodigoUnidadOrganica(data.getUnidad());
    return retorno;
  }

  public static EeutilAplicacion converDataEeutil(AplicacionObject data)
      throws NoSuchAlgorithmException, UnsupportedEncodingException {
    EeutilAplicacion retorno = new EeutilAplicacion();
    retorno.setActiva(data.isActivo());
    retorno.setEmail(data.getEmail());
    retorno.setResponsable(data.getResponsable());
    retorno.setIdaplicacion(data.getIdentificador());
    retorno.setDescripcion(data.getIdentificador());
    retorno.setTelefono(data.getTelefono());

    if (StringUtils.isNotEmpty(data.getPassword())) {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(data.getPassword().getBytes("UTF-8"));
      byte[] digest = md.digest();
      // convert the byte to hex format method 1
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < digest.length; i++) {
        sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
      }
      retorno.setPassword(sb.toString());
    }

    if (data.getAditionalData() != null) {
      if (data.getAditionalData().containsKey("firma")
          && data.getAditionalData().get("firma").equalsIgnoreCase("S")) {
        retorno.setFirma(true);
      } else {
        retorno.setFirma(false);
      }
      data.getAditionalData().remove("firma");
    }

    if (data.getAditionalData() != null) {
      if (data.getAditionalData().containsKey("sello")
          && data.getAditionalData().get("sello").equalsIgnoreCase("S")) {
        retorno.setSello(true);
      } else {
        retorno.setSello(false);
      }
      data.getAditionalData().remove("sello");
    }

    if (data.getAditionalData() != null) {
      if (data.getAditionalData().containsKey("tramitar")
          && data.getAditionalData().get("tramitar").equalsIgnoreCase("S")) {
        retorno.setTramitar(true);
      } else {
        retorno.setTramitar(false);
      }
      data.getAditionalData().remove("tramitar");
    }

    if (data.getAditionalData() != null) {
      retorno.setPropiedades(convertPropiedades(data.getIdentificador(), data.getAditionalData()));
    }
    retorno.setUnidad(data.getCodigoUnidadOrganica());

    return retorno;
  }

  public static Set<EeutilAplicacionPropiedad> convertPropiedades(String app,
      Map<String, String> data) {
    Set<EeutilAplicacionPropiedad> retorno = new HashSet<EeutilAplicacionPropiedad>();
    if (data != null) {
      for (String clave : data.keySet()) {
        if (StringUtils.isNotEmpty(clave) && StringUtils.isNotEmpty(data.get(clave))) {
          retorno.add(new EeutilAplicacionPropiedad(new EeutilAplicacionPropiedadId(app, clave),
              data.get(clave)));
        }
      }
    }
    return retorno;
  }

  public static Map<String, String> convertAditionalData(EeutilAplicacion data) {
    Map<String, String> retorno = new HashMap<String, String>();
    retorno.put("firma", data.isFirma() ? "S" : "N");
    retorno.put("sello", data.isSello() ? "S" : "N");
    retorno.put("tramitar", data.isTramitar() ? "S" : "N");
    if (CollectionUtils.isNotEmpty(data.getPropiedades())) {
      for (EeutilAplicacionPropiedad propiedad : data.getPropiedades()) {
        if (!retorno.containsKey(propiedad.getId().getPropiedad())) {
          retorno.put(propiedad.getId().getPropiedad(), propiedad.getValor());
        }
      }
    }
    return retorno;
  }

  public static Map<String, String> getDefaultAdicional() {
    Map<String, String> retorno = new HashMap<String, String>();
    retorno.put("firma", "S");
    retorno.put("sello", "S");
    retorno.put("tramitar", "S");
    retorno.put("algoritmoFirmaDefecto", "SHA1withRSA");
    retorno.put("aliasCertificado", "");
    retorno.put("formatoFirmaDefecto", "Adobe PDF");
    retorno.put("modoFirmaDefecto", "implicit");
    retorno.put("passwordCertificado", "<noaplica>");
    retorno.put("passwordKS", "");
    retorno.put("rutaKS", "");
    retorno.put("tipoKS", "");
    retorno.put("ip.openoffice", "");
    retorno.put("port.openoffice", "");
    retorno.put("rutaLogo", "${local_home_app}/sgtic/conf/escudo.jpg");
    retorno.put("escalaLogoX", "");
    retorno.put("escalaLogoY", "");
    return retorno;
  }

  public static List<AplicacionOperacionObject> converDataEeutilAplicacionPeticion(
      List<EeutilAplicacionOperacion> data) {
    List<AplicacionOperacionObject> retorno = new ArrayList<AplicacionOperacionObject>();
    if (CollectionUtils.isNotEmpty(data)) {
      for (EeutilAplicacionOperacion appOperacion : data) {
        retorno.add(new AplicacionOperacionObject(appOperacion.getIdAplicacion(),
            appOperacion.getOperacion(), appOperacion.getNumeroPeticiones(),
            appOperacion.isCapturar()));
      }
    }
    return retorno;
  }

  public static List<PeticionObject> converDataPeticion(List<EeutilPeticion> data) {
    List<PeticionObject> retorno = new ArrayList<PeticionObject>();
    if (CollectionUtils.isNotEmpty(data)) {
      for (EeutilPeticion peticion : data) {
        PeticionObject obj = new PeticionObject(peticion.getAplicacion(), peticion.getOperacion(),
            peticion.getFecha(), null);
        retorno.add(obj);
      }
    }
    return retorno;
  }

}
