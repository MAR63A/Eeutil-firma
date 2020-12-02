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

package es.mpt.dsic.inside.model;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class EeutilAplicacionPlantillaId implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String idaplicacion;
  private String idplantilla;


  public EeutilAplicacionPlantillaId() {
    super();
    // TODO Auto-generated constructor stub
  }

  public EeutilAplicacionPlantillaId(String idaplicacion, String idplantilla) {
    super();
    this.idaplicacion = idaplicacion;
    this.idplantilla = idplantilla;
  }

  public String getIdaplicacion() {
    return idaplicacion;
  }

  public void setIdaplicacion(String idaplicacion) {
    this.idaplicacion = idaplicacion;
  }

  public String getIdplantilla() {
    return idplantilla;
  }

  public void setIdplantilla(String idplantilla) {
    this.idplantilla = idplantilla;
  }

  @Override
  public String toString() {
    return "EeutilAplicacionPlantillaId [idaplicacion=" + idaplicacion + ", idplantilla="
        + idplantilla + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idaplicacion == null) ? 0 : idaplicacion.hashCode());
    result = prime * result + ((idplantilla == null) ? 0 : idplantilla.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    EeutilAplicacionPlantillaId other = (EeutilAplicacionPlantillaId) obj;
    if (idaplicacion == null) {
      if (other.idaplicacion != null) {
        return false;
      }
    } else if (!idaplicacion.equals(other.idaplicacion)) {
      return false;
    }
    if (idplantilla == null) {
      if (other.idplantilla != null) {
        return false;
      }
    } else if (!idplantilla.equals(other.idplantilla)) {
      return false;
    }
    return true;
  }


}
