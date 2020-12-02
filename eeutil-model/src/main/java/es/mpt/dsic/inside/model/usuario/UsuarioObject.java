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

package es.mpt.dsic.inside.model.usuario;

public class UsuarioObject {

  private String identificador;
  private boolean activo;
  private String codigoUnidadOrganica;
  private String numeroProcedimiento;
  private String unidadDescripcion;
  private String idRole;


  public String getIdRole() {
    return idRole;
  }

  public void setIdRole(String idRole) {
    this.idRole = idRole;
  }

  public String getCodigoUnidadOrganica() {
    return codigoUnidadOrganica;
  }

  public void setCodigoUnidadOrganica(String codigoUnidadOrganica) {
    this.codigoUnidadOrganica = codigoUnidadOrganica;
  }

  public String getIdentificador() {
    return identificador;
  }

  public void setIdentificador(String identificador) {
    this.identificador = identificador;
  }

  public boolean isActivo() {
    return activo;
  }

  public void setActivo(boolean activo) {
    this.activo = activo;
  }

  public String getNumeroProcedimiento() {
    return numeroProcedimiento;
  }

  public void setNumeroProcedimiento(String numeroProcedimiento) {
    this.numeroProcedimiento = numeroProcedimiento;
  }

  public String getUnidadDescripcion() {
    return unidadDescripcion;
  }

  public void setUnidadDescripcion(String unidadDescripcion) {
    this.unidadDescripcion = unidadDescripcion;
  }


}
