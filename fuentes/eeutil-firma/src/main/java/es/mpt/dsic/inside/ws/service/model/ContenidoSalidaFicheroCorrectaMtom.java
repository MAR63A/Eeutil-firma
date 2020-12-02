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
import javax.mail.util.ByteArrayDataSource;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.util.firma.model.RespuestaFirmaFichero;
import es.mpt.dsic.inside.utils.mime.MimeUtil;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resultadoFirmaFicheroMtom",
    propOrder = {"formatoFirma", "modoFirma", "algoritmoFirma", "contenido", "hashContenido",
        "algoritmoHashContenido", "firma", "hashFirma", "algoritmoHashFirma", "fechaFirma",
        "datosFirmante"})
public class ContenidoSalidaFicheroCorrectaMtom extends ContenidoSalida {

  @XmlElement(required = true, name = "formatoFirma")
  private String formatoFirma;
  @XmlElement(required = true, name = "modoFirma")
  private String modoFirma;
  @XmlElement(required = true, name = "algoritmoFirma")
  private String algoritmoFirma;

  @XmlElement(required = true, name = "contenido")
  @XmlMimeType("application/octet-stream")
  private DataHandler contenido;

  @XmlElement(required = true, name = "hashContenido")
  private String hashContenido;

  @XmlElement(required = true, name = "algoritmoHashContenido")
  private String algoritmoHashContenido;

  @XmlElement(required = true, name = "firma")
  @XmlMimeType("application/octet-stream")
  private DataHandler firma;

  @XmlElement(required = true, name = "hashFirma")
  private String hashFirma;

  @XmlElement(required = true, name = "algoritmoHashFirma")
  private String algoritmoHashFirma;

  @XmlElement(required = true, name = "fechaFirma")
  private String fechaFirma;
  @XmlElement(required = true, name = "datosFirmante")
  private InfoFirmante datosFirmante;

  public ContenidoSalidaFicheroCorrectaMtom() {}

  public ContenidoSalidaFicheroCorrectaMtom(RespuestaFirmaFichero respuesta) {
    this.formatoFirma = respuesta.getFormato();
    this.modoFirma = respuesta.getModo();
    this.algoritmoFirma = respuesta.getAlgoritmo();
    this.contenido = new DataHandler(new ByteArrayDataSource(respuesta.getContenido(),
        MimeUtil.getMimeType(respuesta.getContenido())));
    this.firma = new DataHandler(
        new ByteArrayDataSource(respuesta.getFirma(), MimeUtil.getMimeType(respuesta.getFirma())));
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

  public DataHandler getContenido() {
    return contenido;
  }

  public void setContenido(DataHandler contenido) {
    this.contenido = contenido;
  }

  public DataHandler getFirma() {
    return firma;
  }

  public void setFirma(DataHandler firma) {
    this.firma = firma;
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

  public String getHashContenido() {
    return hashContenido;
  }

  public void setHashContenido(String hashContenido) {
    this.hashContenido = hashContenido;
  }

  public String getAlgoritmoHashContenido() {
    return algoritmoHashContenido;
  }

  public void setAlgoritmoHashContenido(String algoritmoHashContenido) {
    this.algoritmoHashContenido = algoritmoHashContenido;
  }

  public String getHashFirma() {
    return hashFirma;
  }

  public void setHashFirma(String hashFirma) {
    this.hashFirma = hashFirma;
  }

  public String getAlgoritmoHashFirma() {
    return algoritmoHashFirma;
  }

  public void setAlgoritmoHashFirma(String algoritmoHashFirma) {
    this.algoritmoHashFirma = algoritmoHashFirma;
  }

  @Override
  public String toString() {
    return "ContenidoSalidaFicheroCorrecta [formatoFirma=" + formatoFirma + ", modoFirma="
        + modoFirma + ", algoritmoFirma=" + algoritmoFirma + ", fechaFirma=" + fechaFirma
        + ", datosFirmante=" + datosFirmante + "]";
  }

}
