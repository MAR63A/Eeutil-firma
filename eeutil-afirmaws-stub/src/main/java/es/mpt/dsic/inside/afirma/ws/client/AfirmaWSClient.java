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

package es.mpt.dsic.inside.afirma.ws.client;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.DSSSignature;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.ResponseBaseType;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.VerifyRequest;
import afirmaws.services.nativos.model.validarcertificado.InfoCertificadoInfo.Campo;
import afirmaws.services.nativos.model.validarcertificado.MensajeSalida.Respuesta;
import afirmaws.services.nativos.model.validarcertificado.MensajeSalida.Respuesta.ResultadoProcesamiento;
import afirmaws.services.nativos.model.validarfirma.InformacionAdicional;
import afirmaws.services.nativos.model.validarfirma.MensajeEntrada;
import afirmaws.services.nativos.model.validarfirma.MensajeSalida;
import afirmaws.services.nativos.model.validarfirma.Parametros;
import afirmaws.services.nativos.model.validarfirma.ValidacionFirmaElectronica;
import afirmaws.services.nativos.ws.validarcertificado.Validacion;
import afirmaws.services.nativos.ws.validarfirma.Firma;
import es.gob.afirma.core.signers.AOSigner;
import es.gob.afirma.core.signers.AOSignerFactory;
import es.gob.afirma.signers.pades.AOPDFSigner;
import es.mpt.dsic.inside.dssprocessing.DSSSignerProcessor;
import es.mpt.dsic.inside.dssprocessing.DSSSignerProcessorException;
import es.mpt.dsic.inside.dssprocessing.DSSSignerProcessorFactory;
import es.mpt.dsic.inside.dssprocessing.DSSUtil;
import es.mpt.dsic.inside.dssprocessing.constantes.DSSResultConstantes;
import es.mpt.dsic.inside.dssprocessing.impl.XMLDetachedDSSSignerProcessor;
import es.mpt.dsic.inside.dssprocessing.impl.XMLEnvelopedDSSSignerProcessor;
import es.mpt.dsic.inside.dssprocessing.impl.XMLEnvelopingDSSSignerProcessor;
import es.mpt.dsic.inside.exception.AfirmaException;
import es.mpt.dsic.inside.model.ContenidoInfoAfirma;
import es.mpt.dsic.inside.model.FirmaInfoAfirma;
import es.mpt.dsic.inside.model.InformacionFirmaAfirma;
import es.mpt.dsic.inside.model.RequestAmpliarFirmaDSS;
import es.mpt.dsic.inside.model.RequestConfigAfirma;
import es.mpt.dsic.inside.model.RequestObtenerInformacionFirma;
import es.mpt.dsic.inside.model.RequestValidarCertificado;
import es.mpt.dsic.inside.model.RequestValidarFirma;
import es.mpt.dsic.inside.model.RequestValidarFirmaDSS;
import es.mpt.dsic.inside.model.ResultadoAmpliarFirmaAfirma;
import es.mpt.dsic.inside.model.ResultadoValidacionFirmaFormatoAAfirma;
import es.mpt.dsic.inside.model.ResultadoValidacionInfoAfirma;
import es.mpt.dsic.inside.model.ResultadoValidarCertificadoAfirma;
import es.mpt.dsic.inside.model.TipoDeFirmaAfirma;
import es.mpt.dsic.inside.obtenerinformacionfirma.ContenidoFirmado;
import es.mpt.dsic.inside.obtenerinformacionfirma.ContentNotExtractedException;
import es.mpt.dsic.inside.obtenerinformacionfirma.ObtenerInformacionFirmaUtil;
import es.mpt.dsic.inside.util.AOUtilExt;
import es.mpt.dsic.inside.util.CodigosError;
import es.mpt.dsic.inside.util.InformacionFirmante;
import es.mpt.dsic.inside.utils.mime.MimeUtil;

public class AfirmaWSClient {

  protected final static Log logger = LogFactory.getLog(AfirmaWSClient.class);

  // @Autowired
  // @Qualifier("firma")
  private Firma firma;

  // @Autowired
  // @Qualifier("marshallerMvalidafirma")
  private Jaxb2Marshaller marshallerfirma;

  // @Autowired
  // @Qualifier("marshallerMvalidacert")
  private Jaxb2Marshaller marshallercert;

  private Jaxb2Marshaller marshallerdssverify;

  // @Autowired
  // @Qualifier("valCertificado")
  private Validacion valCert;

  private DSSSignature dssAfirmaVerify;

  private DSSSignerProcessorFactory dssProcessorFactory;

  private LoggingOutInterceptor logOut;

  private LoggingInInterceptor logIn;

  private long connectionTimeOut;

  private long receiveTimeOut;

  @PostConstruct
  private void configureLog() {
    logOut.setLimit(-1);
    logOut.setPrettyLogging(Boolean.TRUE);

    logIn.setLimit(-1);
    logIn.setPrettyLogging(Boolean.TRUE);
  }

  private void setupTimeouts(Client client) {

    HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
    HTTPClientPolicy policy = httpConduit.getClient();

    // set time to wait for response in milliseconds. zero means unlimited

    policy.setReceiveTimeout(receiveTimeOut);
    policy.setConnectionTimeout(connectionTimeOut);

  }

  private void disableChunking(Client client) {
    HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
    HTTPClientPolicy policy = httpConduit.getClient();
    policy.setAllowChunking(false);
    policy.setChunkingThreshold(0);
    logger.debug("AllowChunking:" + policy.isAllowChunking());
    logger.debug("ChunkingThreshold:" + policy.getChunkingThreshold());
  }

