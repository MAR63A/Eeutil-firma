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
 * Clase Java para respuestaEnvioJusticiaResponse complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="respuestaEnvioJusticiaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RespuestaEnvioJusticia" type="{https://ssweb.seap.minhap.es/Inside/XSD/v1.0/respuestaEnvioJusticia}RespuestaEnvioJusticia" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "respuestaEnvioJusticiaResponse", propOrder = {"respuestaEnvioJusticia"})
public class RespuestaEnvioJusticiaResponse {

  @XmlElement(name = "RespuestaEnvioJusticia")
  protected RespuestaEnvioJusticia2 respuestaEnvioJusticia;

  /**
   * Obtiene el valor de la propiedad respuestaEnvioJusticia.
   * 
   * @return possible object is {@link RespuestaEnvioJusticia2 }
   * 
   */
  public RespuestaEnvioJusticia2 getRespuestaEnvioJusticia() {
    return respuestaEnvioJusticia;
  }

  /**
   * Define el valor de la propiedad respuestaEnvioJusticia.
   * 
   * @param value allowed object is {@link RespuestaEnvioJusticia2 }
   * 
   */
  public void setRespuestaEnvioJusticia(RespuestaEnvioJusticia2 value) {
    this.respuestaEnvioJusticia = value;
  }

}
