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


package es.mpt.dsic.eeutil.inside.admin.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para RespuestaEnvioJusticia complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="RespuestaEnvioJusticia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AuditoriaEsbAplicacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AuditoriaEsbModulo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AuditoriaEsbServicio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AuditoriaEsbMarcaTiempo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ack" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoEnvio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoUnidadOrganoRemitente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RespuestaEnvioJusticia",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/respuestaEnvioJusticia",
    propOrder = {"auditoriaEsbAplicacion", "auditoriaEsbModulo", "auditoriaEsbServicio",
        "auditoriaEsbMarcaTiempo", "ack", "codigoEnvio", "codigoUnidadOrganoRemitente", "mensaje"})
public class RespuestaEnvioJusticia2 {

  @XmlElement(name = "AuditoriaEsbAplicacion", required = true)
  protected String auditoriaEsbAplicacion;
  @XmlElement(name = "AuditoriaEsbModulo", required = true)
  protected String auditoriaEsbModulo;
  @XmlElement(name = "AuditoriaEsbServicio", required = true)
  protected String auditoriaEsbServicio;
  @XmlElement(name = "AuditoriaEsbMarcaTiempo", required = true)
  protected String auditoriaEsbMarcaTiempo;
  @XmlElement(required = true)
  protected String ack;
  @XmlElement(required = true)
  protected String codigoEnvio;
  @XmlElement(required = true)
  protected String codigoUnidadOrganoRemitente;
  @XmlElement(required = true)
  protected String mensaje;

  /**
   * Obtiene el valor de la propiedad auditoriaEsbAplicacion.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAuditoriaEsbAplicacion() {
    return auditoriaEsbAplicacion;
  }

  /**
   * Define el valor de la propiedad auditoriaEsbAplicacion.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAuditoriaEsbAplicacion(String value) {
    this.auditoriaEsbAplicacion = value;
  }

  /**
   * Obtiene el valor de la propiedad auditoriaEsbModulo.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAuditoriaEsbModulo() {
    return auditoriaEsbModulo;
  }

  /**
   * Define el valor de la propiedad auditoriaEsbModulo.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAuditoriaEsbModulo(String value) {
    this.auditoriaEsbModulo = value;
  }

  /**
   * Obtiene el valor de la propiedad auditoriaEsbServicio.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAuditoriaEsbServicio() {
    return auditoriaEsbServicio;
  }

  /**
   * Define el valor de la propiedad auditoriaEsbServicio.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAuditoriaEsbServicio(String value) {
    this.auditoriaEsbServicio = value;
  }

  /**
   * Obtiene el valor de la propiedad auditoriaEsbMarcaTiempo.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAuditoriaEsbMarcaTiempo() {
    return auditoriaEsbMarcaTiempo;
  }

  /**
   * Define el valor de la propiedad auditoriaEsbMarcaTiempo.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAuditoriaEsbMarcaTiempo(String value) {
    this.auditoriaEsbMarcaTiempo = value;
  }

  /**
   * Obtiene el valor de la propiedad ack.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAck() {
    return ack;
  }

  /**
   * Define el valor de la propiedad ack.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAck(String value) {
    this.ack = value;
  }

  /**
   * Obtiene el valor de la propiedad codigoEnvio.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCodigoEnvio() {
    return codigoEnvio;
  }

  /**
   * Define el valor de la propiedad codigoEnvio.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCodigoEnvio(String value) {
    this.codigoEnvio = value;
  }

  /**
   * Obtiene el valor de la propiedad codigoUnidadOrganoRemitente.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCodigoUnidadOrganoRemitente() {
    return codigoUnidadOrganoRemitente;
  }

  /**
   * Define el valor de la propiedad codigoUnidadOrganoRemitente.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCodigoUnidadOrganoRemitente(String value) {
    this.codigoUnidadOrganoRemitente = value;
  }

  /**
   * Obtiene el valor de la propiedad mensaje.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getMensaje() {
    return mensaje;
  }

  /**
   * Define el valor de la propiedad mensaje.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setMensaje(String value) {
    this.mensaje = value;
  }

}