  public ResultadoValidacionInfoAfirma validarFirma(RequestValidarFirma requestValidarFirma) {
    setupTimeouts(ClientProxy.getClient(firma));
    disableChunking(ClientProxy.getClient(firma));

    System.setProperty("javax.net.ssl.trustStore", requestValidarFirma.getTruststore());
    System.setProperty("javax.net.ssl.trustStorePassword", requestValidarFirma.getPassTruststore());

    ResultadoValidacionInfoAfirma resultado = new ResultadoValidacionInfoAfirma();

    String peticion = crearPeticionValidar(requestValidarFirma);
    // logger.debug("Petici�n a Afirma validar firma:\n" + peticion);
    String respuesta = firma.validarFirma(peticion);
    // logger.debug("Respuesta a Afirma de validar firma:\n" + respuesta);

    MensajeSalida msg = procesarSalidaValidar(respuesta);

    String detalle = "";


    if (msg.getRespuesta().getRespuesta() != null && msg.getRespuesta().getRespuesta().isEstado()) {
      detalle = "La firma es v�lida";
      resultado.setEstado(true);
      resultado.setDetalle(detalle);

    } else {
      resultado.setEstado(false);
      detalle += construyeDetalleRespuesta(msg.getRespuesta().getExcepcion());
      resultado.setDetalle(detalle);
    }

    return resultado;


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // COMENTO ESTA LOCURA DE CODIGO PORQUE NO TIENE SENTIDO!!!! SI MANDO A AFIRMA UNA FIRMA PARA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// VALIDARLA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Y
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// YA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// ME
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// RESPONDE
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// QUE
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// ES
    // VALIDA/O INVALIDA... A QUE MIRO ESTADO DE LOS CERTIFICADOS!!!!!!! AFIRMA YA HABRA HECHO TODAS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// LAS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// COMPROBACIONES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// NECESARIAS
    // PARA AFIRMAR QUE LA FIRMA ES VALIDA O INVALIDA.
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // boolean firmaCorrecta = true;
    // boolean certCorrectos = true;
    //
    // // Si la respuesta no es nula, se ha podido validar la firma y
    // // comprobamos el resultado.
    // if (msg.getRespuesta().getRespuesta() != null) {
    //
    // List<Object> lista = msg.getRespuesta().getRespuesta()
    // .getDescripcion().getContent();
    // for (Object obj : lista) {
    // ValidacionFirmaElectronica val = (ValidacionFirmaElectronica) obj;
    //
    // // Firma correcta, validamos certificados.
    // if (msg.getRespuesta().getRespuesta().isEstado()) {
    //
    // List<InformacionAdicional.Firmante> firmantes = val
    // .getInformacionAdicional().getFirmante();
    // Iterator<InformacionAdicional.Firmante> itFirmantes = firmantes
    // .iterator();
    //
    // while (itFirmantes.hasNext() && certCorrectos) {
    // InformacionAdicional.Firmante firmante = itFirmantes
    // .next();
    // if ("XADES-A".equalsIgnoreCase(requestValidarFirma.getTipoFirma())
    // || "CADES-A".equalsIgnoreCase(requestValidarFirma.getTipoFirma())) {
    // //validamos el certificado TSA
    // RequestValidarCertificado requestValidarCertificado = new RequestValidarCertificado(
    // new RequestConfigAfirma(requestValidarFirma
    // .getIdAplicacion(), requestValidarFirma
    // .getTruststore(), requestValidarFirma
    // .getPassTruststore()),
    // Base64
    // .encodeBase64String(firmante
    // .getCertificadoTSA()), null, 3, true);
    // Respuesta valCertTsa = this
    // .crearPeticionValidarCertificado(requestValidarCertificado);
    //
    // if (!"0".equalsIgnoreCase(valCertTsa
    // .getResultadoProcesamiento()
    // .getResultadoValidacion().getResultado())) {
    // certCorrectos = false;
    // detalle += construyeDetalleRespuesta(valCertTsa
    // .getResultadoProcesamiento()
    // .getResultadoValidacion().getDescripcion());
    // }
    // } else {
    // //validamos el certificado de la firma
    // RequestValidarCertificado requestValidarCertificado = new RequestValidarCertificado(
    // new RequestConfigAfirma(requestValidarFirma
    // .getIdAplicacion(), requestValidarFirma
    // .getTruststore(), requestValidarFirma
    // .getPassTruststore()),
    // firmante.getCertificado(), false, 3, true);
    // Respuesta valCert = this
    // .crearPeticionValidarCertificado(requestValidarCertificado);
    // // Validacion de certificado correcta
    // if (!"0".equalsIgnoreCase(valCert
    // .getResultadoProcesamiento()
    // .getResultadoValidacion().getResultado())) {
    // certCorrectos = false;
    // detalle += construyeDetalleRespuesta(valCert
    // .getResultadoProcesamiento()
    // .getResultadoValidacion().getDescripcion());
    // }
    // }
    // }
    //
    // // Firma no correcta
    // } else {
    // firmaCorrecta = false;
    // detalle += construyeDetalleRespuesta(val);
    // }
    //
    // }
    //
    // // La respuesta es nula porque no se ha podido validar la firma.
    // } else {
    // firmaCorrecta = false;
    // detalle += construyeDetalleRespuesta(msg.getRespuesta()
    // .getExcepcion());
    // }
    //
    // if (firmaCorrecta && certCorrectos) {
    // detalle = "La firma es v�lida";
    // }
    //
    // resultado.setEstado(firmaCorrecta && certCorrectos);
    // resultado.setDetalle(detalle);

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////// fin comentario ////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


  }

