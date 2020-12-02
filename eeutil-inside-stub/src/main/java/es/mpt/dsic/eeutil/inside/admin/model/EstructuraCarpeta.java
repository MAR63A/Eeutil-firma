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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para EstructuraCarpeta complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EstructuraCarpeta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identificadorEstructura" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoUnidadOrganica" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroProcedimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="carpetaIndizada" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/estructuraCarpeta}TipoCarpetaIndizada" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EstructuraCarpeta",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/estructuraCarpeta",
    propOrder = {"identificadorEstructura", "codigoUnidadOrganica", "numeroProcedimiento",
        "carpetaIndizada"})
public class EstructuraCarpeta {

  @XmlElement(required = true)
  protected String identificadorEstructura;
  @XmlElement(required = true)
  protected String codigoUnidadOrganica;
  @XmlElement(required = true)
  protected String numeroProcedimiento;
  protected List<TipoCarpetaIndizada> carpetaIndizada;

  /**
   * Obtiene el valor de la propiedad identificadorEstructura.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdentificadorEstructura() {
    return identificadorEstructura;
  }

  /**
   * Define el valor de la propiedad identificadorEstructura.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdentificadorEstructura(String value) {
    this.identificadorEstructura = value;
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
   * Gets the value of the carpetaIndizada property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the carpetaIndizada property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getCarpetaIndizada().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link TipoCarpetaIndizada }
   * 
   * 
   */
  public List<TipoCarpetaIndizada> getCarpetaIndizada() {
    if (carpetaIndizada == null) {
      carpetaIndizada = new ArrayList<TipoCarpetaIndizada>();
    }
    return this.carpetaIndizada;
  }

}
