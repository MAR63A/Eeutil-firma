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


package afirmaws.services.nativos.model.validarfirma;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="peticion">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://afirmaws/ws/firma}CadenaSinEspacios">
 *               &lt;enumeration value="ValidarFirma"/>
 *               &lt;enumeration value="FirmaServidor"/>
 *               &lt;enumeration value="FirmaServidorCoSign"/>
 *               &lt;enumeration value="FirmaServidorCounterSign"/>
 *               &lt;enumeration value="FirmaUsuario3FasesF1"/>
 *               &lt;enumeration value="FirmaUsuario3FasesF1CoSign"/>
 *               &lt;enumeration value="FirmaUsuario3FasesF1CounterSign"/>
 *               &lt;enumeration value="FirmaUsuario3FasesF3"/>
 *               &lt;enumeration value="FirmaUsuario2FasesF2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="versionMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="respuesta">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://afirmaws/ws/firma}Respuesta">
 *                 &lt;choice>
 *                   &lt;element name="Respuesta">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;choice>
 *                             &lt;sequence>
 *                               &lt;sequence>
 *                                 &lt;element ref="{http://afirmaws/ws/firma}estado"/>
 *                                 &lt;choice>
 *                                   &lt;element ref="{http://afirmaws/ws/firma}descripcion" minOccurs="0"/>
 *                                 &lt;/choice>
 *                               &lt;/sequence>
 *                               &lt;sequence>
 *                                 &lt;element ref="{http://afirmaws/ws/firma}idTransaccion" minOccurs="0"/>
 *                                 &lt;element ref="{http://afirmaws/ws/firma}hash" minOccurs="0"/>
 *                                 &lt;element ref="{http://afirmaws/ws/firma}algoritmoHash" minOccurs="0"/>
 *                                 &lt;element ref="{http://afirmaws/ws/firma}firmaElectronica" minOccurs="0"/>
 *                                 &lt;element ref="{http://afirmaws/ws/firma}formatoFirma" minOccurs="0"/>
 *                                 &lt;element ref="{http://afirmaws/ws/firma}justificanteFirmaElectronica" minOccurs="0"/>
 *                                 &lt;element ref="{http://afirmaws/ws/firma}idDocumento" minOccurs="0"/>
 *                               &lt;/sequence>
 *                             &lt;/sequence>
 *                           &lt;/choice>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Excepcion" type="{http://afirmaws/ws/firma}Excepcion"/>
 *                 &lt;/choice>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"peticion", "versionMsg", "respuesta"})
@XmlRootElement(name = "mensajeSalida")
public class MensajeSalida {

  @XmlElement(required = true)
  protected String peticion;
  @XmlElement(required = true)
  protected String versionMsg;
  @XmlElement(required = true)
  protected MensajeSalida.RespuestaX respuesta;

  /**
   * Gets the value of the peticion property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getPeticion() {
    return peticion;
  }

  /**
   * Sets the value of the peticion property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setPeticion(String value) {
    this.peticion = value;
  }

  /**
   * Gets the value of the versionMsg property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getVersionMsg() {
    return versionMsg;
  }

  /**
   * Sets the value of the versionMsg property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setVersionMsg(String value) {
    this.versionMsg = value;
  }

  /**
   * Gets the value of the respuesta property.
   * 
   * @return possible object is {@link MensajeSalida.Respuesta }
   * 
   */
  public MensajeSalida.RespuestaX getRespuesta() {
    return respuesta;
  }