  public ResultadoValidarCertificadoAfirma validarCertificado(
      RequestValidarCertificado requestValidarCertificado) {
    ResultadoValidarCertificadoAfirma resultado = new ResultadoValidarCertificadoAfirma();
    boolean validado = false;
    String idUsuario = null;
    String numeroSerie = null;
    String detalleValidacion = "";

    Respuesta valCert = this.crearPeticionValidarCertificado(requestValidarCertificado);


    Map<String, String> resultInfoCertificado = new HashMap<String, String>();

    // Validacion de certificado. Si no ha habido error en afirma no devuelve campo exception
    // relleno
    if (valCert.getExcepcion() == null) {
      validado = true;
      List<Campo> campos = valCert.getResultadoProcesamiento().getInfoCertificado().getCampo();
      for (Campo campo : campos) {
        if (campo.getIdCampo().equals("NIFResponsable")) {
          idUsuario = campo.getValorCampo();
        } else if (campo.getIdCampo().equals("numeroSerie")) {
          numeroSerie = campo.getValorCampo();
        } else if (campo.getIdCampo().equals("NIF-CIF") && idUsuario == null) {
          idUsuario = campo.getValorCampo();
        }

        if (requestValidarCertificado.getInfAmpliada()) {
          resultInfoCertificado.put(campo.getIdCampo(), campo.getValorCampo());
        }
      }

      detalleValidacion += construyeDetalleRespuesta(
          valCert.getResultadoProcesamiento().getResultadoValidacion().getDescripcion());
    } else {
      detalleValidacion += construyeDetalleRespuesta(valCert.getExcepcion());
      resultInfoCertificado.put("Afirma", "Error en Validaci�n");
    }



    if (idUsuario == null) {
      validado = false;
      detalleValidacion =
          "El NIF del responsable no se encuentra en la informaci�n del certificado";
    }

    resultado.setValidado(validado);
    resultado.setIdUsuario(idUsuario);
    resultado.setNumeroSerie(numeroSerie);
    resultado.setDetalleValidacion(detalleValidacion);
    resultado.setInfoCertificado(resultInfoCertificado);

    return resultado;
  }

  /**
   * M�todo que realiza una ampliaci�n sobre una firma
   * 
   * @param sign Firma a ampliar
   * @param processor Procesador para la firma
   * @param configuracion Configuraci�n de la aplicaci�n de firma
   * @return Resultado de la ampliaci�n
   * @throws AfirmaWSClientException
   */
  public ResponseBaseType ampliarFirmaDSS(RequestAmpliarFirmaDSS requestAmpliarFirmaDSS)
      throws AfirmaWSClientException {
    ResponseBaseType vresponse = null;

    System.setProperty("javax.net.ssl.trustStore", requestAmpliarFirmaDSS.getTruststore());
    System.setProperty("javax.net.ssl.trustStorePassword",
        requestAmpliarFirmaDSS.getPassTruststore());

    setupTimeouts(ClientProxy.getClient(dssAfirmaVerify));
    disableChunking(ClientProxy.getClient(dssAfirmaVerify));

    try {

      VerifyRequest vrequest = requestAmpliarFirmaDSS.getProcessor().buildVerifyRequestToUpgrade(
          requestAmpliarFirmaDSS.getIdAplicacion(), requestAmpliarFirmaDSS.getSign(),
          requestAmpliarFirmaDSS.getConfiguracion(), null);

      StringWriter sw = new StringWriter();
      marshallerdssverify.marshal(vrequest, new StreamResult(sw));

      logger.debug("Request @firma:" + sw.toString());
      String sResult = dssAfirmaVerify.verify(sw.toString());

      logger.debug("Response @firma:" + sResult);

      vresponse = (ResponseBaseType) ((JAXBElement<?>) marshallerdssverify
          .unmarshal(new StreamSource(new StringReader(sResult)))).getValue();

    } catch (XmlMappingException e) {
      logger.error("Error al hacer marshall o unmarshall" + e.getMessage(), e);
      throw new AfirmaWSClientException("Error al hacer marshall o unmarshall " + e.getMessage(),
          e);

    } catch (Throwable t) {
      logger.error("Error al realizar la peticion a DSSAfirmaVerify " + t.getMessage(), t);
      throw new AfirmaWSClientException(
          "Error al realizar la peticion a DSSAfirmaVerify " + t.getMessage(), t);
    }

    return vresponse;
  }

  private String construyeDetalleRespuesta(Object obj) {
    final String ln = System.getProperty("line.separator");

    StringBuffer mensaje = new StringBuffer("");
    if (obj instanceof afirmaws.services.nativos.model.validarcertificado.MensajeSalida.Respuesta.Excepcion) {
      afirmaws.services.nativos.model.validarcertificado.MensajeSalida.Respuesta.Excepcion e =
          (afirmaws.services.nativos.model.validarcertificado.MensajeSalida.Respuesta.Excepcion) obj;
      mensaje.append("CodigoError: " + e.getCodigoError() + ln);
      mensaje.append("Descripcion: " + e.getDescripcion() + ln);
      mensaje.append("ExcepcionAsociada: " + e.getExcepcionAsociada() + ln);
    } else if (obj instanceof afirmaws.services.nativos.model.validarfirma.Excepcion) {
      afirmaws.services.nativos.model.validarfirma.Excepcion e =
          (afirmaws.services.nativos.model.validarfirma.Excepcion) obj;
      mensaje.append("CodigoError: " + e.getCodigoError() + ln);
      mensaje.append("Descripcion: " + e.getDescripcion() + ln);
      mensaje.append("ExcepcionAsociada: " + e.getExcepcionAsociada() + ln);
    } else if (obj instanceof ValidacionFirmaElectronica) {
      ValidacionFirmaElectronica val = (ValidacionFirmaElectronica) obj;
      mensaje.append("Proceso: " + val.getProceso() + ln);
      mensaje.append("Detalle: " + val.getDetalle() + ln);
      mensaje.append("Conclusion: " + val.getConclusion() + ln);
    } else if (obj instanceof ResultadoProcesamiento) {
      ResultadoProcesamiento res = (ResultadoProcesamiento) obj;
      mensaje.append("Resultado: " + res.getResultadoValidacion().getResultado() + ln);
      mensaje.append("Descripcion: " + res.getResultadoValidacion().getDescripcion() + ln);
    } else if (obj instanceof String) {
      String s = (String) obj;
      mensaje.append(s + ln);
    }

    return mensaje.toString();
  }

