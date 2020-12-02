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
 * Credenciales para llamar por usertoken a sw con urlDestino
 * 
 * <p>
 * Clase Java para CredencialesRemisionNube complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CredencialesRemisionNube">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="urlDestino" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="appRemitenteContenedora">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *               &lt;enumeration value="C"/>
 *               &lt;enumeration value="R"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CredencialesRemisionNube",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/credencialesRemisionNube",
    propOrder = {"urlDestino", "appRemitenteContenedora", "usuario", "password"})
public class CredencialesRemisionNube {

  @XmlElement(required = true)
  protected String urlDestino;
  @XmlElement(required = true)
  protected String appRemitenteContenedora;
  @XmlElement(required = true)
  protected String usuario;
  @XmlElement(required = true)
  protected String password;

  /**
   * Obtiene el valor de la propiedad urlDestino.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getUrlDestino() {
    return urlDestino;
  }

  /**
   * Define el valor de la propiedad urlDestino.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setUrlDestino(String value) {
    this.urlDestino = value;
  }

  /**
   * Obtiene el valor de la propiedad appRemitenteContenedora.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAppRemitenteContenedora() {
    return appRemitenteContenedora;
  }

  /**
   * Define el valor de la propiedad appRemitenteContenedora.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAppRemitenteContenedora(String value) {
    this.appRemitenteContenedora = value;
  }

  /**
   * Obtiene el valor de la propiedad usuario.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getUsuario() {
    return usuario;
  }

  /**
   * Define el valor de la propiedad usuario.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setUsuario(String value) {
    this.usuario = value;
  }

  /**
   * Obtiene el valor de la propiedad password.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getPassword() {
    return password;
  }

  /**
   * Define el valor de la propiedad password.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setPassword(String value) {
    this.password = value;
  }

}
