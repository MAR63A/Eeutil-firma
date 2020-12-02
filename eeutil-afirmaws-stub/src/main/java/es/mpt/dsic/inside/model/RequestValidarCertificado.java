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

public class RequestValidarCertificado extends RequestConfigAfirma {

  private String certificado;
  private Boolean infAmpliada;
  private int modoValidacion;
  private boolean obtenerInfo;

  public RequestValidarCertificado(RequestConfigAfirma config, String certificado,
      Boolean infAmpliada, int modoValidacion, boolean obtenerInfo) {
    super(config.getIdAplicacion(), config.getTruststore(), config.getPassTruststore());
    this.certificado = certificado;
    this.infAmpliada = infAmpliada;
    this.modoValidacion = modoValidacion;
    this.obtenerInfo = obtenerInfo;
  }

  public String getCertificado() {
    return certificado;
  }

  public void setCertificado(String certificado) {
    this.certificado = certificado;
  }

  public Boolean getInfAmpliada() {
    return infAmpliada;
  }

  public void setInfAmpliada(Boolean infAmpliada) {
    this.infAmpliada = infAmpliada;
  }

  public int getModoValidacion() {
    return modoValidacion;
  }

  public void setModoValidacion(int modoValidacion) {
    this.modoValidacion = modoValidacion;
  }

  public boolean isObtenerInfo() {
    return obtenerInfo;
  }

  public void setObtenerInfo(boolean obtenerInfo) {
    this.obtenerInfo = obtenerInfo;
  }
}