  private MensajeSalida procesarSalidaValidar(String entrada) {
    StreamSource source = new StreamSource(new StringReader(entrada));

    Object obj = marshallerfirma.unmarshal(source);
    logger.debug(obj);
    return (MensajeSalida) obj;
  }

  private String crearPeticionValidar(RequestValidarFirma requestValidarFirma) {

    MensajeEntrada entrada = new MensajeEntrada();
    entrada.setPeticion(AfirmaConstantes.OP_VALIDAR_FIRMA);
    entrada.setVersionMsg(AfirmaConstantes.VERSION_1_0);

    Parametros parametros = new Parametros();
    // parametros.setIdAplicacion(AfirmaConstantes.ID_APLICACION);
    parametros.setIdAplicacion(requestValidarFirma.getIdAplicacion());
    parametros.setFirmaElectronica(requestValidarFirma.getFirmaElectronica());

    if (requestValidarFirma.getTipoFirma() != null) {
      parametros.setFormatoFirma(requestValidarFirma.getTipoFirma());
    }

    if (requestValidarFirma.getDatos() != null) {
      parametros.setDatos(requestValidarFirma.getDatos());
    }

    if (requestValidarFirma.getHash() != null) {
      parametros.setHash(requestValidarFirma.getHash().getBytes());
    }

    if (requestValidarFirma.getAlgoritmo() != null) {
      parametros.setAlgoritmoHash(requestValidarFirma.getAlgoritmo());
    }
    //
    entrada.setParametros(parametros);
    StringWriter sw = new StringWriter();
    marshallerfirma.marshal(entrada, new StreamResult(sw));

    return sw.toString();
  }

  private Respuesta crearPeticionValidarCertificado(
      RequestValidarCertificado requestValidarCertificado) {

    setupTimeouts(ClientProxy.getClient(valCert));
    disableChunking(ClientProxy.getClient(valCert));

    System.setProperty("javax.net.ssl.trustStore", requestValidarCertificado.getTruststore());
    System.setProperty("javax.net.ssl.trustStorePassword",
        requestValidarCertificado.getPassTruststore());

    afirmaws.services.nativos.model.validarcertificado.MensajeEntrada entrada =
        new afirmaws.services.nativos.model.validarcertificado.MensajeEntrada();
    entrada.setPeticion(AfirmaConstantes.OP_VALIDAR_CERTIFICADO);
    entrada.setVersionMsg(AfirmaConstantes.VERSION_1_0);

    afirmaws.services.nativos.model.validarcertificado.MensajeEntrada.Parametros parametros =
        new afirmaws.services.nativos.model.validarcertificado.MensajeEntrada.Parametros();

    parametros.setIdAplicacion(requestValidarCertificado.getIdAplicacion());
    parametros.setModoValidacion(requestValidarCertificado.getModoValidacion());
    parametros.setObtenerInfo(requestValidarCertificado.isObtenerInfo());
    parametros.setCertificado(requestValidarCertificado.getCertificado());

    entrada.setParametros(parametros);
    StringWriter sw = new StringWriter();
    marshallercert.marshal(entrada, new StreamResult(sw));
    String salidaCert = valCert.validarCertificado(sw.toString());

    afirmaws.services.nativos.model.validarcertificado.MensajeSalida salidaCertM =
        getSalidaCert(salidaCert);

    return salidaCertM.getRespuesta();

  }

  private afirmaws.services.nativos.model.validarcertificado.MensajeSalida getSalidaCert(
      String entrada)

  {
    StreamSource source = new StreamSource(new StringReader(entrada));

    Object obj = marshallercert.unmarshal(source);
    logger.debug(obj);
    return (afirmaws.services.nativos.model.validarcertificado.MensajeSalida) obj;
  }

  private ResponseBaseType validarFirmaDSS(RequestValidarFirmaDSS requestValidarFirmaDSS)
      throws AfirmaWSClientException {

    setupTimeouts(ClientProxy.getClient(dssAfirmaVerify));
    disableChunking(ClientProxy.getClient(dssAfirmaVerify));

    System.setProperty("javax.net.ssl.trustStore", requestValidarFirmaDSS.getTruststore());
    System.setProperty("javax.net.ssl.trustStorePassword",
        requestValidarFirmaDSS.getPassTruststore());

    ResponseBaseType vresponse = null;

    try {

      VerifyRequest vrequest =
          requestValidarFirmaDSS.getProcessor().buildVerifyRequest(requestValidarFirmaDSS.getSign(),
              requestValidarFirmaDSS.getIdAplicacion(), requestValidarFirmaDSS.getContent());

      StringWriter sw = new StringWriter();
      marshallerdssverify.marshal(vrequest, new StreamResult(sw));

      String sResult = dssAfirmaVerify.verify(sw.toString());

      vresponse = (ResponseBaseType) ((JAXBElement<?>) marshallerdssverify
          .unmarshal(new StreamSource(new StringReader(sResult)))).getValue();

    } catch (XmlMappingException e) {
      logger.error("Error al hacer marshall o unmarshall" + e.getMessage(), e);
      throw new AfirmaWSClientException("Error al hacer marshall o unmarshall " + e.getMessage(),
          e);

    } catch (Throwable t) {
      logger.error("Error al realizar la peticion a DSSAfirmaVerify " + t.getMessage(), t);
      throw new AfirmaWSClientException(
          "Error al realizar la peticion a DSSAfirmaVerify " + t.getMessage(), t);
    }

    return vresponse;

  }

