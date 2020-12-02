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

public class RequestConfigAfirma {

  private String idAplicacion;
  private String truststore;
  private String passTruststore;

  public RequestConfigAfirma() {
    super();
  }

  public RequestConfigAfirma(String idAplicacion, String truststore, String passTruststore) {
    super();
    this.idAplicacion = idAplicacion;
    this.truststore = truststore;
    this.passTruststore = passTruststore;
  }

  public String getIdAplicacion() {
    return idAplicacion;
  }

  public void setIdAplicacion(String idAplicacion) {
    this.idAplicacion = idAplicacion;
  }

  public String getTruststore() {
    return truststore;
  }

  public void setTruststore(String truststore) {
    this.truststore = truststore;
  }

  public String getPassTruststore() {
    return passTruststore;
  }

  public void setPassTruststore(String passTruststore) {
    this.passTruststore = passTruststore;
  }

}
