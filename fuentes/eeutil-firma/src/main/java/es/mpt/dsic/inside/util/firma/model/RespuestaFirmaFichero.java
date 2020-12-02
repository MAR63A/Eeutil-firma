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

package es.mpt.dsic.inside.util.firma.model;

import java.text.SimpleDateFormat;

public class RespuestaFirmaFichero {

  private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
  private String formato;
  private String modo;
  private String algoritmo;
  private byte[] contenido;
  private byte[] firma;
  private DatosFirma datosFirma;


  public String getFormato() {
    return formato;
  }

  public void setFormato(String formato) {
    this.formato = formato;
  }

  public String getModo() {
    return modo;
  }

  public void setModo(String modo) {
    this.modo = modo;
  }

  public DatosFirma getDatosFirma() {
    return datosFirma;
  }

  public void setDatosFirma(DatosFirma datosFirma) {
    this.datosFirma = datosFirma;
  }

  public String getAlgoritmo() {
    return algoritmo;
  }

  public void setAlgoritmo(String algoritmo) {
    this.algoritmo = algoritmo;
  }



  public byte[] getContenido() {
    return contenido;
  }

  public void setContenido(byte[] contenido) {
    this.contenido = contenido;
  }

  public byte[] getFirma() {
    return firma;
  }

  public void setFirma(byte[] firma) {
    this.firma = firma;
  }

  public String dameFechaString() {
    return dateFormat.format(this.datosFirma.getFechaFirma());
  }

  @Override
  public String toString() {
    return "RespuestaFirma [formato=" + formato + ", modo=" + modo + ", algoritmo=" + algoritmo
        + ", datosFirma=" + datosFirma + "]";
  }
}