  public InformacionFirmaAfirma obtenerInformacionFirma(
      RequestObtenerInformacionFirma requestObtenerInformacionFirma) throws AfirmaException {

    InformacionFirmaAfirma infoFirma = new InformacionFirmaAfirma();
    try {

      // Obtenemos el objeto que construirá la petición a enviar a los
      // WS de Afirma.
      DSSSignerProcessor processor =
          dssProcessorFactory.getDSSSignerProcessor(requestObtenerInformacionFirma.getFirma());
      if (processor == null) {
        throw new AfirmaException(CodigosError.COD_0001, CodigosError.MSJ_0001);
      }
      // else if (processor instanceof XMLEnvelopingDSSSignerProcessor) {
      // throw new AfirmaException(CodigosError.COD_0002,
      // CodigosError.MSJ_0002);
      // }

      // Hacemos la llamada a los WS de Afirma
      RequestValidarFirmaDSS requestValidarFirmaDSS = new RequestValidarFirmaDSS(
          new RequestConfigAfirma(requestObtenerInformacionFirma.getIdAplicacion(),
              requestObtenerInformacionFirma.getTruststore(),
              requestObtenerInformacionFirma.getPassTruststore()),
          requestObtenerInformacionFirma.getFirma(), processor,
          requestObtenerInformacionFirma.getContent());
      ResponseBaseType verifyResponse = validarFirmaDSS(requestValidarFirmaDSS);

      // Comprobamos que el formato de la firma es un formato válido
      if (verifyResponse.getResult().getResultMajor()
          .contentEquals(DSSResultConstantes.DSS_MAJOR_REQUESTERERROR)
          && verifyResponse.getResult().getResultMinor()
              .contentEquals(DSSResultConstantes.DSS_MINOR_INCORRECTFORMAT)) {
        logger.info("Los servicios de Afirma responden IncorrectFormat");
        throw new AfirmaException(CodigosError.COD_0003, CodigosError.MSJ_0003);

        // Admitimos la respuesta
        // "Firma válida, firma no válida, y warning. Para el resto lanzamos una excepción"

      } else if (!verifyResponse.getResult().getResultMajor()
          .contentEquals(DSSResultConstantes.DSS_MAJOR_VALIDSIGNATURE)
          && !verifyResponse.getResult().getResultMajor()
              .contentEquals(DSSResultConstantes.DSS_MAJOR_INVALIDSIGNATURE)
          && !verifyResponse.getResult().getResultMajor()
              .contentEquals(DSSResultConstantes.DSS_MAJOR_WARNING)) {
        StringBuffer sb = new StringBuffer("Codigos de respuesta de Afirma inesperados: ");
        sb.append(DSSUtil.getInfoResult(verifyResponse.getResult()));
        logger.info(sb.toString());
        throw new AfirmaException(CodigosError.COD_0004, CodigosError.MSJ_0004);

      }

      if (requestObtenerInformacionFirma.isObtenerFirmantes()) {
        // Obtenemos alguna información de los firmantes mediante las
        // librerías de Afirma.
        AOSigner signer = AOSignerFactory.getSigner(requestObtenerInformacionFirma.getFirma());
        if (signer == null) {
          throw new AfirmaException(CodigosError.COD_0001, CodigosError.MSJ_0001);
        }
        List<InformacionFirmante> firmantes = AOUtilExt.getTreeAsInformacionFirmantes(
            signer.getSignersStructure(requestObtenerInformacionFirma.getFirma(), true));

        // Obtenemos más información de los firmantes, mezclando la
        // información que ya teníamos con la que hemos obtenido de
        // los WS de Afirma
        // List<FirmaInfo> listaFirmaInfo =
        // DSSUtil.mergeInfoFirmantes(firmantes,
        // DSSUtil.getReadableCertificateInfoList(verifyResponse));
        List<FirmaInfoAfirma> listaFirmaInfo = DSSUtil.mergeInfoFirmantes(firmantes,
            DSSUtil.getReadableCertificateInfoAndTimeStampContentList(verifyResponse));
        /*
         * ListaFirmaInfo lista = new ListaFirmaInfo (); lista.setInformacionFirmas(listaFirmaInfo);
         * infoFirma.setFirmantes(lista);
         */
        infoFirma.setFirmantes(listaFirmaInfo);
      }

      if (requestObtenerInformacionFirma.isObtenerDatosFirmados()) {
        if (requestObtenerInformacionFirma.getContent() == null) {
          // Obtenemos el contenido firmado
          ContenidoFirmado contenidoFirmado =
              processor.getSignedData(verifyResponse, requestObtenerInformacionFirma.getFirma());

          if (contenidoFirmado.getBytesDocumento() != null) {
            ContenidoInfoAfirma documentoFirmado = new ContenidoInfoAfirma();
            documentoFirmado.setContenido(contenidoFirmado.getBytesDocumento());
            documentoFirmado.setTipoMIME(contenidoFirmado.getMimeDocumento());
            infoFirma.setDocumentoFirmado(documentoFirmado);
          } else if (contenidoFirmado.getHash() != null) {
            infoFirma.setHashFirmado(new String(contenidoFirmado.getHash()));
            infoFirma.setAlgoritmoHashFirmado(contenidoFirmado.getAlgoritmoHash());
          }
        } else {
          ContenidoInfoAfirma documentoFirmado = new ContenidoInfoAfirma();
          documentoFirmado.setContenido(requestObtenerInformacionFirma.getContent());
          documentoFirmado
              .setTipoMIME(MimeUtil.getMimeType(requestObtenerInformacionFirma.getContent()));
          infoFirma.setDocumentoFirmado(documentoFirmado);
        }
      }

      if (requestObtenerInformacionFirma.isObtenerTipoFirma()) {
        // Obtenemos el tipo de firma
        String tipoFirmaDSS = DSSUtil.getSignatureType(verifyResponse);

        String sufijo = "";
        if (processor instanceof XMLDetachedDSSSignerProcessor) {
          sufijo = " DETACHED";
        } else if (processor instanceof XMLEnvelopedDSSSignerProcessor) {
          sufijo = " ENVELOPED";
          // Aunque ahora no admitimos enveloping lo dejamos preparado
        } else if (processor instanceof XMLEnvelopingDSSSignerProcessor) {
          sufijo = " ENVELOPING";
        }

        String tipoFirma = ObtenerInformacionFirmaUtil.getTipoFirma(tipoFirmaDSS) + sufijo;
        // De momento el modo lo dejamos vacío.
        TipoDeFirmaAfirma tipoDeFirma = new TipoDeFirmaAfirma();
        tipoDeFirma.setTipoFirma(tipoFirma);
        infoFirma.setTipoDeFirma(tipoDeFirma);
      }

      infoFirma.setEsFirma(true);

    } catch (ContentNotExtractedException e) {
      throw new AfirmaException(CodigosError.COD_0011, CodigosError.MSJ_0011);
    } catch (DSSSignerProcessorException e) {
      throw new AfirmaException(CodigosError.COD_0009, CodigosError.MSJ_0009);
    } catch (AfirmaWSClientException e) {
      logger.error("Error en la llamada a @firma", e);
      throw new AfirmaException(CodigosError.COD_0010, CodigosError.MSJ_0010);
    } catch (AfirmaException e) {
      throw e;
    } catch (Throwable t) {
      logger.error("Error inesperado al intentar obtener información de la firma, ", t);
      throw new AfirmaException(CodigosError.COD_0000, CodigosError.MSJ_0000);
    }

    return infoFirma;
  }

