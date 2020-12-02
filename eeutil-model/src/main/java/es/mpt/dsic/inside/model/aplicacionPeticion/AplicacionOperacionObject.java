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

package es.mpt.dsic.inside.model.aplicacionPeticion;

public class AplicacionOperacionObject {

  private String aplicacion;
  private String operacion;
  private Integer contador;
  private boolean capturar;

  public AplicacionOperacionObject(String aplicacion, String operacion, Integer contador,
      boolean capturar) {
    super();
    this.aplicacion = aplicacion;
    this.operacion = operacion;
    this.contador = contador;
    this.capturar = capturar;
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

  public Integer getContador() {
    return contador;
  }

  public void setContador(Integer contador) {
    this.contador = contador;
  }

  public boolean isCapturar() {
    return capturar;
  }

  public void setCapturar(boolean capturar) {
    this.capturar = capturar;
  }

}
