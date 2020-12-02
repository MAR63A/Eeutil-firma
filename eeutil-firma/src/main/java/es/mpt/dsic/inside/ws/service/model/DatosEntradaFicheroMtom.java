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

package es.mpt.dsic.inside.ws.service.model;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosEntradaFicheroMtom", propOrder = {"contenido", "formatoFirma", "modoFirma",
    "algoritmoFirma", "cofirmarSiFirmado", "huellaDigital", "algoritmoHuella"})
public class DatosEntradaFicheroMtom {
  @XmlElement(required = true, name = "contenido")
  @XmlMimeType("application/octet-stream")
  private DataHandler contenido;

  @XmlElement(required = false, name = "formatoFirma")
  private String formatoFirma;
  @XmlElement(required = false, name = "modoFirma")
  private String modoFirma;
  @XmlElement(required = false, name = "algoritmoFirma")
  private String algoritmoFirma;
  @XmlElement(required = false, name = "cofirmarSiFirmado")
  private boolean cofirmarSiFirmado;

  @XmlElement(required = false, name = "huellaDigital")
  private String huellaDigital;

  @XmlElement(required = false, name = "algoritmoHuella")
  protected DatosEntradaFicheroMtom.Algoritmo algoritmoHuella;

  public DataHandler getContenido() {
    return contenido;
  }

  public void setContenido(DataHandler contenido) {
    this.contenido = contenido;
  }

  public String getFormatoFirma() {
    return formatoFirma;
  }

  public void setFormatoFirma(String formatoFirma) {
    this.formatoFirma = formatoFirma;
  }

  public String getModoFirma() {
    return modoFirma;
  }

  public void setModoFirma(String modoFirma) {
    this.modoFirma = modoFirma;
  }

  public String getAlgoritmoFirma() {
    return algoritmoFirma;
  }

  public void setAlgoritmoFirma(String algoritmoFirma) {
    this.algoritmoFirma = algoritmoFirma;
  }

  public boolean isCofirmarSiFirmado() {
    return cofirmarSiFirmado;
  }

  public void setCofirmarSiFirmado(boolean cofirmarSiFirmado) {
    this.cofirmarSiFirmado = cofirmarSiFirmado;
  }

  public String getHuellaDigital() {
    return huellaDigital;
  }

  public void setHuellaDigital(String huellaDigital) {
    this.huellaDigital = huellaDigital;
  }

  public DatosEntradaFicheroMtom.Algoritmo getAlgoritmoHuella() {
    return algoritmoHuella;
  }

  public void setAlgoritmoHuella(DatosEntradaFicheroMtom.Algoritmo algoritmoHuella) {
    this.algoritmoHuella = algoritmoHuella;
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"value"})
  public static class Algoritmo {

    @XmlValue
    protected EnumeracionAlgoritmo value;

    /**
     * Gets the value of the value property.
     * 
     * @return possible object is {@link EnumeracionAlgoritmo }
     * 
     */
    public EnumeracionAlgoritmo getValue() {
      return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value allowed object is {@link EnumeracionAlgoritmo }
     * 
     */
    public void setValue(EnumeracionAlgoritmo value) {
      this.value = value;
    }

  }

  @Override
  public String toString() {
    return "DatosEntradaFichero [formatoFirma=" + formatoFirma + ", modoFirma=" + modoFirma
        + ", algoritmoFirma=" + algoritmoFirma + "cofirmarSiFirmado=" + cofirmarSiFirmado
        + "huellaDigital=" + huellaDigital + "algoritmoHuella=" + algoritmoHuella + "]";
  }

}
