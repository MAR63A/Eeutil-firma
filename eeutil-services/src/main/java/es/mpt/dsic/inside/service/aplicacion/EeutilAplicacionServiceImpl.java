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

package es.mpt.dsic.inside.service.aplicacion;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.ws.BindingProvider;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSPasswordCallback;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import es.mpt.dsic.csvstorage.admin.consumer.AdminWSService;
import es.mpt.dsic.csvstorage.admin.consumer.AdminWSService_Service;
import es.mpt.dsic.csvstorage.admin.model.AltaAplicacion;
import es.mpt.dsic.csvstorage.admin.model.BajaAplicacion;
import es.mpt.dsic.csvstorage.admin.model.CSVStorageException;
import es.mpt.dsic.csvstorage.admin.model.CSVStorageException_Exception;
import es.mpt.dsic.csvstorage.admin.model.InfAdicionalAltaApp;
import es.mpt.dsic.csvstorage.admin.model.ListaAplicaciones;
import es.mpt.dsic.eeutil.csvbroker.admin.model.CSVBrokerException;
import es.mpt.dsic.eeutil.inside.admin.model.Aplicacion;
import es.mpt.dsic.eeutil.inside.admin.model.InfAdicional;
import es.mpt.dsic.eeutil.inside.admin.model.InsideWSException;
import es.mpt.dsic.eeutil.inside.consumer.AdminWS;
import es.mpt.dsic.eeutil.inside.consumer.AdminWebService;
import es.mpt.dsic.inside.convert.ConvertDataCsvBroker;
import es.mpt.dsic.inside.convert.ConvertDataCsvStorage;
import es.mpt.dsic.inside.convert.ConvertDataEeutil;
import es.mpt.dsic.inside.convert.ConvertDataInside;
import es.mpt.dsic.inside.dao.EeutilDao;
import es.mpt.dsic.inside.model.EeutilAplicacion;
import es.mpt.dsic.inside.model.EeutilAplicacionOperacion;
import es.mpt.dsic.inside.model.EeutilAplicacionPlantilla;
import es.mpt.dsic.inside.model.EeutilAplicacionPropiedad;
import es.mpt.dsic.inside.model.EeutilPeticion;
import es.mpt.dsic.inside.model.PeticionesPDFA;
import es.mpt.dsic.inside.model.aplicacion.AplicacionObject;
import es.mpt.dsic.inside.service.ConsumeWSUtils;


@Service("eeutilAplicacionService")
public class EeutilAplicacionServiceImpl implements EeutilAplicacionService {

  protected static final Log logger = LogFactory.getLog(EeutilAplicacionServiceImpl.class);

  private AdminWebService adminInsideWS;

  private AdminWSService adminCsvStorageService;

  private es.mpt.dsic.eeutil.csvbroker.admin.consumer.AdminWSService adminCsvBrokerService;

  private Properties credentialProperties;

  private Properties propertiesSecurity;

  private Boolean connectedInside = Boolean.FALSE;;
  private Boolean connectedCsvStorage = Boolean.FALSE;
  private Boolean connectedCsvBroker = Boolean.FALSE;

  @Autowired
  private EeutilDao eeutilDao;

  @Autowired
  private MessageSource messageSource;

  @Autowired
  ConsumeWSUtils consumeWSUtils;

  public Properties getCredentialProperties() {
    return credentialProperties;
  }

  public void setCredentialProperties(Properties credentialProperties) {
    this.credentialProperties = credentialProperties;
  }

  public Properties getPropertiesSecurity() {
    return propertiesSecurity;
  }

  public void setPropertiesSecurity(Properties propertiesSecurity) {
    this.propertiesSecurity = propertiesSecurity;
  }

  private void configureSystemTrustStore() {
    System.setProperty("javax.net.ssl.trustStore",
        propertiesSecurity.getProperty("org.apache.ws.security.crypto.merlin.truststore.file"));
    System.setProperty("javax.net.ssl.trustStorePassword",
        propertiesSecurity.getProperty("org.apache.ws.security.crypto.merlin.truststore.password"));
    System.setProperty("javax.net.ssl.trustStoreType",
        propertiesSecurity.getProperty("org.apache.ws.security.crypto.merlin.truststore.type"));
  }

