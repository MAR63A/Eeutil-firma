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
 * Url destino de peticiones donde solicitar token o indices de expedientes seg�n dir3 padre
 * 
 * <p>
 * Clase Java para SolicitudAccesoExpAppUrl complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SolicitudAccesoExpAppUrl">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dir3Padre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="urlDestinoPeticion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SolicitudAccesoExpAppUrl",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/solicitudAccesoExpAppUrl",
    propOrder = {"dir3Padre", "urlDestinoPeticion"})
public class SolicitudAccesoExpAppUrl {

  @XmlElement(required = true)
  protected String dir3Padre;
  @XmlElement(required = true)
  protected String urlDestinoPeticion;

  /**
   * Obtiene el valor de la propiedad dir3Padre.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDir3Padre() {
    return dir3Padre;
  }

  /**
   * Define el valor de la propiedad dir3Padre.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDir3Padre(String value) {
    this.dir3Padre = value;
  }

  /**
   * Obtiene el valor de la propiedad urlDestinoPeticion.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getUrlDestinoPeticion() {
    return urlDestinoPeticion;
  }

  /**
   * Define el valor de la propiedad urlDestinoPeticion.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setUrlDestinoPeticion(String value) {
    this.urlDestinoPeticion = value;
  }

}
