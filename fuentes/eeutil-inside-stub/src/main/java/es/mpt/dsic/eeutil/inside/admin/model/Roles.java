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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para Roles complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Roles">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="altaExpediente" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="modificarExpediente" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="leerExpediente" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="altaDocumento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="modificarDocumento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="leerDocumento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="administrarPermisos" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Roles", namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/aplicacion",
    propOrder = {"altaExpediente", "modificarExpediente", "leerExpediente", "altaDocumento",
        "modificarDocumento", "leerDocumento", "administrarPermisos"})
public class Roles {

  protected boolean altaExpediente;
  protected boolean modificarExpediente;
  protected boolean leerExpediente;
  protected boolean altaDocumento;
  protected boolean modificarDocumento;
  protected boolean leerDocumento;
  protected boolean administrarPermisos;

  /**
   * Obtiene el valor de la propiedad altaExpediente.
   * 
   */
  public boolean isAltaExpediente() {
    return altaExpediente;
  }

  /**
   * Define el valor de la propiedad altaExpediente.
   * 
   */
  public void setAltaExpediente(boolean value) {
    this.altaExpediente = value;
  }

  /**
   * Obtiene el valor de la propiedad modificarExpediente.
   * 
   */
  public boolean isModificarExpediente() {
    return modificarExpediente;
  }

  /**
   * Define el valor de la propiedad modificarExpediente.
   * 
   */
  public void setModificarExpediente(boolean value) {
    this.modificarExpediente = value;
  }

  /**
   * Obtiene el valor de la propiedad leerExpediente.
   * 
   */
  public boolean isLeerExpediente() {
    return leerExpediente;
  }

  /**
   * Define el valor de la propiedad leerExpediente.
   * 
   */
  public void setLeerExpediente(boolean value) {
    this.leerExpediente = value;
  }

  /**
   * Obtiene el valor de la propiedad altaDocumento.
   * 
   */
  public boolean isAltaDocumento() {
    return altaDocumento;
  }

  /**
   * Define el valor de la propiedad altaDocumento.
   * 
   */
  public void setAltaDocumento(boolean value) {
    this.altaDocumento = value;
  }

  /**
   * Obtiene el valor de la propiedad modificarDocumento.
   * 
   */
  public boolean isModificarDocumento() {
    return modificarDocumento;
  }

  /**
   * Define el valor de la propiedad modificarDocumento.
   * 
   */
  public void setModificarDocumento(boolean value) {
    this.modificarDocumento = value;
  }

  /**
   * Obtiene el valor de la propiedad leerDocumento.
   * 
   */
  public boolean isLeerDocumento() {
    return leerDocumento;
  }

  /**
   * Define el valor de la propiedad leerDocumento.
   * 
   */
  public void setLeerDocumento(boolean value) {
    this.leerDocumento = value;
  }

  /**
   * Obtiene el valor de la propiedad administrarPermisos.
   * 
   */
  public boolean isAdministrarPermisos() {
    return administrarPermisos;
  }

  /**
   * Define el valor de la propiedad administrarPermisos.
   * 
   */
  public void setAdministrarPermisos(boolean value) {
    this.administrarPermisos = value;
  }

}
