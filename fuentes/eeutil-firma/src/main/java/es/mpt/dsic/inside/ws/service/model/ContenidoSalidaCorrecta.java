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

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.util.firma.model.RespuestaFirma;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resultadoFirma", propOrder = {"formatoFirma", "modoFirma", "algoritmoFirma",
    "datosEntrada", "datosFirmados", "contenidoFirma", "fechaFirma", "datosFirmante"})
public class ContenidoSalidaCorrecta extends ContenidoSalida {

  @XmlElement(required = true, name = "formatoFirma")
  private String formatoFirma;
  @XmlElement(required = true, name = "modoFirma")
  private String modoFirma;
  @XmlElement(required = true, name = "algoritmoFirma")
  private String algoritmoFirma;
  @XmlElement(required = true, name = "datosEntrada")
  private String datosEntrada;
  @XmlElement(required = true, name = "datosFirmados")
  private String datosFirmados;
  @XmlElement(required = true, name = "contenidoFirma")
  private String contenidoFirma;
  @XmlElement(required = true, name = "fechaFirma")
  private String fechaFirma;
  @XmlElement(required = true, name = "datosFirmante")
  private InfoFirmante datosFirmante;

  public ContenidoSalidaCorrecta() {}

  public ContenidoSalidaCorrecta(RespuestaFirma respuesta) {
    this.formatoFirma = respuesta.getFormato();
    this.modoFirma = respuesta.getModo();
    this.algoritmoFirma = respuesta.getAlgoritmo();
    this.datosFirmados = respuesta.getDatosFirmados();
    this.contenidoFirma = respuesta.getFirma();
    this.fechaFirma = respuesta.dameFechaString();
    this.datosFirmante = new InfoFirmante(respuesta.getDatosFirma());
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

  public String getDatosEntrada() {
    return datosEntrada;
  }

  public void setDatosEntrada(String datosEntrada) {
    this.datosEntrada = datosEntrada;
  }

  public String getDatosFirmados() {
    return datosFirmados;
  }

  public void setDatosFirmados(String datosFirmados) {
    this.datosFirmados = datosFirmados;
  }

  public String getContenidoFirma() {
    return contenidoFirma;
  }

  public void setContenidoFirma(String contenidoFirma) {
    this.contenidoFirma = contenidoFirma;
  }

  public String getFechaFirma() {
    return fechaFirma;
  }

  public void setFechaFirma(String fechaFirma) {
    this.fechaFirma = fechaFirma;
  }

  public InfoFirmante getDatosFirmante() {
    return datosFirmante;
  }

  public void setDatosFirmante(InfoFirmante datosFirmante) {
    this.datosFirmante = datosFirmante;
  }

  @Override
  public String toString() {
    return "ContenidoSalidaCorrecta [formatoFirma=" + formatoFirma + ", modoFirma=" + modoFirma
        + ", algoritmoFirma=" + algoritmoFirma + ", datosEntrada=" + datosEntrada
        + ", datosFirmados=" + datosFirmados + ", contenidoFirma=" + contenidoFirma
        + ", fechaFirma=" + fechaFirma + ", datosFirmante=" + datosFirmante + "]";
  }

}
