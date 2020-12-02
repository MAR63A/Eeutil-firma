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
 * Clase Java para Usuario complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Usuario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nif" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoUnidadOrganica" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombreUnidadOrganica" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroProcedimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="activo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="idRol" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Usuario", namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/usuario",
    propOrder = {"nif", "codigoUnidadOrganica", "nombreUnidadOrganica", "numeroProcedimiento",
        "activo", "idRol"})
public class Usuario {

  @XmlElement(required = true)
  protected String nif;
  @XmlElement(required = true)
  protected String codigoUnidadOrganica;
  @XmlElement(required = true)
  protected String nombreUnidadOrganica;
  @XmlElement(required = true)
  protected String numeroProcedimiento;
  protected boolean activo;
  @XmlElement(required = true)
  protected String idRol;

  /**
   * Obtiene el valor de la propiedad nif.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNif() {
    return nif;
  }

  /**
   * Define el valor de la propiedad nif.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNif(String value) {
    this.nif = value;
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
   * Obtiene el valor de la propiedad nombreUnidadOrganica.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNombreUnidadOrganica() {
    return nombreUnidadOrganica;
  }

  /**
   * Define el valor de la propiedad nombreUnidadOrganica.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNombreUnidadOrganica(String value) {
    this.nombreUnidadOrganica = value;
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
   * Obtiene el valor de la propiedad idRol.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdRol() {
    return idRol;
  }

  /**
   * Define el valor de la propiedad idRol.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdRol(String value) {
    this.idRol = value;
  }

}
