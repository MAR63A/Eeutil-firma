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

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the afirmaws.ws.firma package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _TipoDocumento_QNAME =
      new QName("http://afirmaws/ws/firma", "tipoDocumento");
  private final static QName _JustificanteFirmaElectronica_QNAME =
      new QName("http://afirmaws/ws/firma", "justificanteFirmaElectronica");
  private final static QName _Datos_QNAME = new QName("http://afirmaws/ws/firma", "datos");
  private final static QName _FirmaElectronicaServidor_QNAME =
      new QName("http://afirmaws/ws/firma", "firmaElectronicaServidor");
  private final static QName _CustodiarDocumento_QNAME =
      new QName("http://afirmaws/ws/firma", "custodiarDocumento");
  private final static QName _IdAplicacion_QNAME =
      new QName("http://afirmaws/ws/firma", "idAplicacion");
  private final static QName _AlgoritmoHash_QNAME =
      new QName("http://afirmaws/ws/firma", "algoritmoHash");
  private final static QName _Estado_QNAME = new QName("http://afirmaws/ws/firma", "estado");
  private final static QName _IdReferencia_QNAME =
      new QName("http://afirmaws/ws/firma", "idReferencia");
  private final static QName _Firmante_QNAME = new QName("http://afirmaws/ws/firma", "firmante");
  private final static QName _CertificadoFirmante_QNAME =
      new QName("http://afirmaws/ws/firma", "certificadoFirmante");
  private final static QName _FormatoFirma_QNAME =
      new QName("http://afirmaws/ws/firma", "formatoFirma");
  private final static QName _Documento_QNAME = new QName("http://afirmaws/ws/firma", "documento");
  private final static QName _IdDocumento_QNAME =
      new QName("http://afirmaws/ws/firma", "idDocumento");
  private final static QName _NombreDocumento_QNAME =
      new QName("http://afirmaws/ws/firma", "nombreDocumento");
  private final static QName _Hash_QNAME = new QName("http://afirmaws/ws/firma", "hash");
  private final static QName _FirmanteObjetivo_QNAME =
      new QName("http://afirmaws/ws/firma", "firmanteObjetivo");
  private final static QName _FirmaElectronica_QNAME =
      new QName("http://afirmaws/ws/firma", "firmaElectronica");
  private final static QName _IdTransaccion_QNAME =
      new QName("http://afirmaws/ws/firma", "idTransaccion");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: afirmaws.ws.firma
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link InformacionAdicional }
   * 
   */
  public InformacionAdicional createInformacionAdicional() {
    return new InformacionAdicional();
  }

  /**
   * Create an instance of {@link MensajeSalida }
   * 
   */
  public MensajeSalida createMensajeSalida() {
    return new MensajeSalida();
  }

  /**
   * Create an instance of {@link MensajeSalida.Respuesta }
   * 
   */
  public MensajeSalida.RespuestaX createMensajeSalidaRespuesta() {
    return new MensajeSalida.RespuestaX();
  }

  /**
   * Create an instance of {@link InformacionAdicional.Firmante }
   * 
   */
  public InformacionAdicional.Firmante createInformacionAdicionalFirmante() {
    return new InformacionAdicional.Firmante();
  }

  /**
   * Create an instance of {@link Descripcion }
   * 
   */
  public Descripcion createDescripcion() {
    return new Descripcion();
  }

  /**
   * Create an instance of {@link ValidacionFirmaElectronica }
   * 
   */
  public ValidacionFirmaElectronica createValidacionFirmaElectronica() {
    return new ValidacionFirmaElectronica();
  }

  /**
   * Create an instance of {@link MensajeEntrada }
   * 
   */
  public MensajeEntrada createMensajeEntrada() {
    return new MensajeEntrada();
  }

  /**
   * Create an instance of {@link Parametros }
   * 
   */
  public Parametros createParametros() {
    return new Parametros();
  }

  /**
   * Create an instance of {@link afirmaws.ws.firma.Respuesta }
   * 
   */
  public afirmaws.services.nativos.model.validarfirma.Respuesta2 createRespuesta() {
    return new afirmaws.services.nativos.model.validarfirma.Respuesta2();
  }

  /**
   * Create an instance of {@link Excepcion }
   * 
   */
  public Excepcion createExcepcion() {
    return new Excepcion();
  }

  /**
   * Create an instance of {@link MensajeSalida.Respuesta.Respuesta }
   * 
   */
  public MensajeSalida.RespuestaX.Respuesta createMensajeSalidaRespuestaRespuesta() {
    return new MensajeSalida.RespuestaX.Respuesta();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://afirmaws/ws/firma", name = "tipoDocumento")
  public JAXBElement<String> createTipoDocumento(String value) {
    return new JAXBElement<String>(_TipoDocumento_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://afirmaws/ws/firma", name = "justificanteFirmaElectronica")
  public JAXBElement<byte[]> createJustificanteFirmaElectronica(byte[] value) {
    return new JAXBElement<byte[]>(_JustificanteFirmaElectronica_QNAME, byte[].class, null,
        ((byte[]) value));
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://afirmaws/ws/firma", name = "datos")
  public JAXBElement<byte[]> createDatos(byte[] value) {
    return new JAXBElement<byte[]>(_Datos_QNAME, byte[].class, null, ((byte[]) value));
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://afirmaws/ws/firma", name = "firmaElectronicaServidor")
  public JAXBElement<String> createFirmaElectronicaServidor(String value) {
    return new JAXBElement<String>(_FirmaElectronicaServidor_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://afirmaws/ws/firma", name = "custodiarDocumento",
      defaultValue = "false")
  public JAXBElement<Boolean> createCustodiarDocumento(Boolean value) {
    return new JAXBElement<Boolean>(_CustodiarDocumento_QNAME, Boolean.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://afirmaws/ws/firma", name = "idAplicacion")
  public JAXBElement<String> createIdAplicacion(String value) {
    return new JAXBElement<String>(_IdAplicacion_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://afirmaws/ws/firma", name = "algoritmoHash")
  public JAXBElement<String> createAlgoritmoHash(String value) {
    return new JAXBElement<String>(_AlgoritmoHash_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://afirmaws/ws/firma", name = "estado")
  public JAXBElement<Boolean> createEstado(Boolean value) {
    return new JAXBElement<Boolean>(_Estado_QNAME, Boolean.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://afirmaws/ws/firma", name = "idReferencia")
  public JAXBElement<String> createIdReferencia(String value) {
    return new JAXBElement<String>(_IdReferencia_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://afirmaws/ws/firma", name = "firmante")
  public JAXBElement<String> createFirmante(String value) {
    return new JAXBElement<String>(_Firmante_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://afirmaws/ws/firma", name = "certificadoFirmante")
  public JAXBElement<byte[]> createCertificadoFirmante(byte[] value) {
    return new JAXBElement<byte[]>(_CertificadoFirmante_QNAME, byte[].class, null,
        ((byte[]) value));
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://afirmaws/ws/firma", name = "formatoFirma")
  public JAXBElement<String> createFormatoFirma(String value) {
    return new JAXBElement<String>(_FormatoFirma_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://afirmaws/ws/firma", name = "documento")
  public JAXBElement<byte[]> createDocumento(byte[] value) {
    return new JAXBElement<byte[]>(_Documento_QNAME, byte[].class, null, ((byte[]) value));
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://afirmaws/ws/firma", name = "idDocumento")
  public JAXBElement<String> createIdDocumento(String value) {
    return new JAXBElement<String>(_IdDocumento_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://afirmaws/ws/firma", name = "nombreDocumento")
  public JAXBElement<String> createNombreDocumento(String value) {
    return new JAXBElement<String>(_NombreDocumento_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://afirmaws/ws/firma", name = "hash")
  public JAXBElement<byte[]> createHash(byte[] value) {
    return new JAXBElement<byte[]>(_Hash_QNAME, byte[].class, null, ((byte[]) value));
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://afirmaws/ws/firma", name = "firmanteObjetivo")
  public JAXBElement<byte[]> createFirmanteObjetivo(byte[] value) {
    return new JAXBElement<byte[]>(_FirmanteObjetivo_QNAME, byte[].class, null, ((byte[]) value));
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://afirmaws/ws/firma", name = "firmaElectronica")
  public JAXBElement<byte[]> createFirmaElectronica(byte[] value) {
    return new JAXBElement<byte[]>(_FirmaElectronica_QNAME, byte[].class, null, ((byte[]) value));
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://afirmaws/ws/firma", name = "idTransaccion")
  public JAXBElement<String> createIdTransaccion(String value) {
    return new JAXBElement<String>(_IdTransaccion_QNAME, String.class, null, value);
  }

}