  public Boolean configureInside() {
    URL urlInside = null;
    String urlIns = null;
    try {
      if (!connectedInside) {
        if (credentialProperties != null) {
          try {
            urlIns = credentialProperties.getProperty("inside.admin.url");
            logger.debug(String.format("El WS de Inside se encuentra en %s", urlIns));
            urlInside = new URL(urlIns);
          } catch (MalformedURLException me) {
            logger.error("No se puede crear la URL del servicio admin de Inside " + urlIns, me);
          }

          AdminWS ssInside = new AdminWS(urlInside);
          adminInsideWS = ssInside.getPort(AdminWebService.class);
          configureInside(urlIns);
          connectedInside = Boolean.TRUE;
        }
      }
    } catch (Exception e) {
      logger.error("No se puede conectar al servicio Admin de Inside " + urlIns, e);
    }
    return connectedInside;
  }

  public Boolean configureCsvStorage() {
    String urlCsvSto = null;
    URL urlCsvStorage = null;
    try {
      if (!connectedCsvStorage) {

        try {
          urlCsvSto = credentialProperties.getProperty("csvStorage.admin.url");
          logger.debug(String.format("El WS de CsvStorage se encuentra en %s", urlCsvSto));
          urlCsvStorage = new URL(urlCsvSto);
        } catch (MalformedURLException me) {
          logger.error("No se puede crear la URL del servicio Csv Storage " + urlCsvSto, me);
        }

        AdminWSService_Service ssCsv = new AdminWSService_Service(urlCsvStorage);
        adminCsvStorageService = ssCsv.getPort(AdminWSService.class);
        configureCsvStorage(urlCsvSto);
        connectedCsvStorage = Boolean.TRUE;

      }
    } catch (Exception e) {
      logger.error("No se puede conectar al servicio Admin de Csv Storage " + urlCsvSto, e);
    }
    return connectedCsvStorage;
  }


  public Boolean configureCsvBroker() {
    String urlCsvBro = null;
    URL urlCsvBroker = null;
    try {
      if (!connectedCsvBroker) {

        try {
          urlCsvBro = credentialProperties.getProperty("csvBroker.admin.url");
          logger.debug(String.format("El WS de CsvBroker se encuentra en %s", urlCsvBro));
          urlCsvBroker = new URL(urlCsvBro);
        } catch (MalformedURLException me) {
          logger.error("No se puede crear la URL del servicio Csv Broker " + urlCsvBro, me);
        }

        es.mpt.dsic.eeutil.csvbroker.admin.consumer.AdminWSService_Service ssCsv =
            new es.mpt.dsic.eeutil.csvbroker.admin.consumer.AdminWSService_Service(urlCsvBroker);
        adminCsvBrokerService = ssCsv.getAdminWSServicePort();
        configureCsvBroker(urlCsvBro);
        connectedCsvBroker = Boolean.TRUE;
      }
    } catch (Exception e) {
      logger.error("No se puede conectar al servicio Admin de Csv Broker " + urlCsvBro, e);
    }
    return connectedCsvBroker;
  }


  private void configureInside(String url) {
    configureSystemTrustStore();

    final String passwordCertificado = propertiesSecurity.getProperty("passwordCertificado");

    org.apache.cxf.endpoint.Client client = ClientProxy.getClient(adminInsideWS);
    org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();

    // Disable Chucking
    consumeWSUtils.disableChunking(client);

    Map<String, Object> outProps = new HashMap<String, Object>();
    outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE);
    outProps.put(WSHandlerConstants.SIG_KEY_ID, "DirectReference");

    // cargamos las properties de seguridad
    outProps.put("CryptoProperties", propertiesSecurity);
    outProps.put("signaturePropRefId", "CryptoProperties");

    outProps.put(WSHandlerConstants.USER,
        propertiesSecurity.getProperty("WSHandlerConstants.USER"));

    outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
    outProps.put(WSHandlerConstants.PW_CALLBACK_REF, new CallbackHandler() {
      @Override
      public void handle(final Callback[] callbacks)
          throws IOException, UnsupportedCallbackException {
        final WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        pc.setPassword(passwordCertificado);
      }
    });

    outProps.put(WSHandlerConstants.SIGNATURE_PARTS,
        "{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body");

    WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
    cxfEndpoint.getOutInterceptors().add(wssOut);

