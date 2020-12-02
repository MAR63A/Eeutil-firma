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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "peticiones")
public class EeutilPeticion implements Serializable {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  @Id
  @TableGenerator(name = "GeneradorPk_Peticion", table = "GeneradorClaves",
      pkColumnName = "GenName", valueColumnName = "GenValue", pkColumnValue = "peticiones",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "GeneradorPk_Peticion")
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  @Column(name = "aplicacion", nullable = false, length = 100)
  private String aplicacion;

  @Column(name = "operacion", nullable = false, length = 100)
  private String operacion;

  @Column(name = "fecha", nullable = false)
  private Date fecha;

  @Column(name = "peticion", nullable = false)
  @Lob
  private byte[] peticion;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAplicacion() {
    return aplicacion;
  }

  public void setAplicacion(String aplicacion) {
    this.aplicacion = aplicacion;
  }

  public String getOperacion() {
    return operacion;
  }

  public void setOperacion(String operacion) {
    this.operacion = operacion;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public byte[] getPeticion() {
    return peticion;
  }

  public void setPeticion(byte[] peticion) {
    this.peticion = peticion;
  }

  @Override
  public String toString() {
    StringBuffer tmpBuff = new StringBuffer("Peticiones [");
    tmpBuff.append("id=");
    tmpBuff.append(id);
    tmpBuff.append(",aplicacion=");
    tmpBuff.append(aplicacion);
    tmpBuff.append(",operacion=");
    tmpBuff.append(operacion);
    tmpBuff.append(",fecha=");
    tmpBuff.append(fecha);
    tmpBuff.append("]");
    return tmpBuff.toString();
  }

}
