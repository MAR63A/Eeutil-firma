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
 * Clase Java para Unidad complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Unidad">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroProcedimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="activo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Unidad", namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/unidad",
    propOrder = {"codigo", "nombre", "numeroProcedimiento", "activo"})
public class Unidad {

  @XmlElement(required = true)
  protected String codigo;
  @XmlElement(required = true)
  protected String nombre;
  @XmlElement(required = true)
  protected String numeroProcedimiento;
  protected boolean activo;

  /**
   * Obtiene el valor de la propiedad codigo.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCodigo() {
    return codigo;
  }

  /**
   * Define el valor de la propiedad codigo.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCodigo(String value) {
    this.codigo = value;
  }

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

}
