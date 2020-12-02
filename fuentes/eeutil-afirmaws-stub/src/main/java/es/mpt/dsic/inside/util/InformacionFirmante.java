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

package es.mpt.dsic.inside.util;

import java.security.cert.X509Certificate;


public class InformacionFirmante {

  private String nombre;
  private String fecha;
  private TipoFirma tipo;
  private X509Certificate certificado;



  public static enum TipoFirma {
    F, // firma
    CF, // cofirma
    XF // contrafirma
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public TipoFirma getTipo() {
    return tipo;
  }

  public void setTipo(TipoFirma tipo) {
    this.tipo = tipo;
  }

  public X509Certificate getCertificado() {
    return certificado;
  }

  public void setCertificado(X509Certificate certificado) {
    this.certificado = certificado;
  }
}
