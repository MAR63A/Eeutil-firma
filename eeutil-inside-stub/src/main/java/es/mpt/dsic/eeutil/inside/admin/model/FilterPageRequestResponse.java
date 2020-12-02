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
 * Clase Java para FilterPageRequestResponse complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="FilterPageRequestResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="totales" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="devueltos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pagina" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="paginas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FilterPageRequestResponse",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/filter",
    propOrder = {"totales", "devueltos", "pagina", "paginas"})
public class FilterPageRequestResponse {

  protected int totales;
  protected int devueltos;
  protected int pagina;
  protected int paginas;

  /**
   * Obtiene el valor de la propiedad totales.
   * 
   */
  public int getTotales() {
    return totales;
  }

  /**
   * Define el valor de la propiedad totales.
   * 
   */
  public void setTotales(int value) {
    this.totales = value;
  }

  /**
   * Obtiene el valor de la propiedad devueltos.
   * 
   */
  public int getDevueltos() {
    return devueltos;
  }

  /**
   * Define el valor de la propiedad devueltos.
   * 
   */
  public void setDevueltos(int value) {
    this.devueltos = value;
  }

  /**
   * Obtiene el valor de la propiedad pagina.
   * 
   */
  public int getPagina() {
    return pagina;
  }

  /**
   * Define el valor de la propiedad pagina.
   * 
   */
  public void setPagina(int value) {
    this.pagina = value;
  }

  /**
   * Obtiene el valor de la propiedad paginas.
   * 
   */
  public int getPaginas() {
    return paginas;
  }

  /**
   * Define el valor de la propiedad paginas.
   * 
   */
  public void setPaginas(int value) {
    this.paginas = value;
  }

}
