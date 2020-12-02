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


package es.mpt.dsic.loadTables.service.oficinas.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para servicios complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="servicios">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="incluidos" type="{http://impl.manager.directorio.map.es/wsExport}excluidos" minOccurs="0"/>
 *         &lt;element name="excluidos" type="{http://impl.manager.directorio.map.es/wsExport}excluidos" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "servicios", propOrder = {"incluidos", "excluidos"})
public class Servicios {

  protected Excluidos incluidos;
  protected Excluidos excluidos;

  /**
   * Obtiene el valor de la propiedad incluidos.
   * 
   * @return possible object is {@link Excluidos }
   * 
   */
  public Excluidos getIncluidos() {
    return incluidos;
  }

  /**
   * Define el valor de la propiedad incluidos.
   * 
   * @param value allowed object is {@link Excluidos }
   * 
   */
  public void setIncluidos(Excluidos value) {
    this.incluidos = value;
  }

  /**
   * Obtiene el valor de la propiedad excluidos.
   * 
   * @return possible object is {@link Excluidos }
   * 
   */
  public Excluidos getExcluidos() {
    return excluidos;
  }

  /**
   * Define el valor de la propiedad excluidos.
   * 
   * @param value allowed object is {@link Excluidos }
   * 
   */
  public void setExcluidos(Excluidos value) {
    this.excluidos = value;
  }

}
