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

package es.mpt.dsic.inside.security.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import es.mpt.dsic.inside.model.EeutilAplicacion;
import es.mpt.dsic.inside.model.EeutilAplicacionPlantilla;
import es.mpt.dsic.inside.model.EeutilAplicacionPropiedad;
import es.mpt.dsic.inside.util.bbdd.VariableConversor;

public class EeutilAppInfo implements AppInfo, Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;


  private String idaplicacion;
  private String descripcion;
  private String password;
  private Map<String, String> propiedades = new HashMap<String, String>();

  private Map<String, byte[]> plantillas = new HashMap<String, byte[]>();

  @Override
  public Map<String, String> getPropiedades() {
    return propiedades;
  }

  public void setPropiedades(Map<String, String> propiedades) {
    this.propiedades = propiedades;
  }

  public EeutilAppInfo(String idaplicacion, String descripcion) {
    super();
    this.idaplicacion = idaplicacion;
    this.descripcion = descripcion;
  }

  public EeutilAppInfo(EeutilAplicacion app) {
    this.idaplicacion = app.getIdaplicacion();
    this.descripcion = app.getDescripcion();
    this.password = app.getPassword();
    propiedades = new HashMap<String, String>();
    for (EeutilAplicacionPropiedad prop : app.getPropiedades()) {
      propiedades.put(prop.getId().getPropiedad(), VariableConversor.convertir(prop.getValor()));
    }

    if (CollectionUtils.isNotEmpty(app.getPlantillas())) {
      for (EeutilAplicacionPlantilla plantilla : app.getPlantillas()) {
        plantillas.put(plantilla.getId().getIdplantilla(), plantilla.getPlantilla());
      }
    }
  }

  @Override
  public String getDescripcion() {
    return descripcion;
  }



  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }



  public void setIdaplicacion(String idaplicacion) {
    this.idaplicacion = idaplicacion;
  }



  @Override
  public String getIdaplicacion() {
    return this.idaplicacion;
  }

  @Override
  public String toString() {
    return "EeutilAppInfo [idaplicacion=" + idaplicacion + ", descripcion=" + descripcion
        + ", propiedades=" + propiedades + "]";
  }


  @Override
  public Map<String, byte[]> getPlantillas() {
    return plantillas;
  }

  @Override
  public byte[] getPlantilla(String idPlantilla) {
    if (plantillas != null) {
      return plantillas.get(idPlantilla);
    } else {
      return null;
    }
  }

  @Override
  public String getPassword() {
    return this.password;
  }

}
