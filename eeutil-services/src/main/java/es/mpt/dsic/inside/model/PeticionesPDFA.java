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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "peticiones_pdfa")
public class PeticionesPDFA implements Serializable {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  @Id
  @TableGenerator(name = "GeneradorPk_Peticiones_PDFA", table = "GeneradorClaves",
      pkColumnName = "GenName", valueColumnName = "GenValue", pkColumnValue = "peticiones_pdfa",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "GeneradorPk_Peticiones_PDFA")
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  @Column(name = "idaplicacion", nullable = false, length = 100)
  private String idAplicacion;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "fecha_peticion", nullable = false, length = 19)
  private Date fechaPeticion;

  @Column(name = "numero_paginas", nullable = false)
  private Integer numeroPaginas;

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

  public Date getFechaPeticion() {
    return fechaPeticion;
  }

  public void setFechaPeticion(Date fechaPeticion) {
    this.fechaPeticion = fechaPeticion;
  }

  public Integer getNumeroPaginas() {
    return numeroPaginas;
  }

  public void setNumeroPaginas(Integer numeroPaginas) {
    this.numeroPaginas = numeroPaginas;
  }

  @Override
  public String toString() {
    StringBuffer tmpBuff = new StringBuffer("PeticionesPDFA [");
    tmpBuff.append("id=");
    tmpBuff.append(id);
    tmpBuff.append(",idaplicacion=");
    tmpBuff.append(idAplicacion);
    tmpBuff.append(",fechaPeticion=");
    tmpBuff.append(fechaPeticion);
    tmpBuff.append(",numeroPaginas=");
    tmpBuff.append(numeroPaginas);
    tmpBuff.append("]");
    return tmpBuff.toString();
  }

}
