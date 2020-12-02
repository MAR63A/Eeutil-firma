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
 * Clase Java para UsuarioResultadoBusqueda complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="UsuarioResultadoBusqueda">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paginador" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/filter}FilterPageRequestResponse"/>
 *         &lt;element name="resultados" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/usuario}Usuario" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UsuarioResultadoBusqueda",
    namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/usuario",
    propOrder = {"paginador", "resultados"})
public class UsuarioResultadoBusqueda {

  @XmlElement(required = true)
  protected FilterPageRequestResponse paginador;
  protected List<Usuario> resultados;

  /**
   * Obtiene el valor de la propiedad paginador.
   * 
   * @return possible object is {@link FilterPageRequestResponse }
   * 
   */
  public FilterPageRequestResponse getPaginador() {
    return paginador;
  }

  /**
   * Define el valor de la propiedad paginador.
   * 
   * @param value allowed object is {@link FilterPageRequestResponse }
   * 
   */
  public void setPaginador(FilterPageRequestResponse value) {
    this.paginador = value;
  }

  /**
   * Gets the value of the resultados property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the resultados property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getResultados().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link Usuario }
   * 
   * 
   */
  public List<Usuario> getResultados() {
    if (resultados == null) {
      resultados = new ArrayList<Usuario>();
    }
    return this.resultados;
  }

}