  /**
   * Recibe una firma y una configuraci�n de ampliaci�n y devuelve la firma con el upgrade apropiado
   */

  public ResultadoAmpliarFirmaAfirma ampliarFirma(RequestAmpliarFirmaDSS requestAmpliarFirmaDSS)
      throws AfirmaException {

    ResultadoAmpliarFirmaAfirma resultadoAmpliarFirma = new ResultadoAmpliarFirmaAfirma();

    try {

      // Si la firma es PADES y se quiere a�adir sello de tiempo
      // devolvemos un error //
      if (esPADES(requestAmpliarFirmaDSS.getSign()) && AfirmaConstantes.UPGRADE_TIMESTAMP
          .equals(requestAmpliarFirmaDSS.getConfiguracion().getFormatoAmpliacion())) {
        requestAmpliarFirmaDSS.getConfiguracion()
            .setFormatoAmpliacion(AfirmaConstantes.UPGRADE_TIMESTAMP_PDF);
      }

      // Obtenemos el objeto que construir� la petici�n a enviar a los
      // WS de Afirma.
      DSSSignerProcessor processor =
          dssProcessorFactory.getDSSSignerProcessor(requestAmpliarFirmaDSS.getSign());
      if (processor == null) {
        throw new AfirmaException(CodigosError.COD_0001, CodigosError.MSJ_0001);
      } else if (processor instanceof XMLEnvelopingDSSSignerProcessor) {
        throw new AfirmaException(CodigosError.COD_0002, CodigosError.MSJ_0002);
      }

      // Hacemos la llamada a los WS de Afirma
      requestAmpliarFirmaDSS.setProcessor(processor);
      ResponseBaseType verifyResponse = ampliarFirmaDSS(requestAmpliarFirmaDSS);

      // Comprobamos si se ha realizado la operaci�n correctamente
      if (verifyResponse.getResult().getResultMajor()
          .contentEquals(DSSResultConstantes.DSS_MAJOR_REQUESTERERROR)
          && verifyResponse.getResult().getResultMinor()
              .contentEquals(DSSResultConstantes.DSS_MINOR_INCOMPLETEUPGRADEOP)) {
        logger.info("Los servicios de Afirma responden IncompleteUpgradeOperation para operaci�n: "
            + requestAmpliarFirmaDSS.getConfiguracion().getFormatoAmpliacion());
        throw new AfirmaException(CodigosError.COD_0005, CodigosError.MSJ_0005);

        // Comprobamos si el tipo de amplicaci�n introducido es
        // correcto
      } else if (verifyResponse.getResult().getResultMajor()
          .contentEquals(DSSResultConstantes.DSS_MAJOR_REQUESTERERROR)
          && verifyResponse.getResult().getResultMinor()
              .contentEquals(DSSResultConstantes.DSS_MINOR_INCORRECTRETURNUPDATEDSIGNTYPE)) {
        logger.info(
            "Los servicios de Afirma responden IncompleteReturnUpdatedSignatureType para operaci�n: "
                + requestAmpliarFirmaDSS.getConfiguracion().getFormatoAmpliacion());
        throw new AfirmaException(CodigosError.COD_0006, CodigosError.MSJ_0006);

        // Comprobamos si el tipo de amplicaci�n ha sido introducido
      } else if (verifyResponse.getResult().getResultMajor()
          .contentEquals(DSSResultConstantes.DSS_MAJOR_REQUESTERERROR)
          && verifyResponse.getResult().getResultMinor()
              .contentEquals(DSSResultConstantes.DSS_MINOR_UPDATEDSIGNTYPENOTPROVIDED)) {
        logger.info(
            "Los servicios de Afirma responden UpdatedSignatureTypeNotProvided para operaci�n: "
                + requestAmpliarFirmaDSS.getConfiguracion().getFormatoAmpliacion());
        throw new AfirmaException(CodigosError.COD_0007, CodigosError.MSJ_0007);

        // Admitimos la respuesta "�xito",
        // "Pendiente de periodo de gracia" y "warning". Para el resto
        // lanzamos una excepci�n"
      } else if (!verifyResponse.getResult().getResultMajor()
          .contentEquals(DSSResultConstantes.DSS_MAJOR_SUCCESS)
          && !verifyResponse.getResult().getResultMajor()
              .contentEquals(DSSResultConstantes.DSS_MAJOR_PENDING)
          && !verifyResponse.getResult().getResultMajor()
              .contentEquals(DSSResultConstantes.DSS_MAJOR_WARNING)) {
        StringBuffer sb = new StringBuffer("Codigos de respuesta de Afirma inesperados: ");
        sb.append(DSSUtil.getInfoResult(verifyResponse.getResult()));
        logger.info(sb.toString());
        throw new AfirmaException(CodigosError.COD_0004, CodigosError.MSJ_0004);
      }

      // Recogemos la firma ampliada //
      byte[] firmaAmpliada = processor.getUpgradedSignature(verifyResponse);
      resultadoAmpliarFirma.setFirma(firmaAmpliada);

    } catch (DSSSignerProcessorException e) {
      throw new AfirmaException(CodigosError.COD_0009, CodigosError.MSJ_0009);
    } catch (AfirmaWSClientException e) {
      logger.error("Error en la llamada a @firma", e);
      throw new AfirmaException(CodigosError.COD_0010, CodigosError.MSJ_0010);
    } catch (AfirmaException e) {
      throw e;
    } catch (Throwable t) {
      logger.error("Error inesperado al intentar ampliar la firma, ", t);
      throw new AfirmaException(CodigosError.COD_0000, CodigosError.MSJ_0000);
    }

    return resultadoAmpliarFirma;
  }

