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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "aplicacion_operacion")
public class EeutilAplicacionOperacion implements Serializable {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  @Id
  @TableGenerator(name = "GeneradorPk_Aplicacion_Operacion", table = "GeneradorClaves",
      pkColumnName = "GenName", valueColumnName = "GenValue",
      pkColumnValue = "aplicacion_operacion", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "GeneradorPk_Aplicacion_Operacion")
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  @Column(name = "idaplicacion", nullable = false, length = 100)
  private String idAplicacion;

  @Column(name = "operacion", nullable = false, length = 100)
  private String operacion;

  @Column(name = "numero_peticiones", nullable = false)
  private Integer numeroPeticiones;

  @Type(type = "es.mpt.dsic.inside.util.bbdd.StringBooleanUserType")
  private boolean capturar;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getIdAplicacion() {
    return idAplicacion;
  }

  public void setIdAplicacion(String idAplicacion) {
    this.idAplicacion = idAplicacion;
  }

  public String getOperacion() {
    return operacion;
  }

  public void setOperacion(String operacion) {
    this.operacion = operacion;
  }

  public Integer getNumeroPeticiones() {
    return numeroPeticiones;
  }

  public void setNumeroPeticiones(Integer numeroPeticiones) {
    this.numeroPeticiones = numeroPeticiones;
  }

  public boolean isCapturar() {
    return capturar;
  }

  public void setCapturar(boolean capturar) {
    this.capturar = capturar;
  }

  @Override
  public String toString() {
    StringBuffer tmpBuff = new StringBuffer("PeticionesAplicaciones [");
    tmpBuff.append("id=");
    tmpBuff.append(id);
    tmpBuff.append(",idaplicacion=");
    tmpBuff.append(idAplicacion);
    tmpBuff.append(",operacion=");
    tmpBuff.append(operacion);
    tmpBuff.append(",numeroPeticiones=");
    tmpBuff.append(numeroPeticiones);
    tmpBuff.append(",capturar=");
    tmpBuff.append(capturar);
    tmpBuff.append("]");
    return tmpBuff.toString();
  }

}
