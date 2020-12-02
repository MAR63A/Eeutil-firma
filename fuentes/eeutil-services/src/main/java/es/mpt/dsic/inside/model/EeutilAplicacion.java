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
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "inside_aplicaciones")
public class EeutilAplicacion implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;


  @Id
  private String idaplicacion;
  private String password;
  private String descripcion;
  @Type(type = "es.mpt.dsic.inside.util.bbdd.StringBooleanUserType")
  private boolean activa;
  @Type(type = "es.mpt.dsic.inside.util.bbdd.StringBooleanUserType")
  private boolean tramitar;
  @Type(type = "es.mpt.dsic.inside.util.bbdd.StringBooleanUserType")
  private boolean sello;
  @Type(type = "es.mpt.dsic.inside.util.bbdd.StringBooleanUserType")
  private boolean firma;
  private String email;
  private String telefono;
  private String responsable;
  private String unidad;


  @OneToMany(cascade = CascadeType.ALL, mappedBy = "id.idaplicacion", fetch = FetchType.EAGER)
  private Set<EeutilAplicacionPropiedad> propiedades = new HashSet<EeutilAplicacionPropiedad>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "id.idaplicacion", fetch = FetchType.EAGER)
  private Set<EeutilAplicacionPlantilla> plantillas = new HashSet<EeutilAplicacionPlantilla>();

  public Set<EeutilAplicacionPropiedad> getPropiedades() {
    return propiedades;
  }

  public void setPropiedades(Set<EeutilAplicacionPropiedad> propiedades) {
    this.propiedades = propiedades;
  }

  public boolean isTramitar() {
    return tramitar;
  }

  public void setTramitar(boolean tramitar) {
    this.tramitar = tramitar;
  }

  public boolean isSello() {
    return sello;
  }

  public void setSello(boolean sello) {
    this.sello = sello;
  }

  public boolean isFirma() {
    return firma;
  }

  public void setFirma(boolean firma) {
    this.firma = firma;
  }

  public String getIdaplicacion() {
    return idaplicacion;
  }

  public void setIdaplicacion(String idaplicacion) {
    this.idaplicacion = idaplicacion;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public boolean isActiva() {
    return activa;
  }

  public void setActiva(boolean activa) {
    this.activa = activa;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getResponsable() {
    return responsable;
  }

  public void setResponsable(String responsable) {
    this.responsable = responsable;
  }

  public Set<EeutilAplicacionPlantilla> getPlantillas() {
    return plantillas;
  }

  public void setPlantillas(Set<EeutilAplicacionPlantilla> plantillas) {
    this.plantillas = plantillas;
  }


  @Override
  public String toString() {
    return "EeutilAplicacion [idaplicacion=" + idaplicacion + ", password=" + password
        + ", descripcion=" + descripcion + ", activa=" + activa + ", tramitar=" + tramitar
        + ", sello=" + sello + ", firma=" + firma + ", propiedades=" + propiedades + "]";
  }

  /**
   * @return the unidad
   */
  public String getUnidad() {
    return unidad;
  }

  /**
   * @param unidad the unidad to set
   */
  public void setUnidad(String unidad) {
    this.unidad = unidad;
  }



}
