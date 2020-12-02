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

package es.mpt.dsic.inside.model.aplicacion;

import java.util.Map;
import java.util.Map.Entry;

public class AplicacionObject {

  private String identificador;
  private boolean activo;
  private String password;
  private String responsable;
  private String email;
  private String telefono;
  private Map<String, String> aditionalData;
  private String aditionalString;
  private String codigoUnidadOrganica;
  private String serialNumberCertificado;
  private String numeroProcedimiento;


  public String getSerialNumberCertificado() {
    return serialNumberCertificado;
  }

  public void setSerialNumberCertificado(String serialNumberCertificado) {
    this.serialNumberCertificado = serialNumberCertificado;
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

  public String getResponsable() {
    return responsable;
  }

  public void setResponsable(String responsable) {
    this.responsable = responsable;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Map<String, String> getAditionalData() {
    return aditionalData;
  }

  public void setAditionalData(Map<String, String> aditionalData) {
    this.aditionalData = aditionalData;
  }

  public String getAditionalString() {
    StringBuffer retorno = new StringBuffer();
    if (aditionalData != null) {
      for (Entry<String, String> data : aditionalData.entrySet()) {
        if (!retorno.toString().equals("")) {
          retorno.append(";;");
        }
        retorno.append(data.getKey());
        retorno.append("=");
        retorno.append(data.getValue());
      }
    }
    return retorno.toString();
  }

  public void setAditionalString(String aditionalString) {
    this.aditionalString = aditionalString;
  }


  public String getNumeroProcedimiento() {
    return numeroProcedimiento;
  }

  public void setNumeroProcedimiento(String numeroProcedimiento) {
    this.numeroProcedimiento = numeroProcedimiento;
  }

}