  /**
   * Sets the value of the respuesta property.
   * 
   * @param value allowed object is {@link MensajeSalida.Respuesta }
   * 
   */
  public void setRespuesta(MensajeSalida.RespuestaX value) {
    this.respuesta = value;
  }


  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained within this class.
   * 
   * <pre>
   * &lt;complexType>
   *   &lt;complexContent>
   *     &lt;extension base="{http://afirmaws/ws/firma}Respuesta">
   *       &lt;choice>
   *         &lt;element name="Respuesta">
   *           &lt;complexType>
   *             &lt;complexContent>
   *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                 &lt;choice>
   *                   &lt;sequence>
   *                     &lt;sequence>
   *                       &lt;element ref="{http://afirmaws/ws/firma}estado"/>
   *                       &lt;choice>
   *                         &lt;element ref="{http://afirmaws/ws/firma}descripcion" minOccurs="0"/>
   *                       &lt;/choice>
   *                     &lt;/sequence>
   *                     &lt;sequence>
   *                       &lt;element ref="{http://afirmaws/ws/firma}idTransaccion" minOccurs="0"/>
   *                       &lt;element ref="{http://afirmaws/ws/firma}hash" minOccurs="0"/>
   *                       &lt;element ref="{http://afirmaws/ws/firma}algoritmoHash" minOccurs="0"/>
   *                       &lt;element ref="{http://afirmaws/ws/firma}firmaElectronica" minOccurs="0"/>
   *                       &lt;element ref="{http://afirmaws/ws/firma}formatoFirma" minOccurs="0"/>
   *                       &lt;element ref="{http://afirmaws/ws/firma}justificanteFirmaElectronica" minOccurs="0"/>
   *                       &lt;element ref="{http://afirmaws/ws/firma}idDocumento" minOccurs="0"/>
   *                     &lt;/sequence>
   *                   &lt;/sequence>
   *                 &lt;/choice>
   *               &lt;/restriction>
   *             &lt;/complexContent>
   *           &lt;/complexType>
   *         &lt;/element>
   *         &lt;element name="Excepcion" type="{http://afirmaws/ws/firma}Excepcion"/>
   *       &lt;/choice>
   *     &lt;/extension>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"respuesta", "excepcion"})
  public static class RespuestaX extends afirmaws.services.nativos.model.validarfirma.Respuesta2 {

    @XmlElement(name = "Respuesta", nillable = true)
    protected MensajeSalida.RespuestaX.Respuesta respuesta;
    @XmlElement(name = "Excepcion")
    protected Excepcion excepcion;

    /**
     * Gets the value of the respuesta property.
     * 
     * @return possible object is {@link MensajeSalida.RespuestaX.Respuesta }
     * 
     */
    public MensajeSalida.RespuestaX.Respuesta getRespuesta() {
      return respuesta;
    }

    /**
     * Sets the value of the respuesta property.
     * 
     * @param value allowed object is {@link MensajeSalida.RespuestaX.Respuesta }
     * 
     */
    public void setRespuesta(MensajeSalida.RespuestaX.Respuesta value) {
      this.respuesta = value;
    }

    /**
     * Gets the value of the excepcion property.
     * 
     * @return possible object is {@link Excepcion }
     * 
     */
    public Excepcion getExcepcion() {
      return excepcion;
    }

    /**
     * Sets the value of the excepcion property.
     * 
     * @param value allowed object is {@link Excepcion }
     * 
     */
    public void setExcepcion(Excepcion value) {
      this.excepcion = value;
    }


    /**
     * <p>
     * Java class for anonymous complex type.
     * 
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice>
     *         &lt;sequence>
     *           &lt;sequence>
     *             &lt;element ref="{http://afirmaws/ws/firma}estado"/>
     *             &lt;choice>
     *               &lt;element ref="{http://afirmaws/ws/firma}descripcion" minOccurs="0"/>
     *             &lt;/choice>
     *           &lt;/sequence>
     *           &lt;sequence>
     *             &lt;element ref="{http://afirmaws/ws/firma}idTransaccion" minOccurs="0"/>
     *             &lt;element ref="{http://afirmaws/ws/firma}hash" minOccurs="0"/>
     *             &lt;element ref="{http://afirmaws/ws/firma}algoritmoHash" minOccurs="0"/>
     *             &lt;element ref="{http://afirmaws/ws/firma}firmaElectronica" minOccurs="0"/>
     *             &lt;element ref="{http://afirmaws/ws/firma}formatoFirma" minOccurs="0"/>
     *             &lt;element ref="{http://afirmaws/ws/firma}justificanteFirmaElectronica" minOccurs="0"/>
     *             &lt;element ref="{http://afirmaws/ws/firma}idDocumento" minOccurs="0"/>
     *           &lt;/sequence>
     *         &lt;/sequence>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "",
        propOrder = {"estado", "descripcion", "idTransaccion", "hash", "algoritmoHash",
            "firmaElectronica", "formatoFirma", "justificanteFirmaElectronica", "idDocumento"})
    public static class Respuesta {

      protected Boolean estado;
      protected Descripcion descripcion;
      protected String idTransaccion;
      protected byte[] hash;
      protected String algoritmoHash;
      protected byte[] firmaElectronica;
      protected String formatoFirma;
      protected byte[] justificanteFirmaElectronica;
      protected String idDocumento;

      /**
       * Gets the value of the estado property.
       * 
       * @return possible object is {@link Boolean }
       * 
       */
      public Boolean isEstado() {
        return estado;
      }

      /**
       * Sets the value of the estado property.
       * 
       * @param value allowed object is {@link Boolean }
       * 
       */
      public void setEstado(Boolean value) {
        this.estado = value;
      }

      /**
       * Gets the value of the descripcion property.
       * 
       * @return possible object is {@link Descripcion }
       * 
       */
      public Descripcion getDescripcion() {
        return descripcion;
      }

      /**
       * Sets the value of the descripcion property.
       * 
       * @param value allowed object is {@link Descripcion }
       * 
       */
      public void setDescripcion(Descripcion value) {
        this.descripcion = value;
      }

      /**
       * Gets the value of the idTransaccion property.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getIdTransaccion() {
        return idTransaccion;
      }

      /**
       * Sets the value of the idTransaccion property.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setIdTransaccion(String value) {
        this.idTransaccion = value;
      }

      /**
       * Gets the value of the hash property.
       * 
       * @return possible object is byte[]
       */
      public byte[] getHash() {
        return hash;
      }

      /**
       * Sets the value of the hash property.
       * 
       * @param value allowed object is byte[]
       */
      public void setHash(byte[] value) {
        this.hash = ((byte[]) value);
      }

      /**
       * Gets the value of the algoritmoHash property.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getAlgoritmoHash() {
        return algoritmoHash;
      }

      /**
       * Sets the value of the algoritmoHash property.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setAlgoritmoHash(String value) {
        this.algoritmoHash = value;
      }

      /**
       * Gets the value of the firmaElectronica property.
       * 
       * @return possible object is byte[]
       */
      public byte[] getFirmaElectronica() {
        return firmaElectronica;
      }

      /**
       * Sets the value of the firmaElectronica property.
       * 
       * @param value allowed object is byte[]
       */
      public void setFirmaElectronica(byte[] value) {
        this.firmaElectronica = ((byte[]) value);
      }

      /**
       * Gets the value of the formatoFirma property.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getFormatoFirma() {
        return formatoFirma;
      }

      /**
       * Sets the value of the formatoFirma property.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setFormatoFirma(String value) {
        this.formatoFirma = value;
      }

      /**
       * Gets the value of the justificanteFirmaElectronica property.
       * 
       * @return possible object is byte[]
       */
      public byte[] getJustificanteFirmaElectronica() {
        return justificanteFirmaElectronica;
      }

      /**
       * Sets the value of the justificanteFirmaElectronica property.
       * 
       * @param value allowed object is byte[]
       */
      public void setJustificanteFirmaElectronica(byte[] value) {
        this.justificanteFirmaElectronica = ((byte[]) value);
      }

      /**
       * Gets the value of the idDocumento property.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getIdDocumento() {
        return idDocumento;
      }

      /**
       * Sets the value of the idDocumento property.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setIdDocumento(String value) {
        this.idDocumento = value;
      }

    }

  }

}
