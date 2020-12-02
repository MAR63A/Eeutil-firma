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

package es.mpt.dsic.inside.model.peticion;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PeticionObject {

  private String aplicacion;
  private String operacion;
  private Date fecha;
  private String fechaString;
  private byte[] peticion;

  private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

  public PeticionObject(String aplicacion, String operacion, Date fecha, byte[] peticion) {
    super();
    this.aplicacion = aplicacion;
    this.operacion = operacion;
    this.fecha = fecha;
    if (fecha != null) {
      this.fechaString = format.format(fecha);
    }
    this.peticion = peticion;
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

  public String getFechaString() {
    return fechaString;
  }

  public void setFechaString(String fechaString) {
    this.fechaString = fechaString;
  }

}
