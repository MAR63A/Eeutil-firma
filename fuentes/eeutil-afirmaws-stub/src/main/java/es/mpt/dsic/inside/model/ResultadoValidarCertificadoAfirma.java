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

import java.util.Map;


public class ResultadoValidarCertificadoAfirma {

  private boolean validado;

  private String idUsuario;

  private String numeroSerie;

  private String detalleValidacion;

  private Map<String, String> infoCertificado;

  public boolean isValidado() {
    return validado;
  }

  public void setValidado(boolean validado) {
    this.validado = validado;
  }

  public String getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(String idUsuario) {
    this.idUsuario = idUsuario;
  }

  public String getNumeroSerie() {
    return numeroSerie;
  }

  public void setNumeroSerie(String numeroSerie) {
    this.numeroSerie = numeroSerie;
  }

  public String getDetalleValidacion() {
    return detalleValidacion;
  }

  public void setDetalleValidacion(String detalleValidacion) {
    this.detalleValidacion = detalleValidacion;
  }

  public Map<String, String> getInfoCertificado() {
    return infoCertificado;
  }

  public void setInfoCertificado(Map<String, String> infoCertificado) {
    this.infoCertificado = infoCertificado;
  }


}
