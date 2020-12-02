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

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the es.mpt.dsic.eeutil.inside.admin.model package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _EliminarUnidadUsuarioResponse_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "eliminarUnidadUsuarioResponse");
  private final static QName _AltaCredencialesRemisionNubeResponse_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
          "altaCredencialesRemisionNubeResponse");
  private final static QName _GetUnidadesAplicacionResponse_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "getUnidadesAplicacionResponse");
  private final static QName _GetUnidadesUsuario_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "getUnidadesUsuario");
  private final static QName _AltaSolicitudAccesoExpAppUrl_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "altaSolicitudAccesoExpAppUrl");
  private final static QName _AltaUsuarioResponse_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "altaUsuarioResponse");
  private final static QName _ListaNumeroProcedimiento_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "listaNumeroProcedimiento");
  private final static QName _ActualizarCredencialesEeetuilApp_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
          "actualizarCredencialesEeetuilApp");
  private final static QName _ErrorInside_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService/types/error", "errorInside");
  private final static QName _AltaUsuario_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "altaUsuario");
  private final static QName _ListaUsuariosPaginadoResponse_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "listaUsuariosPaginadoResponse");
  private final static QName _ComprobarDirectorioTemporal_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "comprobarDirectorioTemporal");
  private final static QName _AltaEstructuraCarpetaResponse_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "altaEstructuraCarpetaResponse");
  private final static QName _BajaAplicacionResponse_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "bajaAplicacionResponse");
  private final static QName _InfAdicionalAltaApp_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "infAdicionalAltaApp");
  private final static QName _AltaSolicitudAccesoExpAppUrlResponse_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
          "altaSolicitudAccesoExpAppUrlResponse");
  private final static QName _AltaCredencialesRemisionNube_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "altaCredencialesRemisionNube");
  private final static QName _AltaUnidadUsuario_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "altaUnidadUsuario");
  private final static QName _ListaUsuariosResponse_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "listaUsuariosResponse");
  private final static QName _ListaEstructuraCarpetaResponse_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "listaEstructuraCarpetaResponse");
  private final static QName _AltaAplicacionResponse_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "altaAplicacionResponse");
  private final static QName _GetUnidadesAplicacion_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "getUnidadesAplicacion");
  private final static QName _LimpiarDirectorioTemporal_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "limpiarDirectorioTemporal");
  private final static QName _ListaUsuariosPaginado_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "listaUsuariosPaginado");
  private final static QName _BajaUsuario_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "bajaUsuario");
  private final static QName _ComprobarDirectorioTemporalResponse_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
          "comprobarDirectorioTemporalResponse");
  private final static QName _LanzaJobComunicacionTokenExpediente_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
          "lanzaJobComunicacionTokenExpediente");
  private final static QName _ActualizarCredencialesRemisionNubeResponse_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
          "actualizarCredencialesRemisionNubeResponse");
  private final static QName _ListaAplicacionesResponse_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "listaAplicacionesResponse");
  private final static QName _ListaUsuarios_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "listaUsuarios");
  private final static QName _ListaAplicaciones_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "listaAplicaciones");
  private final static QName _DeleteEstructuraCarpetaResponse_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "deleteEstructuraCarpetaResponse");
  private final static QName _GetUnidadesUsuarioResponse_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "getUnidadesUsuarioResponse");
  private final static QName _ActualizarCredencialesEeetuilAppResponse_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
          "actualizarCredencialesEeetuilAppResponse");
  private final static QName _ListaEstructuraCarpeta_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "listaEstructuraCarpeta");
  private final static QName _EliminarUnidadUsuario_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "eliminarUnidadUsuario");
  private final static QName _AltaUnidadAplicacion_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "altaUnidadAplicacion");
  private final static QName _CrearActualizarUnidadAplicacionEeutilResponse_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
          "crearActualizarUnidadAplicacionEeutilResponse");
  private final static QName _GetUnidadAplicacionEeutilResponse_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
          "getUnidadAplicacionEeutilResponse");
  private final static QName _LimpiarDirectorioTemporalResponse_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
          "limpiarDirectorioTemporalResponse");
  private final static QName _ListaNumeroProcedimientoResponse_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
          "listaNumeroProcedimientoResponse");
  private final static QName _AltaNumeroProcedimientoResponse_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "altaNumeroProcedimientoResponse");
  private final static QName _BajaAplicacion_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "bajaAplicacion");
  private final static QName _LanzaJobComunicacionTokenExpedienteResponse_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
          "lanzaJobComunicacionTokenExpedienteResponse");
  private final static QName _ActualizarUnidadesDir3Response_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "actualizarUnidadesDir3Response");
  private final static QName _GetUnidadAplicacionEeutil_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "getUnidadAplicacionEeutil");
  private final static QName _CrearActualizarUnidadAplicacionEeutil_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
          "crearActualizarUnidadAplicacionEeutil");
  private final static QName _CrearActualizarSerialNumberCertificado_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
          "crearActualizarSerialNumberCertificado");
  private final static QName _AltaAplicacion_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "altaAplicacion");
  private final static QName _Credential_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService/types/credential", "credential");
  private final static QName _DeleteEstructuraCarpeta_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "deleteEstructuraCarpeta");
  private final static QName _ActualizarSolicitudAccesoExpAppUrl_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
          "actualizarSolicitudAccesoExpAppUrl");
  private final static QName _RespuestaEnvioJusticiaResponse_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "respuestaEnvioJusticiaResponse");
  private final static QName _EliminarUnidadAplicacionResponse_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
          "eliminarUnidadAplicacionResponse");
  private final static QName _ActualizarSolicitudAccesoExpAppUrlResponse_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
          "actualizarSolicitudAccesoExpAppUrlResponse");
  private final static QName _AltaEstructuraCarpeta_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "altaEstructuraCarpeta");
  private final static QName _RespuestaEnvioJusticia_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "respuestaEnvioJusticia");
  private final static QName _CrearActualizarSerialNumberCertificadoResponse_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
          "crearActualizarSerialNumberCertificadoResponse");
  private final static QName _BajaUsuarioResponse_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "bajaUsuarioResponse");
  private final static QName _EliminarUnidadAplicacion_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "eliminarUnidadAplicacion");
  private final static QName _AltaNumeroProcedimiento_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "altaNumeroProcedimiento");
  private final static QName _ActualizarCredencialesRemisionNube_QNAME =
      new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
          "actualizarCredencialesRemisionNube");
  private final static QName _AltaUnidadUsuarioResponse_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "altaUnidadUsuarioResponse");
  private final static QName _AltaUnidadAplicacionResponse_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "altaUnidadAplicacionResponse");
  private final static QName _InfAdicionalAltaAppResponse_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "infAdicionalAltaAppResponse");
  private final static QName _ActualizarUnidadesDir3_QNAME = new QName(
      "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "actualizarUnidadesDir3");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: es.mpt.dsic.eeutil.inside.admin.model
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link WSCredentialInside }
   * 
   */
  public WSCredentialInside createWSCredentialInside() {
    return new WSCredentialInside();
  }

  /**
   * Create an instance of {@link WSErrorInside }
   * 
   */
  public WSErrorInside createWSErrorInside() {
    return new WSErrorInside();
  }

  /**
   * Create an instance of {@link Usuario }
   * 
   */
  public Usuario createUsuario() {
    return new Usuario();
  }

  /**
   * Create an instance of {@link UsuarioResultadoBusqueda }
   * 
   */
  public UsuarioResultadoBusqueda createUsuarioResultadoBusqueda() {
    return new UsuarioResultadoBusqueda();
  }

  /**
   * Create an instance of {@link BajaAplicacionResponse }
   * 
   */
  public BajaAplicacionResponse createBajaAplicacionResponse() {
    return new BajaAplicacionResponse();
  }

  /**
   * Create an instance of {@link AltaCredencialesRemisionNube }
   * 
   */
  public AltaCredencialesRemisionNube createAltaCredencialesRemisionNube() {
    return new AltaCredencialesRemisionNube();
  }

  /**
   * Create an instance of {@link InfAdicionalAltaApp }
   * 
   */
  public InfAdicionalAltaApp createInfAdicionalAltaApp() {
    return new InfAdicionalAltaApp();
  }

  /**
   * Create an instance of {@link AltaSolicitudAccesoExpAppUrlResponse }
   * 
   */
  public AltaSolicitudAccesoExpAppUrlResponse createAltaSolicitudAccesoExpAppUrlResponse() {
    return new AltaSolicitudAccesoExpAppUrlResponse();
  }

  /**
   * Create an instance of {@link AltaUnidadUsuario }
   * 
   */
  public AltaUnidadUsuario createAltaUnidadUsuario() {
    return new AltaUnidadUsuario();
  }

  /**
   * Create an instance of {@link ListaUsuariosResponse }
   * 
   */
  public ListaUsuariosResponse createListaUsuariosResponse() {
    return new ListaUsuariosResponse();
  }

  /**
   * Create an instance of {@link AltaAplicacionResponse }
   * 
   */
  public AltaAplicacionResponse createAltaAplicacionResponse() {
    return new AltaAplicacionResponse();
  }

  /**
   * Create an instance of {@link ListaEstructuraCarpetaResponse }
   * 
   */
  public ListaEstructuraCarpetaResponse createListaEstructuraCarpetaResponse() {
    return new ListaEstructuraCarpetaResponse();
  }

  /**
   * Create an instance of {@link EliminarUnidadUsuarioResponse }
   * 
   */
  public EliminarUnidadUsuarioResponse createEliminarUnidadUsuarioResponse() {
    return new EliminarUnidadUsuarioResponse();
  }

  /**
   * Create an instance of {@link GetUnidadesAplicacionResponse }
   * 
   */
  public GetUnidadesAplicacionResponse createGetUnidadesAplicacionResponse() {
    return new GetUnidadesAplicacionResponse();
  }

  /**
   * Create an instance of {@link GetUnidadesUsuario }
   * 
   */
  public GetUnidadesUsuario createGetUnidadesUsuario() {
    return new GetUnidadesUsuario();
  }

  /**
   * Create an instance of {@link AltaCredencialesRemisionNubeResponse }
   * 
   */
  public AltaCredencialesRemisionNubeResponse createAltaCredencialesRemisionNubeResponse() {
    return new AltaCredencialesRemisionNubeResponse();
  }

  /**
   * Create an instance of {@link AltaSolicitudAccesoExpAppUrl }
   * 
   */
  public AltaSolicitudAccesoExpAppUrl createAltaSolicitudAccesoExpAppUrl() {
    return new AltaSolicitudAccesoExpAppUrl();
  }

  /**
   * Create an instance of {@link AltaUsuarioResponse }
   * 
   */
  public AltaUsuarioResponse createAltaUsuarioResponse() {
    return new AltaUsuarioResponse();
  }

  /**
   * Create an instance of {@link ListaNumeroProcedimiento }
   * 
   */
  public ListaNumeroProcedimiento createListaNumeroProcedimiento() {
    return new ListaNumeroProcedimiento();
  }

  /**
   * Create an instance of {@link ActualizarCredencialesEeetuilApp }
   * 
   */
  public ActualizarCredencialesEeetuilApp createActualizarCredencialesEeetuilApp() {
    return new ActualizarCredencialesEeetuilApp();
  }

  /**
   * Create an instance of {@link AltaUsuario }
   * 
   */
  public AltaUsuario createAltaUsuario() {
    return new AltaUsuario();
  }

  /**
   * Create an instance of {@link AltaEstructuraCarpetaResponse }
   * 
   */
  public AltaEstructuraCarpetaResponse createAltaEstructuraCarpetaResponse() {
    return new AltaEstructuraCarpetaResponse();
  }

  /**
   * Create an instance of {@link ComprobarDirectorioTemporal }
   * 
   */
  public ComprobarDirectorioTemporal createComprobarDirectorioTemporal() {
    return new ComprobarDirectorioTemporal();
  }

  /**
   * Create an instance of {@link ListaUsuariosPaginadoResponse }
   * 
   */
  public ListaUsuariosPaginadoResponse createListaUsuariosPaginadoResponse() {
    return new ListaUsuariosPaginadoResponse();
  }

  /**
   * Create an instance of {@link ComprobarDirectorioTemporalResponse }
   * 
   */
  public ComprobarDirectorioTemporalResponse createComprobarDirectorioTemporalResponse() {
    return new ComprobarDirectorioTemporalResponse();
  }

  /**
   * Create an instance of {@link BajaUsuario }
   * 
   */
  public BajaUsuario createBajaUsuario() {
    return new BajaUsuario();
  }

  /**
   * Create an instance of {@link ListaUsuariosPaginado }
   * 
   */
  public ListaUsuariosPaginado createListaUsuariosPaginado() {
    return new ListaUsuariosPaginado();
  }

  /**
   * Create an instance of {@link LanzaJobComunicacionTokenExpediente }
   * 
   */
  public LanzaJobComunicacionTokenExpediente createLanzaJobComunicacionTokenExpediente() {
    return new LanzaJobComunicacionTokenExpediente();
  }

  /**
   * Create an instance of {@link ActualizarCredencialesRemisionNubeResponse }
   * 
   */
  public ActualizarCredencialesRemisionNubeResponse createActualizarCredencialesRemisionNubeResponse() {
    return new ActualizarCredencialesRemisionNubeResponse();
  }

  /**
   * Create an instance of {@link ListaAplicacionesResponse }
   * 
   */
  public ListaAplicacionesResponse createListaAplicacionesResponse() {
    return new ListaAplicacionesResponse();
  }

  /**
   * Create an instance of {@link ListaAplicaciones }
   * 
   */
  public ListaAplicaciones createListaAplicaciones() {
    return new ListaAplicaciones();
  }

  /**
   * Create an instance of {@link ListaUsuarios }
   * 
   */
  public ListaUsuarios createListaUsuarios() {
    return new ListaUsuarios();
  }

  /**
   * Create an instance of {@link DeleteEstructuraCarpetaResponse }
   * 
   */
  public DeleteEstructuraCarpetaResponse createDeleteEstructuraCarpetaResponse() {
    return new DeleteEstructuraCarpetaResponse();
  }

  /**
   * Create an instance of {@link GetUnidadesUsuarioResponse }
   * 
   */
  public GetUnidadesUsuarioResponse createGetUnidadesUsuarioResponse() {
    return new GetUnidadesUsuarioResponse();
  }

  /**
   * Create an instance of {@link ActualizarCredencialesEeetuilAppResponse }
   * 
   */
  public ActualizarCredencialesEeetuilAppResponse createActualizarCredencialesEeetuilAppResponse() {
    return new ActualizarCredencialesEeetuilAppResponse();
  }

  /**
   * Create an instance of {@link ListaEstructuraCarpeta }
   * 
   */
  public ListaEstructuraCarpeta createListaEstructuraCarpeta() {
    return new ListaEstructuraCarpeta();
  }

  /**
   * Create an instance of {@link EliminarUnidadUsuario }
   * 
   */
  public EliminarUnidadUsuario createEliminarUnidadUsuario() {
    return new EliminarUnidadUsuario();
  }

  /**
   * Create an instance of {@link GetUnidadesAplicacion }
   * 
   */
  public GetUnidadesAplicacion createGetUnidadesAplicacion() {
    return new GetUnidadesAplicacion();
  }

  /**
   * Create an instance of {@link LimpiarDirectorioTemporal }
   * 
   */
  public LimpiarDirectorioTemporal createLimpiarDirectorioTemporal() {
    return new LimpiarDirectorioTemporal();
  }

  /**
   * Create an instance of {@link CrearActualizarUnidadAplicacionEeutil }
   * 
   */
  public CrearActualizarUnidadAplicacionEeutil createCrearActualizarUnidadAplicacionEeutil() {
    return new CrearActualizarUnidadAplicacionEeutil();
  }

  /**
   * Create an instance of {@link CrearActualizarSerialNumberCertificado }
   * 
   */
  public CrearActualizarSerialNumberCertificado createCrearActualizarSerialNumberCertificado() {
    return new CrearActualizarSerialNumberCertificado();
  }

  /**
   * Create an instance of {@link AltaAplicacion }
   * 
   */
  public AltaAplicacion createAltaAplicacion() {
    return new AltaAplicacion();
  }

  /**
   * Create an instance of {@link ActualizarSolicitudAccesoExpAppUrl }
   * 
   */
  public ActualizarSolicitudAccesoExpAppUrl createActualizarSolicitudAccesoExpAppUrl() {
    return new ActualizarSolicitudAccesoExpAppUrl();
  }

  /**
   * Create an instance of {@link DeleteEstructuraCarpeta }
   * 
   */
  public DeleteEstructuraCarpeta createDeleteEstructuraCarpeta() {
    return new DeleteEstructuraCarpeta();
  }

  /**
   * Create an instance of {@link RespuestaEnvioJusticiaResponse }
   * 
   */
  public RespuestaEnvioJusticiaResponse createRespuestaEnvioJusticiaResponse() {
    return new RespuestaEnvioJusticiaResponse();
  }

  /**
   * Create an instance of {@link EliminarUnidadAplicacionResponse }
   * 
   */
  public EliminarUnidadAplicacionResponse createEliminarUnidadAplicacionResponse() {
    return new EliminarUnidadAplicacionResponse();
  }

  /**
   * Create an instance of {@link CrearActualizarUnidadAplicacionEeutilResponse }
   * 
   */
  public CrearActualizarUnidadAplicacionEeutilResponse createCrearActualizarUnidadAplicacionEeutilResponse() {
    return new CrearActualizarUnidadAplicacionEeutilResponse();
  }

  /**
   * Create an instance of {@link GetUnidadAplicacionEeutilResponse }
   * 
   */
  public GetUnidadAplicacionEeutilResponse createGetUnidadAplicacionEeutilResponse() {
    return new GetUnidadAplicacionEeutilResponse();
  }

  /**
   * Create an instance of {@link LimpiarDirectorioTemporalResponse }
   * 
   */
  public LimpiarDirectorioTemporalResponse createLimpiarDirectorioTemporalResponse() {
    return new LimpiarDirectorioTemporalResponse();
  }

  /**
   * Create an instance of {@link ListaNumeroProcedimientoResponse }
   * 
   */
  public ListaNumeroProcedimientoResponse createListaNumeroProcedimientoResponse() {
    return new ListaNumeroProcedimientoResponse();
  }

  /**
   * Create an instance of {@link AltaNumeroProcedimientoResponse }
   * 
   */
  public AltaNumeroProcedimientoResponse createAltaNumeroProcedimientoResponse() {
    return new AltaNumeroProcedimientoResponse();
  }

  /**
   * Create an instance of {@link AltaUnidadAplicacion }
   * 
   */
  public AltaUnidadAplicacion createAltaUnidadAplicacion() {
    return new AltaUnidadAplicacion();
  }

  /**
   * Create an instance of {@link BajaAplicacion }
   * 
   */
  public BajaAplicacion createBajaAplicacion() {
    return new BajaAplicacion();
  }

  /**
   * Create an instance of {@link LanzaJobComunicacionTokenExpedienteResponse }
   * 
   */
  public LanzaJobComunicacionTokenExpedienteResponse createLanzaJobComunicacionTokenExpedienteResponse() {
    return new LanzaJobComunicacionTokenExpedienteResponse();
  }

  /**
   * Create an instance of {@link GetUnidadAplicacionEeutil }
   * 
   */
  public GetUnidadAplicacionEeutil createGetUnidadAplicacionEeutil() {
    return new GetUnidadAplicacionEeutil();
  }

  /**
   * Create an instance of {@link ActualizarUnidadesDir3Response }
   * 
   */
  public ActualizarUnidadesDir3Response createActualizarUnidadesDir3Response() {
    return new ActualizarUnidadesDir3Response();
  }

  /**
   * Create an instance of {@link AltaUnidadUsuarioResponse }
   * 
   */
  public AltaUnidadUsuarioResponse createAltaUnidadUsuarioResponse() {
    return new AltaUnidadUsuarioResponse();
  }

  /**
   * Create an instance of {@link AltaUnidadAplicacionResponse }
   * 
   */
  public AltaUnidadAplicacionResponse createAltaUnidadAplicacionResponse() {
    return new AltaUnidadAplicacionResponse();
  }

  /**
   * Create an instance of {@link InfAdicionalAltaAppResponse }
   * 
   */
  public InfAdicionalAltaAppResponse createInfAdicionalAltaAppResponse() {
    return new InfAdicionalAltaAppResponse();
  }

  /**
   * Create an instance of {@link ActualizarUnidadesDir3 }
   * 
   */
  public ActualizarUnidadesDir3 createActualizarUnidadesDir3() {
    return new ActualizarUnidadesDir3();
  }

  /**
   * Create an instance of {@link ActualizarSolicitudAccesoExpAppUrlResponse }
   * 
   */
  public ActualizarSolicitudAccesoExpAppUrlResponse createActualizarSolicitudAccesoExpAppUrlResponse() {
    return new ActualizarSolicitudAccesoExpAppUrlResponse();
  }

  /**
   * Create an instance of {@link CrearActualizarSerialNumberCertificadoResponse }
   * 
   */
  public CrearActualizarSerialNumberCertificadoResponse createCrearActualizarSerialNumberCertificadoResponse() {
    return new CrearActualizarSerialNumberCertificadoResponse();
  }

  /**
   * Create an instance of {@link RespuestaEnvioJusticia }
   * 
   */
  public RespuestaEnvioJusticia createRespuestaEnvioJusticia() {
    return new RespuestaEnvioJusticia();
  }

  /**
   * Create an instance of {@link AltaEstructuraCarpeta }
   * 
   */
  public AltaEstructuraCarpeta createAltaEstructuraCarpeta() {
    return new AltaEstructuraCarpeta();
  }

  /**
   * Create an instance of {@link BajaUsuarioResponse }
   * 
   */
  public BajaUsuarioResponse createBajaUsuarioResponse() {
    return new BajaUsuarioResponse();
  }

  /**
   * Create an instance of {@link EliminarUnidadAplicacion }
   * 
   */
  public EliminarUnidadAplicacion createEliminarUnidadAplicacion() {
    return new EliminarUnidadAplicacion();
  }

  /**
   * Create an instance of {@link AltaNumeroProcedimiento }
   * 
   */
  public AltaNumeroProcedimiento createAltaNumeroProcedimiento() {
    return new AltaNumeroProcedimiento();
  }

  /**
   * Create an instance of {@link ActualizarCredencialesRemisionNube }
   * 
   */
  public ActualizarCredencialesRemisionNube createActualizarCredencialesRemisionNube() {
    return new ActualizarCredencialesRemisionNube();
  }

  /**
   * Create an instance of {@link InfAdicional }
   * 
   */
  public InfAdicional createInfAdicional() {
    return new InfAdicional();
  }

  /**
   * Create an instance of {@link CredencialEeutil }
   * 
   */
  public CredencialEeutil createCredencialEeutil() {
    return new CredencialEeutil();
  }

  /**
   * Create an instance of {@link Aplicacion }
   * 
   */
  public Aplicacion createAplicacion() {
    return new Aplicacion();
  }

  /**
   * Create an instance of {@link Roles }
   * 
   */
  public Roles createRoles() {
    return new Roles();
  }

  /**
   * Create an instance of {@link FilterPageRequest }
   * 
   */
  public FilterPageRequest createFilterPageRequest() {
    return new FilterPageRequest();
  }

  /**
   * Create an instance of {@link FilterPageRequestResponse }
   * 
   */
  public FilterPageRequestResponse createFilterPageRequestResponse() {
    return new FilterPageRequestResponse();
  }

  /**
   * Create an instance of {@link Unidad }
   * 
   */
  public Unidad createUnidad() {
    return new Unidad();
  }

  /**
   * Create an instance of {@link UnidadAplicacionEeutil }
   * 
   */
  public UnidadAplicacionEeutil createUnidadAplicacionEeutil() {
    return new UnidadAplicacionEeutil();
  }

  /**
   * Create an instance of {@link NumeroProcedimiento }
   * 
   */
  public NumeroProcedimiento createNumeroProcedimiento() {
    return new NumeroProcedimiento();
  }

  /**
   * Create an instance of {@link TipoCarpetaIndizada }
   * 
   */
  public TipoCarpetaIndizada createTipoCarpetaIndizada() {
    return new TipoCarpetaIndizada();
  }

  /**
   * Create an instance of {@link EstructuraCarpeta }
   * 
   */
  public EstructuraCarpeta createEstructuraCarpeta() {
    return new EstructuraCarpeta();
  }

  /**
   * Create an instance of {@link CredencialesRemisionNube }
   * 
   */
  public CredencialesRemisionNube createCredencialesRemisionNube() {
    return new CredencialesRemisionNube();
  }

  /**
   * Create an instance of {@link RespuestaEnvioJusticia2 }
   * 
   */
  public RespuestaEnvioJusticia2 createRespuestaEnvioJusticia2() {
    return new RespuestaEnvioJusticia2();
  }

  /**
   * Create an instance of {@link SolicitudAccesoExpAppUrl }
   * 
   */
  public SolicitudAccesoExpAppUrl createSolicitudAccesoExpAppUrl() {
    return new SolicitudAccesoExpAppUrl();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link EliminarUnidadUsuarioResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "eliminarUnidadUsuarioResponse")
  public JAXBElement<EliminarUnidadUsuarioResponse> createEliminarUnidadUsuarioResponse(
      EliminarUnidadUsuarioResponse value) {
    return new JAXBElement<EliminarUnidadUsuarioResponse>(_EliminarUnidadUsuarioResponse_QNAME,
        EliminarUnidadUsuarioResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link AltaCredencialesRemisionNubeResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "altaCredencialesRemisionNubeResponse")
  public JAXBElement<AltaCredencialesRemisionNubeResponse> createAltaCredencialesRemisionNubeResponse(
      AltaCredencialesRemisionNubeResponse value) {
    return new JAXBElement<AltaCredencialesRemisionNubeResponse>(
        _AltaCredencialesRemisionNubeResponse_QNAME, AltaCredencialesRemisionNubeResponse.class,
        null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link GetUnidadesAplicacionResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "getUnidadesAplicacionResponse")
  public JAXBElement<GetUnidadesAplicacionResponse> createGetUnidadesAplicacionResponse(
      GetUnidadesAplicacionResponse value) {
    return new JAXBElement<GetUnidadesAplicacionResponse>(_GetUnidadesAplicacionResponse_QNAME,
        GetUnidadesAplicacionResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link GetUnidadesUsuario }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "getUnidadesUsuario")
  public JAXBElement<GetUnidadesUsuario> createGetUnidadesUsuario(GetUnidadesUsuario value) {
    return new JAXBElement<GetUnidadesUsuario>(_GetUnidadesUsuario_QNAME, GetUnidadesUsuario.class,
        null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link AltaSolicitudAccesoExpAppUrl
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "altaSolicitudAccesoExpAppUrl")
  public JAXBElement<AltaSolicitudAccesoExpAppUrl> createAltaSolicitudAccesoExpAppUrl(
      AltaSolicitudAccesoExpAppUrl value) {
    return new JAXBElement<AltaSolicitudAccesoExpAppUrl>(_AltaSolicitudAccesoExpAppUrl_QNAME,
        AltaSolicitudAccesoExpAppUrl.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link AltaUsuarioResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "altaUsuarioResponse")
  public JAXBElement<AltaUsuarioResponse> createAltaUsuarioResponse(AltaUsuarioResponse value) {
    return new JAXBElement<AltaUsuarioResponse>(_AltaUsuarioResponse_QNAME,
        AltaUsuarioResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ListaNumeroProcedimiento }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "listaNumeroProcedimiento")
  public JAXBElement<ListaNumeroProcedimiento> createListaNumeroProcedimiento(
      ListaNumeroProcedimiento value) {
    return new JAXBElement<ListaNumeroProcedimiento>(_ListaNumeroProcedimiento_QNAME,
        ListaNumeroProcedimiento.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ActualizarCredencialesEeetuilApp
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "actualizarCredencialesEeetuilApp")
  public JAXBElement<ActualizarCredencialesEeetuilApp> createActualizarCredencialesEeetuilApp(
      ActualizarCredencialesEeetuilApp value) {
    return new JAXBElement<ActualizarCredencialesEeetuilApp>(
        _ActualizarCredencialesEeetuilApp_QNAME, ActualizarCredencialesEeetuilApp.class, null,
        value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link WSErrorInside }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService/types/error",
      name = "errorInside")
  public JAXBElement<WSErrorInside> createErrorInside(WSErrorInside value) {
    return new JAXBElement<WSErrorInside>(_ErrorInside_QNAME, WSErrorInside.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link AltaUsuario }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "altaUsuario")
  public JAXBElement<AltaUsuario> createAltaUsuario(AltaUsuario value) {
    return new JAXBElement<AltaUsuario>(_AltaUsuario_QNAME, AltaUsuario.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ListaUsuariosPaginadoResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "listaUsuariosPaginadoResponse")
  public JAXBElement<ListaUsuariosPaginadoResponse> createListaUsuariosPaginadoResponse(
      ListaUsuariosPaginadoResponse value) {
    return new JAXBElement<ListaUsuariosPaginadoResponse>(_ListaUsuariosPaginadoResponse_QNAME,
        ListaUsuariosPaginadoResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ComprobarDirectorioTemporal
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "comprobarDirectorioTemporal")
  public JAXBElement<ComprobarDirectorioTemporal> createComprobarDirectorioTemporal(
      ComprobarDirectorioTemporal value) {
    return new JAXBElement<ComprobarDirectorioTemporal>(_ComprobarDirectorioTemporal_QNAME,
        ComprobarDirectorioTemporal.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link AltaEstructuraCarpetaResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "altaEstructuraCarpetaResponse")
  public JAXBElement<AltaEstructuraCarpetaResponse> createAltaEstructuraCarpetaResponse(
      AltaEstructuraCarpetaResponse value) {
    return new JAXBElement<AltaEstructuraCarpetaResponse>(_AltaEstructuraCarpetaResponse_QNAME,
        AltaEstructuraCarpetaResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link BajaAplicacionResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "bajaAplicacionResponse")
  public JAXBElement<BajaAplicacionResponse> createBajaAplicacionResponse(
      BajaAplicacionResponse value) {
    return new JAXBElement<BajaAplicacionResponse>(_BajaAplicacionResponse_QNAME,
        BajaAplicacionResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link InfAdicionalAltaApp }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "infAdicionalAltaApp")
  public JAXBElement<InfAdicionalAltaApp> createInfAdicionalAltaApp(InfAdicionalAltaApp value) {
    return new JAXBElement<InfAdicionalAltaApp>(_InfAdicionalAltaApp_QNAME,
        InfAdicionalAltaApp.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link AltaSolicitudAccesoExpAppUrlResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "altaSolicitudAccesoExpAppUrlResponse")
  public JAXBElement<AltaSolicitudAccesoExpAppUrlResponse> createAltaSolicitudAccesoExpAppUrlResponse(
      AltaSolicitudAccesoExpAppUrlResponse value) {
    return new JAXBElement<AltaSolicitudAccesoExpAppUrlResponse>(
        _AltaSolicitudAccesoExpAppUrlResponse_QNAME, AltaSolicitudAccesoExpAppUrlResponse.class,
        null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link AltaCredencialesRemisionNube
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "altaCredencialesRemisionNube")
  public JAXBElement<AltaCredencialesRemisionNube> createAltaCredencialesRemisionNube(
      AltaCredencialesRemisionNube value) {
    return new JAXBElement<AltaCredencialesRemisionNube>(_AltaCredencialesRemisionNube_QNAME,
        AltaCredencialesRemisionNube.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link AltaUnidadUsuario }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "altaUnidadUsuario")
  public JAXBElement<AltaUnidadUsuario> createAltaUnidadUsuario(AltaUnidadUsuario value) {
    return new JAXBElement<AltaUnidadUsuario>(_AltaUnidadUsuario_QNAME, AltaUnidadUsuario.class,
        null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ListaUsuariosResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "listaUsuariosResponse")
  public JAXBElement<ListaUsuariosResponse> createListaUsuariosResponse(
      ListaUsuariosResponse value) {
    return new JAXBElement<ListaUsuariosResponse>(_ListaUsuariosResponse_QNAME,
        ListaUsuariosResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ListaEstructuraCarpetaResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "listaEstructuraCarpetaResponse")
  public JAXBElement<ListaEstructuraCarpetaResponse> createListaEstructuraCarpetaResponse(
      ListaEstructuraCarpetaResponse value) {
    return new JAXBElement<ListaEstructuraCarpetaResponse>(_ListaEstructuraCarpetaResponse_QNAME,
        ListaEstructuraCarpetaResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link AltaAplicacionResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "altaAplicacionResponse")
  public JAXBElement<AltaAplicacionResponse> createAltaAplicacionResponse(
      AltaAplicacionResponse value) {
    return new JAXBElement<AltaAplicacionResponse>(_AltaAplicacionResponse_QNAME,
        AltaAplicacionResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link GetUnidadesAplicacion }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "getUnidadesAplicacion")
  public JAXBElement<GetUnidadesAplicacion> createGetUnidadesAplicacion(
      GetUnidadesAplicacion value) {
    return new JAXBElement<GetUnidadesAplicacion>(_GetUnidadesAplicacion_QNAME,
        GetUnidadesAplicacion.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link LimpiarDirectorioTemporal }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "limpiarDirectorioTemporal")
  public JAXBElement<LimpiarDirectorioTemporal> createLimpiarDirectorioTemporal(
      LimpiarDirectorioTemporal value) {
    return new JAXBElement<LimpiarDirectorioTemporal>(_LimpiarDirectorioTemporal_QNAME,
        LimpiarDirectorioTemporal.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ListaUsuariosPaginado }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "listaUsuariosPaginado")
  public JAXBElement<ListaUsuariosPaginado> createListaUsuariosPaginado(
      ListaUsuariosPaginado value) {
    return new JAXBElement<ListaUsuariosPaginado>(_ListaUsuariosPaginado_QNAME,
        ListaUsuariosPaginado.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link BajaUsuario }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "bajaUsuario")
  public JAXBElement<BajaUsuario> createBajaUsuario(BajaUsuario value) {
    return new JAXBElement<BajaUsuario>(_BajaUsuario_QNAME, BajaUsuario.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ComprobarDirectorioTemporalResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "comprobarDirectorioTemporalResponse")
  public JAXBElement<ComprobarDirectorioTemporalResponse> createComprobarDirectorioTemporalResponse(
      ComprobarDirectorioTemporalResponse value) {
    return new JAXBElement<ComprobarDirectorioTemporalResponse>(
        _ComprobarDirectorioTemporalResponse_QNAME, ComprobarDirectorioTemporalResponse.class, null,
        value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link LanzaJobComunicacionTokenExpediente
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "lanzaJobComunicacionTokenExpediente")
  public JAXBElement<LanzaJobComunicacionTokenExpediente> createLanzaJobComunicacionTokenExpediente(
      LanzaJobComunicacionTokenExpediente value) {
    return new JAXBElement<LanzaJobComunicacionTokenExpediente>(
        _LanzaJobComunicacionTokenExpediente_QNAME, LanzaJobComunicacionTokenExpediente.class, null,
        value);
  }

  /**
   * Create an instance of {@link JAXBElement
   * }{@code <}{@link ActualizarCredencialesRemisionNubeResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "actualizarCredencialesRemisionNubeResponse")
  public JAXBElement<ActualizarCredencialesRemisionNubeResponse> createActualizarCredencialesRemisionNubeResponse(
      ActualizarCredencialesRemisionNubeResponse value) {
    return new JAXBElement<ActualizarCredencialesRemisionNubeResponse>(
        _ActualizarCredencialesRemisionNubeResponse_QNAME,
        ActualizarCredencialesRemisionNubeResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ListaAplicacionesResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "listaAplicacionesResponse")
  public JAXBElement<ListaAplicacionesResponse> createListaAplicacionesResponse(
      ListaAplicacionesResponse value) {
    return new JAXBElement<ListaAplicacionesResponse>(_ListaAplicacionesResponse_QNAME,
        ListaAplicacionesResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ListaUsuarios }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "listaUsuarios")
  public JAXBElement<ListaUsuarios> createListaUsuarios(ListaUsuarios value) {
    return new JAXBElement<ListaUsuarios>(_ListaUsuarios_QNAME, ListaUsuarios.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ListaAplicaciones }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "listaAplicaciones")
  public JAXBElement<ListaAplicaciones> createListaAplicaciones(ListaAplicaciones value) {
    return new JAXBElement<ListaAplicaciones>(_ListaAplicaciones_QNAME, ListaAplicaciones.class,
        null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link DeleteEstructuraCarpetaResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "deleteEstructuraCarpetaResponse")
  public JAXBElement<DeleteEstructuraCarpetaResponse> createDeleteEstructuraCarpetaResponse(
      DeleteEstructuraCarpetaResponse value) {
    return new JAXBElement<DeleteEstructuraCarpetaResponse>(_DeleteEstructuraCarpetaResponse_QNAME,
        DeleteEstructuraCarpetaResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link GetUnidadesUsuarioResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "getUnidadesUsuarioResponse")
  public JAXBElement<GetUnidadesUsuarioResponse> createGetUnidadesUsuarioResponse(
      GetUnidadesUsuarioResponse value) {
    return new JAXBElement<GetUnidadesUsuarioResponse>(_GetUnidadesUsuarioResponse_QNAME,
        GetUnidadesUsuarioResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement
   * }{@code <}{@link ActualizarCredencialesEeetuilAppResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "actualizarCredencialesEeetuilAppResponse")
  public JAXBElement<ActualizarCredencialesEeetuilAppResponse> createActualizarCredencialesEeetuilAppResponse(
      ActualizarCredencialesEeetuilAppResponse value) {
    return new JAXBElement<ActualizarCredencialesEeetuilAppResponse>(
        _ActualizarCredencialesEeetuilAppResponse_QNAME,
        ActualizarCredencialesEeetuilAppResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ListaEstructuraCarpeta }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "listaEstructuraCarpeta")
  public JAXBElement<ListaEstructuraCarpeta> createListaEstructuraCarpeta(
      ListaEstructuraCarpeta value) {
    return new JAXBElement<ListaEstructuraCarpeta>(_ListaEstructuraCarpeta_QNAME,
        ListaEstructuraCarpeta.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link EliminarUnidadUsuario }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "eliminarUnidadUsuario")
  public JAXBElement<EliminarUnidadUsuario> createEliminarUnidadUsuario(
      EliminarUnidadUsuario value) {
    return new JAXBElement<EliminarUnidadUsuario>(_EliminarUnidadUsuario_QNAME,
        EliminarUnidadUsuario.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link AltaUnidadAplicacion }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "altaUnidadAplicacion")
  public JAXBElement<AltaUnidadAplicacion> createAltaUnidadAplicacion(AltaUnidadAplicacion value) {
    return new JAXBElement<AltaUnidadAplicacion>(_AltaUnidadAplicacion_QNAME,
        AltaUnidadAplicacion.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement
   * }{@code <}{@link CrearActualizarUnidadAplicacionEeutilResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "crearActualizarUnidadAplicacionEeutilResponse")
  public JAXBElement<CrearActualizarUnidadAplicacionEeutilResponse> createCrearActualizarUnidadAplicacionEeutilResponse(
      CrearActualizarUnidadAplicacionEeutilResponse value) {
    return new JAXBElement<CrearActualizarUnidadAplicacionEeutilResponse>(
        _CrearActualizarUnidadAplicacionEeutilResponse_QNAME,
        CrearActualizarUnidadAplicacionEeutilResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link GetUnidadAplicacionEeutilResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "getUnidadAplicacionEeutilResponse")
  public JAXBElement<GetUnidadAplicacionEeutilResponse> createGetUnidadAplicacionEeutilResponse(
      GetUnidadAplicacionEeutilResponse value) {
    return new JAXBElement<GetUnidadAplicacionEeutilResponse>(
        _GetUnidadAplicacionEeutilResponse_QNAME, GetUnidadAplicacionEeutilResponse.class, null,
        value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link LimpiarDirectorioTemporalResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "limpiarDirectorioTemporalResponse")
  public JAXBElement<LimpiarDirectorioTemporalResponse> createLimpiarDirectorioTemporalResponse(
      LimpiarDirectorioTemporalResponse value) {
    return new JAXBElement<LimpiarDirectorioTemporalResponse>(
        _LimpiarDirectorioTemporalResponse_QNAME, LimpiarDirectorioTemporalResponse.class, null,
        value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ListaNumeroProcedimientoResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "listaNumeroProcedimientoResponse")
  public JAXBElement<ListaNumeroProcedimientoResponse> createListaNumeroProcedimientoResponse(
      ListaNumeroProcedimientoResponse value) {
    return new JAXBElement<ListaNumeroProcedimientoResponse>(
        _ListaNumeroProcedimientoResponse_QNAME, ListaNumeroProcedimientoResponse.class, null,
        value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link AltaNumeroProcedimientoResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "altaNumeroProcedimientoResponse")
  public JAXBElement<AltaNumeroProcedimientoResponse> createAltaNumeroProcedimientoResponse(
      AltaNumeroProcedimientoResponse value) {
    return new JAXBElement<AltaNumeroProcedimientoResponse>(_AltaNumeroProcedimientoResponse_QNAME,
        AltaNumeroProcedimientoResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link BajaAplicacion }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "bajaAplicacion")
  public JAXBElement<BajaAplicacion> createBajaAplicacion(BajaAplicacion value) {
    return new JAXBElement<BajaAplicacion>(_BajaAplicacion_QNAME, BajaAplicacion.class, null,
        value);
  }

  /**
   * Create an instance of {@link JAXBElement
   * }{@code <}{@link LanzaJobComunicacionTokenExpedienteResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "lanzaJobComunicacionTokenExpedienteResponse")
  public JAXBElement<LanzaJobComunicacionTokenExpedienteResponse> createLanzaJobComunicacionTokenExpedienteResponse(
      LanzaJobComunicacionTokenExpedienteResponse value) {
    return new JAXBElement<LanzaJobComunicacionTokenExpedienteResponse>(
        _LanzaJobComunicacionTokenExpedienteResponse_QNAME,
        LanzaJobComunicacionTokenExpedienteResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ActualizarUnidadesDir3Response
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "actualizarUnidadesDir3Response")
  public JAXBElement<ActualizarUnidadesDir3Response> createActualizarUnidadesDir3Response(
      ActualizarUnidadesDir3Response value) {
    return new JAXBElement<ActualizarUnidadesDir3Response>(_ActualizarUnidadesDir3Response_QNAME,
        ActualizarUnidadesDir3Response.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link GetUnidadAplicacionEeutil }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "getUnidadAplicacionEeutil")
  public JAXBElement<GetUnidadAplicacionEeutil> createGetUnidadAplicacionEeutil(
      GetUnidadAplicacionEeutil value) {
    return new JAXBElement<GetUnidadAplicacionEeutil>(_GetUnidadAplicacionEeutil_QNAME,
        GetUnidadAplicacionEeutil.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link CrearActualizarUnidadAplicacionEeutil
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "crearActualizarUnidadAplicacionEeutil")
  public JAXBElement<CrearActualizarUnidadAplicacionEeutil> createCrearActualizarUnidadAplicacionEeutil(
      CrearActualizarUnidadAplicacionEeutil value) {
    return new JAXBElement<CrearActualizarUnidadAplicacionEeutil>(
        _CrearActualizarUnidadAplicacionEeutil_QNAME, CrearActualizarUnidadAplicacionEeutil.class,
        null, value);
  }

  /**
   * Create an instance of {@link JAXBElement
   * }{@code <}{@link CrearActualizarSerialNumberCertificado }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "crearActualizarSerialNumberCertificado")
  public JAXBElement<CrearActualizarSerialNumberCertificado> createCrearActualizarSerialNumberCertificado(
      CrearActualizarSerialNumberCertificado value) {
    return new JAXBElement<CrearActualizarSerialNumberCertificado>(
        _CrearActualizarSerialNumberCertificado_QNAME, CrearActualizarSerialNumberCertificado.class,
        null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link AltaAplicacion }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "altaAplicacion")
  public JAXBElement<AltaAplicacion> createAltaAplicacion(AltaAplicacion value) {
    return new JAXBElement<AltaAplicacion>(_AltaAplicacion_QNAME, AltaAplicacion.class, null,
        value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link WSCredentialInside }{@code >}}
   * 
   */
  @XmlElementDecl(
      namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService/types/credential",
      name = "credential")
  public JAXBElement<WSCredentialInside> createCredential(WSCredentialInside value) {
    return new JAXBElement<WSCredentialInside>(_Credential_QNAME, WSCredentialInside.class, null,
        value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link DeleteEstructuraCarpeta }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "deleteEstructuraCarpeta")
  public JAXBElement<DeleteEstructuraCarpeta> createDeleteEstructuraCarpeta(
      DeleteEstructuraCarpeta value) {
    return new JAXBElement<DeleteEstructuraCarpeta>(_DeleteEstructuraCarpeta_QNAME,
        DeleteEstructuraCarpeta.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ActualizarSolicitudAccesoExpAppUrl
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "actualizarSolicitudAccesoExpAppUrl")
  public JAXBElement<ActualizarSolicitudAccesoExpAppUrl> createActualizarSolicitudAccesoExpAppUrl(
      ActualizarSolicitudAccesoExpAppUrl value) {
    return new JAXBElement<ActualizarSolicitudAccesoExpAppUrl>(
        _ActualizarSolicitudAccesoExpAppUrl_QNAME, ActualizarSolicitudAccesoExpAppUrl.class, null,
        value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link RespuestaEnvioJusticiaResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "respuestaEnvioJusticiaResponse")
  public JAXBElement<RespuestaEnvioJusticiaResponse> createRespuestaEnvioJusticiaResponse(
      RespuestaEnvioJusticiaResponse value) {
    return new JAXBElement<RespuestaEnvioJusticiaResponse>(_RespuestaEnvioJusticiaResponse_QNAME,
        RespuestaEnvioJusticiaResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link EliminarUnidadAplicacionResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "eliminarUnidadAplicacionResponse")
  public JAXBElement<EliminarUnidadAplicacionResponse> createEliminarUnidadAplicacionResponse(
      EliminarUnidadAplicacionResponse value) {
    return new JAXBElement<EliminarUnidadAplicacionResponse>(
        _EliminarUnidadAplicacionResponse_QNAME, EliminarUnidadAplicacionResponse.class, null,
        value);
  }

  /**
   * Create an instance of {@link JAXBElement
   * }{@code <}{@link ActualizarSolicitudAccesoExpAppUrlResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "actualizarSolicitudAccesoExpAppUrlResponse")
  public JAXBElement<ActualizarSolicitudAccesoExpAppUrlResponse> createActualizarSolicitudAccesoExpAppUrlResponse(
      ActualizarSolicitudAccesoExpAppUrlResponse value) {
    return new JAXBElement<ActualizarSolicitudAccesoExpAppUrlResponse>(
        _ActualizarSolicitudAccesoExpAppUrlResponse_QNAME,
        ActualizarSolicitudAccesoExpAppUrlResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link AltaEstructuraCarpeta }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "altaEstructuraCarpeta")
  public JAXBElement<AltaEstructuraCarpeta> createAltaEstructuraCarpeta(
      AltaEstructuraCarpeta value) {
    return new JAXBElement<AltaEstructuraCarpeta>(_AltaEstructuraCarpeta_QNAME,
        AltaEstructuraCarpeta.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link RespuestaEnvioJusticia }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "respuestaEnvioJusticia")
  public JAXBElement<RespuestaEnvioJusticia> createRespuestaEnvioJusticia(
      RespuestaEnvioJusticia value) {
    return new JAXBElement<RespuestaEnvioJusticia>(_RespuestaEnvioJusticia_QNAME,
        RespuestaEnvioJusticia.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement
   * }{@code <}{@link CrearActualizarSerialNumberCertificadoResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "crearActualizarSerialNumberCertificadoResponse")
  public JAXBElement<CrearActualizarSerialNumberCertificadoResponse> createCrearActualizarSerialNumberCertificadoResponse(
      CrearActualizarSerialNumberCertificadoResponse value) {
    return new JAXBElement<CrearActualizarSerialNumberCertificadoResponse>(
        _CrearActualizarSerialNumberCertificadoResponse_QNAME,
        CrearActualizarSerialNumberCertificadoResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link BajaUsuarioResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "bajaUsuarioResponse")
  public JAXBElement<BajaUsuarioResponse> createBajaUsuarioResponse(BajaUsuarioResponse value) {
    return new JAXBElement<BajaUsuarioResponse>(_BajaUsuarioResponse_QNAME,
        BajaUsuarioResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link EliminarUnidadAplicacion }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "eliminarUnidadAplicacion")
  public JAXBElement<EliminarUnidadAplicacion> createEliminarUnidadAplicacion(
      EliminarUnidadAplicacion value) {
    return new JAXBElement<EliminarUnidadAplicacion>(_EliminarUnidadAplicacion_QNAME,
        EliminarUnidadAplicacion.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link AltaNumeroProcedimiento }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "altaNumeroProcedimiento")
  public JAXBElement<AltaNumeroProcedimiento> createAltaNumeroProcedimiento(
      AltaNumeroProcedimiento value) {
    return new JAXBElement<AltaNumeroProcedimiento>(_AltaNumeroProcedimiento_QNAME,
        AltaNumeroProcedimiento.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ActualizarCredencialesRemisionNube
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "actualizarCredencialesRemisionNube")
  public JAXBElement<ActualizarCredencialesRemisionNube> createActualizarCredencialesRemisionNube(
      ActualizarCredencialesRemisionNube value) {
    return new JAXBElement<ActualizarCredencialesRemisionNube>(
        _ActualizarCredencialesRemisionNube_QNAME, ActualizarCredencialesRemisionNube.class, null,
        value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link AltaUnidadUsuarioResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "altaUnidadUsuarioResponse")
  public JAXBElement<AltaUnidadUsuarioResponse> createAltaUnidadUsuarioResponse(
      AltaUnidadUsuarioResponse value) {
    return new JAXBElement<AltaUnidadUsuarioResponse>(_AltaUnidadUsuarioResponse_QNAME,
        AltaUnidadUsuarioResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link AltaUnidadAplicacionResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "altaUnidadAplicacionResponse")
  public JAXBElement<AltaUnidadAplicacionResponse> createAltaUnidadAplicacionResponse(
      AltaUnidadAplicacionResponse value) {
    return new JAXBElement<AltaUnidadAplicacionResponse>(_AltaUnidadAplicacionResponse_QNAME,
        AltaUnidadAplicacionResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link InfAdicionalAltaAppResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "infAdicionalAltaAppResponse")
  public JAXBElement<InfAdicionalAltaAppResponse> createInfAdicionalAltaAppResponse(
      InfAdicionalAltaAppResponse value) {
    return new JAXBElement<InfAdicionalAltaAppResponse>(_InfAdicionalAltaAppResponse_QNAME,
        InfAdicionalAltaAppResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ActualizarUnidadesDir3 }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
      name = "actualizarUnidadesDir3")
  public JAXBElement<ActualizarUnidadesDir3> createActualizarUnidadesDir3(
      ActualizarUnidadesDir3 value) {
    return new JAXBElement<ActualizarUnidadesDir3>(_ActualizarUnidadesDir3_QNAME,
        ActualizarUnidadesDir3.class, null, value);
  }

}