    BindingProvider bp = (BindingProvider) adminInsideWS;
    bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
  }

  private void configureCsvStorage(String url) {
    configureSystemTrustStore();

    final String passwordCertificado = propertiesSecurity.getProperty("passwordCertificado");

    org.apache.cxf.endpoint.Client client = ClientProxy.getClient(adminCsvStorageService);
    org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();

    // Disable Chucking
    consumeWSUtils.disableChunking(client);

    Map<String, Object> outProps = new HashMap<String, Object>();
    outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE);
    outProps.put(WSHandlerConstants.SIG_KEY_ID, "DirectReference");

    // cargamos las properties de seguridad
    outProps.put("CryptoProperties", propertiesSecurity);
    outProps.put("signaturePropRefId", "CryptoProperties");

    outProps.put(WSHandlerConstants.USER,
        propertiesSecurity.getProperty("WSHandlerConstants.USER"));

    outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
    outProps.put(WSHandlerConstants.PW_CALLBACK_REF, new CallbackHandler() {
      @Override
      public void handle(final Callback[] callbacks)
          throws IOException, UnsupportedCallbackException {
        final WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        pc.setPassword(passwordCertificado);
      }
    });

    outProps.put(WSHandlerConstants.SIGNATURE_PARTS,
        "{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body");

    WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
    cxfEndpoint.getOutInterceptors().add(wssOut);

    BindingProvider bp = (BindingProvider) adminCsvStorageService;
    bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);


  }

  private void configureCsvBroker(String url) {
    configureSystemTrustStore();

    final String passwordCertificado = propertiesSecurity.getProperty("passwordCertificado");
    final String alias = propertiesSecurity.getProperty("WSHandlerConstants.USER");

    org.apache.cxf.endpoint.Client client = ClientProxy.getClient(adminCsvBrokerService);

    org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();

    // Disable Chucking
    consumeWSUtils.disableChunking(client);

    Map<String, Object> outProps = new HashMap<String, Object>();
    outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE);
    outProps.put(WSHandlerConstants.SIG_KEY_ID, "DirectReference");

    // cargamos las properties de seguridad
    outProps.put("CryptoProperties", propertiesSecurity);
    outProps.put("signaturePropRefId", "CryptoProperties");

    outProps.put(WSHandlerConstants.USER, alias);

    outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
    outProps.put(WSHandlerConstants.PW_CALLBACK_REF, new CallbackHandler() {
      @Override
      public void handle(final Callback[] callbacks)
          throws IOException, UnsupportedCallbackException {
        final WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        pc.setPassword(passwordCertificado);
      }
    });

    WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
    cxfEndpoint.getOutInterceptors().add(wssOut);

    BindingProvider bp = (BindingProvider) adminCsvBrokerService;
    bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);

  }

  @SuppressWarnings("unchecked")
  @Override
  public List<AplicacionObject> getAplicaciones(String app)
      throws InsideWSException, CSVStorageException, CSVBrokerException {
    List<AplicacionObject> retorno = null;
    if (app.equals("Inside") && configureInside()) {
      retorno = ConvertDataInside.converDataInside(adminInsideWS.listaAplicaciones());
    } else if (app.equals("Eeutil")) {
      List<EeutilAplicacion> data = eeutilDao.getAllObjetos(EeutilAplicacion.class);
      retorno = ConvertDataEeutil.converDataEeutil(data);
    } else if (app.equals("CsvStorage") && configureCsvStorage()) {
      ListaAplicaciones parameters = new ListaAplicaciones();
      retorno =
          ConvertDataCsvStorage.convertData(adminCsvStorageService.listaAplicaciones(parameters));
    } else if (app.equals("CsvBroker") && configureCsvBroker()) {
      es.mpt.dsic.eeutil.csvbroker.admin.model.ListaAplicaciones parameters =
          new es.mpt.dsic.eeutil.csvbroker.admin.model.ListaAplicaciones();
      retorno =
          ConvertDataCsvBroker.convertData(adminCsvBrokerService.listaAplicaciones(parameters));
    }
    return retorno;
  }

  @Override
  public Map<String, String> getInfAdicional(String app) throws InsideWSException,
      CSVStorageException_Exception, CSVStorageException, CSVBrokerException {
    Map<String, String> retorno = new HashMap<String, String>();
    if (app.equals("Inside") && configureInside()) {
      List<InfAdicional> data = adminInsideWS.infAdicionalAltaApp();
      retorno = ConvertDataInside.convertIndAdicionalInside(data);
    } else if (app.equals("Eeutil")) {
      retorno = ConvertDataEeutil.getDefaultAdicional();
    } else if (app.equals("CsvStorage") && configureCsvStorage()) {
      InfAdicionalAltaApp criteria = new InfAdicionalAltaApp();
      retorno = ConvertDataCsvStorage
          .convertInfAdicional(adminCsvStorageService.infAdicionalAltaApp(criteria));
    } else if (app.equals("CsvBroker") && configureCsvBroker()) {
      es.mpt.dsic.eeutil.csvbroker.admin.model.InfAdicionalAltaApp criteria =
          new es.mpt.dsic.eeutil.csvbroker.admin.model.InfAdicionalAltaApp();
      retorno = ConvertDataCsvBroker
          .convertInfAdicional(adminCsvBrokerService.infAdicionalAltaApp(criteria));
    }
    return retorno;
  }

  @Override
  public AplicacionObject desactivar(String app, AplicacionObject aplicacion)
      throws InsideWSException, CSVStorageException, CSVBrokerException {
    AplicacionObject retorno = null;
    if (app.equals("Inside") && configureInside()) {
      Aplicacion apli = ConvertDataInside.converDataFromInside(aplicacion);
      apli.setNombre(aplicacion.getIdentificador());
      apli = adminInsideWS.bajaAplicacion(apli);
      retorno = ConvertDataInside.converDataToInside(apli);
    } else if (app.equals("Eeutil")) {
      EeutilAplicacion apli = (EeutilAplicacion) eeutilDao.getObjeto(EeutilAplicacion.class,
          aplicacion.getIdentificador());
      apli.setActiva(false);
      eeutilDao.update(apli);
    } else if (app.equals("CsvStorage") && configureCsvStorage()) {
      BajaAplicacion parameter = new BajaAplicacion();
      parameter.setIdAplicacion(aplicacion.getIdentificador());
      retorno = ConvertDataCsvStorage.convertData(adminCsvStorageService.bajaAplicacion(parameter));
    } else if (app.equals("CsvBroker") && configureCsvBroker()) {
      es.mpt.dsic.eeutil.csvbroker.admin.model.BajaAplicacion parameter =
          new es.mpt.dsic.eeutil.csvbroker.admin.model.BajaAplicacion();
      parameter.setIdAplicacion(aplicacion.getIdentificador());
      retorno = ConvertDataCsvBroker.convertData(adminCsvBrokerService.bajaAplicacion(parameter));
    }
    return retorno;
  }

  @Override
  public AplicacionObject activar(String app, AplicacionObject aplicacion)
      throws InsideWSException, CSVStorageException, CSVBrokerException {
    AplicacionObject retorno = null;
    if (app.equals("Inside") && configureInside()) {
      throw new InsideWSException("No se ha podido activar la aplicaci�n");
    } else if (app.equals("Eeutil")) {
      EeutilAplicacion apli = (EeutilAplicacion) eeutilDao.getObjeto(EeutilAplicacion.class,
          aplicacion.getIdentificador());
      apli.setActiva(true);
      eeutilDao.update(apli);
    } else if (app.equals("CsvStorage") && configureCsvStorage()) {
      throw new CSVStorageException("No se ha podido activar la aplicaci�n");
    } else if (app.equals("CsvBroker") && configureCsvBroker()) {
      throw new CSVBrokerException("No se ha podido activar la aplicaci�n");
    }
    return retorno;
  }

  @Override
  public void eliminarAplicacion(String app, AplicacionObject aplicacion, Locale locale)
      throws InsideWSException, CSVStorageException, CSVBrokerException {
    if (app.equals("Inside") && configureInside()) {
      throw new InsideWSException(
          messageSource.getMessage("listaAplicaciones.eliminar.error", null, locale));
    } else if (app.equals("Eeutil")) {
      removeAppEeutil(aplicacion.getIdentificador());
    } else if (app.equals("CsvStorage") && configureCsvStorage()) {
      throw new CSVStorageException(
          messageSource.getMessage("listaAplicaciones.eliminar.error", null, locale));
    } else if (app.equals("CsvBroker") && configureCsvBroker()) {
      throw new CSVBrokerException(
          messageSource.getMessage("listaAplicaciones.eliminar.error", null, locale));
    }
  }

  @Override
  public AplicacionObject altaAplicacion(String app, AplicacionObject aplicacion)
      throws InsideWSException, CSVStorageException, CSVBrokerException {
    try {
      AplicacionObject retorno = null;
      if (app.equals("Inside") && configureInside()) {
        Aplicacion apli = ConvertDataInside.converDataFromInside(aplicacion);
        apli = adminInsideWS.altaAplicacion(apli);
        retorno = ConvertDataInside.converDataToInside(apli);
      } else if (app.equals("Eeutil")) {
        EeutilAplicacion apli = ConvertDataEeutil.converDataEeutil(aplicacion);
        apli.setActiva(true);
        Object dataBBDD = eeutilDao.getObjeto(EeutilAplicacion.class, apli.getIdaplicacion());
        if (dataBBDD != null) {
          if (StringUtils.isEmpty(aplicacion.getPassword())) {
            apli.setPassword(((EeutilAplicacion) dataBBDD).getPassword());
          }
          // eliminamos las propiedades previas
          EeutilAplicacion entity = (EeutilAplicacion) dataBBDD;
          if (CollectionUtils.isNotEmpty(entity.getPropiedades())) {
            for (EeutilAplicacionPropiedad propiedad : entity.getPropiedades()) {
              eeutilDao.remove(propiedad);
            }
          }

          eeutilDao.update(apli);
        } else {
          eeutilDao.salvar(apli);
        }
      } else if (app.equals("CsvStorage") && configureCsvStorage()) {
        AltaAplicacion parameter = new AltaAplicacion();
        parameter.setAplicacion(ConvertDataCsvStorage.convertData(aplicacion));
        retorno =
            ConvertDataCsvStorage.convertData(adminCsvStorageService.altaAplicacion(parameter));
      } else if (app.equals("CsvBroker") && configureCsvBroker()) {
        es.mpt.dsic.eeutil.csvbroker.admin.model.AltaAplicacion parameter =
            new es.mpt.dsic.eeutil.csvbroker.admin.model.AltaAplicacion();
        parameter.setAplicacion(ConvertDataCsvBroker.convertData(aplicacion));
        retorno = ConvertDataCsvBroker.convertData(adminCsvBrokerService.altaAplicacion(parameter));
      }
      return retorno;
    } catch (NoSuchAlgorithmException e) {
      throw new InsideWSException(e.getMessage());
    } catch (UnsupportedEncodingException e) {
      throw new InsideWSException(e.getMessage());
    }
  }

  private void removeAppEeutil(String identificador) {
    EeutilAplicacion appEntity =
        (EeutilAplicacion) eeutilDao.getObjeto(EeutilAplicacion.class, identificador);

    // peticiones pdfa
    List<Criterion> critPeticionesPdfA = new ArrayList<Criterion>();
    critPeticionesPdfA.add(Restrictions.eq("idAplicacion", identificador));
    List<PeticionesPDFA> peticionesPdfA =
        eeutilDao.findObjetos(PeticionesPDFA.class, critPeticionesPdfA);
    if (CollectionUtils.isNotEmpty(peticionesPdfA)) {
      for (PeticionesPDFA peticionPDFA : peticionesPdfA) {
        eeutilDao.remove(peticionPDFA);
      }
    }

    // peticiones
    List<Criterion> critPeticiones = new ArrayList<Criterion>();
    critPeticiones.add(Restrictions.eq("aplicacion", identificador));
    List<EeutilPeticion> peticiones = eeutilDao.findObjetos(EeutilPeticion.class, critPeticiones);
    if (CollectionUtils.isNotEmpty(peticiones)) {
      for (EeutilPeticion peticion : peticiones) {
        eeutilDao.remove(peticion);
      }
    }

    // aplicacion operacion
    List<Criterion> critAppOperacion = new ArrayList<Criterion>();
    critAppOperacion.add(Restrictions.eq("idAplicacion", identificador));
    List<EeutilAplicacionOperacion> appOperaciones =
        eeutilDao.findObjetos(EeutilAplicacionOperacion.class, critAppOperacion);
    if (CollectionUtils.isNotEmpty(appOperaciones)) {
      for (EeutilAplicacionOperacion appOperacion : appOperaciones) {
        eeutilDao.remove(appOperacion);
      }
    }

    // aplicaciones plantillas
    if (CollectionUtils.isNotEmpty(appEntity.getPlantillas())) {
      for (EeutilAplicacionPlantilla plantillApp : appEntity.getPlantillas()) {
        eeutilDao.remove(plantillApp);
      }
    }

    // propiedades aplicacion
    if (CollectionUtils.isNotEmpty(appEntity.getPropiedades())) {
      for (EeutilAplicacionPropiedad propiedadApp : appEntity.getPropiedades()) {
        eeutilDao.remove(propiedadApp);
      }
    }


    // aplicacion
    eeutilDao.remove(appEntity);
  }

}
