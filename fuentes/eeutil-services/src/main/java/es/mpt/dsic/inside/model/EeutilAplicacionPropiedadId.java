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
public class EeutilAplicacionPropiedadId implements Serializable {
  private String idaplicacion;
  private String propiedad;


  public EeutilAplicacionPropiedadId() {
    super();
    // TODO Auto-generated constructor stub
  }

  public EeutilAplicacionPropiedadId(String idaplicacion, String propiedad) {
    super();
    this.idaplicacion = idaplicacion;
    this.propiedad = propiedad;
  }

  public String getIdaplicacion() {
    return idaplicacion;
  }

  public void setIdaplicacion(String idaplicacion) {
    this.idaplicacion = idaplicacion;
  }

  public String getPropiedad() {
    return propiedad;
  }

  public void setPropiedad(String propiedad) {
    this.propiedad = propiedad;
  }

  @Override
  public String toString() {
    return "EeutilAplicacionPropiedadId [idaplicacion=" + idaplicacion + ", propiedad=" + propiedad
        + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idaplicacion == null) ? 0 : idaplicacion.hashCode());
    result = prime * result + ((propiedad == null) ? 0 : propiedad.hashCode());
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
    EeutilAplicacionPropiedadId other = (EeutilAplicacionPropiedadId) obj;
    if (idaplicacion == null) {
      if (other.idaplicacion != null) {
        return false;
      }
    } else if (!idaplicacion.equals(other.idaplicacion)) {
      return false;
    }
    if (propiedad == null) {
      if (other.propiedad != null) {
        return false;
      }
    } else if (!propiedad.equals(other.propiedad)) {
      return false;
    }
    return true;
  }


}
