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

public class RequestValidarFirma extends RequestConfigAfirma {

  private String firmaElectronica;
  private String datos;
  private String hash;
  private String algoritmo;
  private String tipoFirma;

  public RequestValidarFirma(RequestConfigAfirma config, String firmaElectronica, String datos,
      String hash, String algoritmo, String tipoFirma) {
    super(config.getIdAplicacion(), config.getTruststore(), config.getPassTruststore());
    this.firmaElectronica = firmaElectronica;
    this.datos = datos;
    this.hash = hash;
    this.algoritmo = algoritmo;
    this.tipoFirma = tipoFirma;
  }

  public String getFirmaElectronica() {
    return firmaElectronica;
  }

  public void setFirmaElectronica(String firmaElectronica) {
    this.firmaElectronica = firmaElectronica;
  }

  public String getDatos() {
    return datos;
  }

  public void setDatos(String datos) {
    this.datos = datos;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public String getAlgoritmo() {
    return algoritmo;
  }

  public void setAlgoritmo(String algoritmo) {
    this.algoritmo = algoritmo;
  }

  public String getTipoFirma() {
    return tipoFirma;
  }

  public void setTipoFirma(String tipoFirma) {
    this.tipoFirma = tipoFirma;
  }

}