  /**
   * M�todo que comprueba si una firma es en formato PADES
   * 
   * @throws IOException
   */
  private boolean esPADES(byte[] firma) throws IOException {
    boolean esPADES = false;
    AOSigner signer = AOSignerFactory.getSigner(firma);
    if (signer instanceof AOPDFSigner) {
      esPADES = true;
    }
    return esPADES;
  }

  public Firma getFirma() {
    return firma;
  }

  public void setFirma(Firma firma) {
    this.firma = firma;
  }

  public Jaxb2Marshaller getMarshallerfirma() {
    return marshallerfirma;
  }

  public void setMarshallerfirma(Jaxb2Marshaller marshallerfirma) {
    this.marshallerfirma = marshallerfirma;
  }

  public Jaxb2Marshaller getMarshallercert() {
    return marshallercert;
  }

  public void setMarshallercert(Jaxb2Marshaller marshallercert) {
    this.marshallercert = marshallercert;
  }

  public Validacion getValCert() {
    return valCert;
  }

  public void setValCert(Validacion valCert) {
    this.valCert = valCert;
  }

  public Jaxb2Marshaller getMarshallerdssverify() {
    return marshallerdssverify;
  }

  public void setMarshallerdssverify(Jaxb2Marshaller marshallerdssverify) {
    this.marshallerdssverify = marshallerdssverify;
  }

  public DSSSignature getDssAfirmaVerify() {
    return dssAfirmaVerify;
  }

  public void setDssAfirmaVerify(DSSSignature dssAfirmaVerify) {
    this.dssAfirmaVerify = dssAfirmaVerify;
  }

  public DSSSignerProcessorFactory getDssProcessorFactory() {
    return dssProcessorFactory;
  }

  public void setDssProcessorFactory(DSSSignerProcessorFactory dssProcessorFactory) {
    this.dssProcessorFactory = dssProcessorFactory;
  }

  public LoggingOutInterceptor getLogOut() {
    return logOut;
  }

  public void setLogOut(LoggingOutInterceptor logOut) {
    this.logOut = logOut;
  }

  public LoggingInInterceptor getLogIn() {
    return logIn;
  }

  public void setLogIn(LoggingInInterceptor logIn) {
    this.logIn = logIn;
  }

  public String obtenerTipoFirmaDss(RequestObtenerInformacionFirma requestObtenerInformacionFirma)
      throws AfirmaException {
    String tipoFirmaDSS = "";

    try {
      // Obtenemos el objeto que construirá la petición a enviar a los
      // WS de Afirma.
      DSSSignerProcessor processor =
          dssProcessorFactory.getDSSSignerProcessor(requestObtenerInformacionFirma.getFirma());
      if (processor == null) {
        throw new AfirmaException(CodigosError.COD_0001, CodigosError.MSJ_0001);
      } else if (processor instanceof XMLEnvelopingDSSSignerProcessor) {
        throw new AfirmaException(CodigosError.COD_0002, CodigosError.MSJ_0002);
      }

      // Hacemos la llamada a los WS de Afirma
      RequestValidarFirmaDSS requestValidarFirmaDSS = new RequestValidarFirmaDSS(
          new RequestConfigAfirma(requestObtenerInformacionFirma.getIdAplicacion(),
              requestObtenerInformacionFirma.getTruststore(),
              requestObtenerInformacionFirma.getPassTruststore()),
          requestObtenerInformacionFirma.getFirma(), processor,
          requestObtenerInformacionFirma.getContent());
      ResponseBaseType verifyResponse = validarFirmaDSS(requestValidarFirmaDSS);

      // Comprobamos que el formato de la firma es un formato válido
      if (verifyResponse.getResult().getResultMajor()
          .contentEquals(DSSResultConstantes.DSS_MAJOR_REQUESTERERROR)
          && verifyResponse.getResult().getResultMinor()
              .contentEquals(DSSResultConstantes.DSS_MINOR_INCORRECTFORMAT)) {
        logger.info("Los servicios de Afirma responden IncorrectFormat");
        throw new AfirmaException(CodigosError.COD_0003, CodigosError.MSJ_0003);

        // Admitimos la respuesta
        // "Firma válida, firma no válida, y warning. Para el resto lanzamos una excepción"

      } else if (!verifyResponse.getResult().getResultMajor()
          .contentEquals(DSSResultConstantes.DSS_MAJOR_VALIDSIGNATURE)
          && !verifyResponse.getResult().getResultMajor()
              .contentEquals(DSSResultConstantes.DSS_MAJOR_INVALIDSIGNATURE)
          && !verifyResponse.getResult().getResultMajor()
              .contentEquals(DSSResultConstantes.DSS_MAJOR_WARNING)) {
        StringBuffer sb = new StringBuffer("Codigos de respuesta de Afirma inesperados: ");
        sb.append(DSSUtil.getInfoResult(verifyResponse.getResult()));
        logger.info(sb.toString());
        throw new AfirmaException(CodigosError.COD_0004, CodigosError.MSJ_0004);

      }

      tipoFirmaDSS = DSSUtil.getSignatureType(verifyResponse);
    } catch (DSSSignerProcessorException e) {
      logger.error("Error en la llamada a @firma", e);
      throw new AfirmaException(CodigosError.COD_0009, CodigosError.MSJ_0009);
    } catch (AfirmaWSClientException e) {
      logger.error("Error en la llamada a @firma", e);
      throw new AfirmaException(CodigosError.COD_0010, CodigosError.MSJ_0010);
    }
    return tipoFirmaDSS;
  }

