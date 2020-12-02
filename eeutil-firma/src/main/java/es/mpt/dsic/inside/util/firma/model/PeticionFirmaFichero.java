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

import java.util.Arrays;
import es.mpt.dsic.inside.ws.service.model.DatosEntradaFichero;



public class PeticionFirmaFichero {
  private static String DEFAULT_FORMATO_FIRMA = "CAdES";
  private static String DEFAULT_MODO_FIRMA = "explicit";
  private static String DEFAULT_ALGORITMO_FIRMA = "explicit";

  private String formato = DEFAULT_FORMATO_FIRMA;
  private String modo = DEFAULT_MODO_FIRMA;
  private String algoritmo = DEFAULT_ALGORITMO_FIRMA;
  private String params = "";
  // String cadena de contenido.
  private byte[] contenido;

  private boolean cofirmarSiFirmado;


  public PeticionFirmaFichero() {}

  public PeticionFirmaFichero(DatosEntradaFichero entrada) {
    this.algoritmo = entrada.getAlgoritmoFirma();
    this.contenido = entrada.getContenido();
    this.formato = entrada.getFormatoFirma();
    this.params = entrada.getNodeToSign();
    this.modo = entrada.getModoFirma();
    this.cofirmarSiFirmado = entrada.isCofirmarSiFirmado();
  }


  public String getParams() {
    return params;
  }

  public void setParams(String params) {
    this.params = params;
  }

  public String getFormato() {
    return formato;
  }

  public void setFormato(String formato) {
    if (formato != null && !formato.isEmpty())
      this.formato = formato;
    else
      this.formato = DEFAULT_FORMATO_FIRMA;
  }

  public String getModo() {
    return modo;
  }

  public void setModo(String modo) {
    if (modo != null && !modo.isEmpty())
      this.modo = modo;
    else
      this.modo = DEFAULT_MODO_FIRMA;
  }

  public String getAlgoritmo() {
    return algoritmo;
  }

  public void setAlgoritmo(String algoritmo) {
    if (algoritmo != null && !algoritmo.isEmpty())
      this.algoritmo = algoritmo;
    else
      this.algoritmo = DEFAULT_ALGORITMO_FIRMA;
  }

  public byte[] getContenido() {
    return contenido;
  }

  public void setContenido(byte[] contenido) {
    this.contenido = contenido;
  }

  public boolean isCofirmarSiFirmado() {
    return cofirmarSiFirmado;
  }

  public void setCofirmarSiFirmado(boolean cofirmarSiFirmado) {
    this.cofirmarSiFirmado = cofirmarSiFirmado;
  }


  @Override
  public String toString() {
    return "PeticionFirma [formato=" + formato + ", modo=" + modo + ", algoritmo=" + algoritmo
        + "]";
  }

  public boolean verificaContenido() {
    if (this.contenido == null || Arrays.toString(this.contenido).equals(""))
      return false;
    // return Pattern.matches("[A-Za-z0-9+/=]", this.contenido);
    // return Base64.isBase64(this.contenido.getBytes());
    return true;
  }



}

