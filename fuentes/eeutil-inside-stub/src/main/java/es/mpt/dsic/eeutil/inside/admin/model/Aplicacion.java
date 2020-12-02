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
 * Clase Java para Aplicacion complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Aplicacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="activo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="roles" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/aplicacion}Roles"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="responsable" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoUnidadOrganica" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroProcedimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="credencialEeutil" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/aplicacion}CredencialEeutil"/>
 *         &lt;element name="serialNumberCertificado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Aplicacion", namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/aplicacion",
    propOrder = {"nombre", "password", "activo", "roles", "email", "telefono", "responsable",
        "codigoUnidadOrganica", "numeroProcedimiento", "credencialEeutil",
        "serialNumberCertificado"})
public class Aplicacion {

  @XmlElement(required = true)
  protected String nombre;
  @XmlElement(required = true)
  protected String password;
  protected boolean activo;
  @XmlElement(required = true)
  protected Roles roles;
  @XmlElement(required = true)
  protected String email;
  @XmlElement(required = true)
  protected String telefono;
  @XmlElement(required = true)
  protected String responsable;
  @XmlElement(required = true)
  protected String codigoUnidadOrganica;
  @XmlElement(required = false)
  protected String numeroProcedimiento;
  @XmlElement(required = true)
  protected CredencialEeutil credencialEeutil;
  @XmlElement(required = false)
  protected String serialNumberCertificado;

  /**
   * Obtiene el valor de la propiedad nombre.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Define el valor de la propiedad nombre.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNombre(String value) {
    this.nombre = value;
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

  /**
   * Obtiene el valor de la propiedad activo.
   * 
   */
  public boolean isActivo() {
    return activo;
  }

  /**
   * Define el valor de la propiedad activo.
   * 
   */
  public void setActivo(boolean value) {
    this.activo = value;
  }

  /**
   * Obtiene el valor de la propiedad roles.
   * 
   * @return possible object is {@link Roles }
   * 
   */
  public Roles getRoles() {
    return roles;
  }

  /**
   * Define el valor de la propiedad roles.
   * 
   * @param value allowed object is {@link Roles }
   * 
   */
  public void setRoles(Roles value) {
    this.roles = value;
  }

  /**
   * Obtiene el valor de la propiedad email.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getEmail() {
    return email;
  }

  /**
   * Define el valor de la propiedad email.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setEmail(String value) {
    this.email = value;
  }

  /**
   * Obtiene el valor de la propiedad telefono.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getTelefono() {
    return telefono;
  }

  /**
   * Define el valor de la propiedad telefono.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setTelefono(String value) {
    this.telefono = value;
  }

  /**
   * Obtiene el valor de la propiedad responsable.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getResponsable() {
    return responsable;
  }

  /**
   * Define el valor de la propiedad responsable.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setResponsable(String value) {
    this.responsable = value;
  }

  /**
   * Obtiene el valor de la propiedad codigoUnidadOrganica.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCodigoUnidadOrganica() {
    return codigoUnidadOrganica;
  }

  /**
   * Define el valor de la propiedad codigoUnidadOrganica.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCodigoUnidadOrganica(String value) {
    this.codigoUnidadOrganica = value;
  }

  /**
   * Obtiene el valor de la propiedad numeroProcedimiento.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNumeroProcedimiento() {
    return numeroProcedimiento;
  }

  /**
   * Define el valor de la propiedad numeroProcedimiento.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNumeroProcedimiento(String value) {
    this.numeroProcedimiento = value;
  }

  /**
   * Obtiene el valor de la propiedad credencialEeutil.
   * 
   * @return possible object is {@link CredencialEeutil }
   * 
   */
  public CredencialEeutil getCredencialEeutil() {
    return credencialEeutil;
  }

  /**
   * Define el valor de la propiedad credencialEeutil.
   * 
   * @param value allowed object is {@link CredencialEeutil }
   * 
   */
  public void setCredencialEeutil(CredencialEeutil value) {
    this.credencialEeutil = value;
  }

  /**
   * Obtiene el valor de la propiedad serialNumberCertificado.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getSerialNumberCertificado() {
    return serialNumberCertificado;
  }

  /**
   * Define el valor de la propiedad serialNumberCertificado.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setSerialNumberCertificado(String value) {
    this.serialNumberCertificado = value;
  }

}