  public ResultadoValidacionFirmaFormatoAAfirma validarFirmaFormatoA(
      RequestValidarFirma requestValidarFirma) {
    setupTimeouts(ClientProxy.getClient(firma));
    disableChunking(ClientProxy.getClient(firma));

    System.setProperty("javax.net.ssl.trustStore", requestValidarFirma.getTruststore());
    System.setProperty("javax.net.ssl.trustStorePassword", requestValidarFirma.getPassTruststore());

    ResultadoValidacionFirmaFormatoAAfirma resultado = new ResultadoValidacionFirmaFormatoAAfirma();

    String peticion = crearPeticionValidar(requestValidarFirma);
    // logger.debug("Petici�n a Afirma validar firma:\n" + peticion);
    String respuesta = firma.validarFirma(peticion);
    // logger.debug("Respuesta a Afirma de validar firma:\n" + respuesta);

    MensajeSalida msg = procesarSalidaValidar(respuesta);

    String detalle = "";
    boolean firmaCorrecta = true;
    boolean certCorrectos = true;

    // Si la respuesta no es nula, se ha podido validar la firma y
    // comprobamos el resultado.
    if (msg.getRespuesta().getRespuesta() != null) {

      List<Object> lista = msg.getRespuesta().getRespuesta().getDescripcion().getContent();
      for (Object obj : lista) {
        ValidacionFirmaElectronica val = (ValidacionFirmaElectronica) obj;

        // Firma correcta, validamos certificados.
        if (msg.getRespuesta().getRespuesta().isEstado()) {

          List<InformacionAdicional.Firmante> firmantes =
              val.getInformacionAdicional().getFirmante();
          Iterator<InformacionAdicional.Firmante> itFirmantes = firmantes.iterator();

          while (itFirmantes.hasNext() && certCorrectos) {
            InformacionAdicional.Firmante firmante = itFirmantes.next();
            RequestValidarCertificado requestValidarCertificado = new RequestValidarCertificado(
                new RequestConfigAfirma(requestValidarFirma.getIdAplicacion(),
                    requestValidarFirma.getTruststore(), requestValidarFirma.getPassTruststore()),
                Base64.encodeBase64String(firmante.getCertificadoTSA()), null, 3, true);

            logger.debug(
                "Certificado TSA:" + Base64.encodeBase64String(firmante.getCertificadoTSA()));
            Respuesta valCertTsa = this.crearPeticionValidarCertificado(requestValidarCertificado);
            // Validacion de certificado correcta
            if (!"0".equalsIgnoreCase(
                valCertTsa.getResultadoProcesamiento().getResultadoValidacion().getResultado())) {
              certCorrectos = false;
              detalle += construyeDetalleRespuesta(
                  valCertTsa.getResultadoProcesamiento().getResultadoValidacion().getDescripcion());
            } else {
              List<Campo> campos =
                  valCertTsa.getResultadoProcesamiento().getInfoCertificado().getCampo();
              for (Campo campo : campos) {
                if (campo.getIdCampo().equals("validoHasta")) {
                  logger.debug("Fecha Validez TSA:" + campo.getValorCampo());
                  resultado.setFechaValidezCertificadoTSA(campo.getValorCampo());
                }
              }
            }
          }

          // Firma no correcta
        } else {
          firmaCorrecta = false;
          detalle += construyeDetalleRespuesta(val);
        }

      }

      // La respuesta es nula porque no se ha podido validar la firma.
    } else {
      firmaCorrecta = false;
      detalle += construyeDetalleRespuesta(msg.getRespuesta().getExcepcion());
    }

    if (firmaCorrecta && certCorrectos) {
      detalle = "La firma es v�lida";
    }

    resultado.setEstado(firmaCorrecta && certCorrectos);
    resultado.setDetalle(detalle);

    return resultado;

  }

  public long getConnectionTimeOut() {
    return connectionTimeOut;
  }

  public void setConnectionTimeOut(long connectionTimeOut) {
    this.connectionTimeOut = connectionTimeOut;
  }

  public long getReceiveTimeOut() {
    return receiveTimeOut;
  }

  public void setReceiveTimeOut(long receiveTimeOut) {
    this.receiveTimeOut = receiveTimeOut;
  }

}
