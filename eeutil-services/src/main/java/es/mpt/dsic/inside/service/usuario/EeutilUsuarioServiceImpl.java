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

package es.mpt.dsic.inside.service.usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.ws.BindingProvider;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSPasswordCallback;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.mpt.dsic.eeutil.inside.admin.model.InsideWSException;
import es.mpt.dsic.eeutil.inside.admin.model.NumeroProcedimiento;
import es.mpt.dsic.eeutil.inside.admin.model.Unidad;
import es.mpt.dsic.eeutil.inside.admin.model.Usuario;
import es.mpt.dsic.eeutil.inside.admin.model.UsuarioResultadoBusqueda;
import es.mpt.dsic.eeutil.inside.consumer.AdminWS;
import es.mpt.dsic.eeutil.inside.consumer.AdminWebService;
import es.mpt.dsic.inside.model.pagination.FilterPageRequest;
import es.mpt.dsic.inside.model.pagination.ResultadoBusqueda;
import es.mpt.dsic.inside.model.procedimiento.ProcedimientoObject;
import es.mpt.dsic.inside.model.unidad.UnidadObject;
import es.mpt.dsic.inside.model.usuario.UsuarioObject;
import es.mpt.dsic.inside.service.ConsumeWSUtils;

@Service("eeutilUsuarioService")
public class EeutilUsuarioServiceImpl implements EeutilUsuarioService {

  protected static final Log logger = LogFactory.getLog(EeutilUsuarioServiceImpl.class);

  private AdminWebService adminInsideWS;

  private Properties credentialProperties;

  private Properties propertiesSecurity;

  private Boolean connectedInside = Boolean.FALSE;

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

  @PostConstruct
  public Boolean configureInside() {
    String urlIns = null;
    if (credentialProperties != null && !connectedInside) {
      try {
        urlIns = credentialProperties.getProperty("inside.admin.url");
        logger.debug(String.format("El WS de FOLIADO se encuentra en %s", urlIns));

        URL url = new URL(urlIns);
        AdminWS adminWS = new AdminWS(url);
        adminInsideWS = adminWS.getPort(AdminWebService.class);

        configureInside(urlIns);
        connectedInside = Boolean.TRUE;
      } catch (Exception e) {
        logger.error("No se puede conectar al servicio Admin de Inside " + urlIns, e);
      }
    }
    return connectedInside;
  }

  private void configureInside(String url) {

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

    // carga alternativa apuntando al fichero fisicamente
    // outProps.put(WSHandlerConstants.SIG_PROP_FILE,
    // "security.properties");

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

  @Override
  public ResultadoBusqueda<UsuarioObject> getUsuarios(String app, FilterPageRequest filterEeutil)
      throws InsideWSException {
    ResultadoBusqueda<UsuarioObject> retorno = new ResultadoBusqueda<UsuarioObject>();
    if (app.equals("Inside") && configureInside()) {
      es.mpt.dsic.eeutil.inside.admin.model.FilterPageRequest filter =
          new es.mpt.dsic.eeutil.inside.admin.model.FilterPageRequest();
      filter.setFiltro(filterEeutil.getFilter());
      filter.setLimite(filterEeutil.getPageSize());
      filter.setPagina(filterEeutil.getPageNumber());
      UsuarioResultadoBusqueda datos = adminInsideWS.listaUsuariosPaginado(filter);
      retorno.setData(converDataInside(datos));
      retorno.setiTotalRecords(datos.getPaginador().getTotales());
      retorno.setiTotalDisplayRecords(datos.getPaginador().getDevueltos());
    }
    return retorno;
  }

  @Override
  public List<UnidadObject> getUsuariosFromNif(String nifUsuario) throws InsideWSException {
    List<UnidadObject> retorno = null;
    if (configureInside()) {
      retorno = converDataInsideUnidad(adminInsideWS.getUnidadesUsuario(nifUsuario));
    }
    return retorno;
  }

  @Override
  public List<ProcedimientoObject> getListaProcedimientos(String nifUsuario)
      throws InsideWSException {
    List<ProcedimientoObject> retorno = null;
    if (configureInside()) {
      retorno = converDataInsideProcedimiento(adminInsideWS.listaNumeroProcedimiento());
    }
    return retorno;
  }

  @Override
  public void borrarUnidadUsuario(String nifUsuario, String codigoUnidadOrganica,
      String numeroProcedimiento) throws InsideWSException {

    if (configureInside()) {
      adminInsideWS.eliminarUnidadUsuario(nifUsuario, codigoUnidadOrganica, numeroProcedimiento);
    }

  }

  private UsuarioObject converDataInside(Usuario data) {
    UsuarioObject retorno = new UsuarioObject();
    retorno.setIdentificador(data.getNif());
    retorno.setActivo(data.isActivo());
    retorno.setCodigoUnidadOrganica(data.getCodigoUnidadOrganica());
    retorno.setNumeroProcedimiento(data.getNumeroProcedimiento());
    retorno.setUnidadDescripcion(data.getNombreUnidadOrganica());
    return retorno;
  }

  private List<UsuarioObject> converDataInside(UsuarioResultadoBusqueda data) {
    List<UsuarioObject> retorno = new ArrayList<UsuarioObject>();
    if (CollectionUtils.isNotEmpty(data.getResultados())) {
      for (Usuario user : data.getResultados()) {
        retorno.add(converDataInside(user));
      }
    }
    return retorno;
  }

  private UnidadObject converDataInside(Unidad data) {
    UnidadObject retorno = new UnidadObject();
    retorno.setNombre(data.getNombre());
    retorno.setActivo(data.isActivo());
    retorno.setNumeroProcedimiento(data.getNumeroProcedimiento());
    retorno.setCodigo(data.getCodigo());
    return retorno;
  }

  private List<UnidadObject> converDataInsideUnidad(List<Unidad> data) {
    List<UnidadObject> retorno = new ArrayList<UnidadObject>();
    if (CollectionUtils.isNotEmpty(data)) {
      for (Unidad user : data) {
        retorno.add(converDataInside(user));
      }
    }
    return retorno;
  }

  private ProcedimientoObject converDataInsideProcedimiento(NumeroProcedimiento data) {
    ProcedimientoObject retorno = new ProcedimientoObject();
    retorno.setNumeroProcedimiento(data.getNumeroProcedimiento());
    return retorno;
  }

  private List<ProcedimientoObject> converDataInsideProcedimiento(List<NumeroProcedimiento> data) {
    List<ProcedimientoObject> retorno = new ArrayList<ProcedimientoObject>();
    if (CollectionUtils.isNotEmpty(data)) {
      for (NumeroProcedimiento procedimiento : data) {
        retorno.add(converDataInsideProcedimiento(procedimiento));
      }
    }
    return retorno;
  }

  @Override
  public UsuarioObject bajaUsuario(String app, UsuarioObject usuario) throws InsideWSException {
    UsuarioObject retorno = null;
    if (app.equals("Inside") && configureInside()) {
      Usuario user = new Usuario();
      user.setNif(usuario.getIdentificador());
      user.setCodigoUnidadOrganica(usuario.getCodigoUnidadOrganica());
      user = adminInsideWS.bajaUsuario(user);
      retorno = converDataInside(user);
    }
    return retorno;
  }

  @Override
  public UsuarioObject altaUsuario(String app, UsuarioObject usuario) throws InsideWSException {
    UsuarioObject retorno = null;
    if (app.equals("Inside") && configureInside()) {
      Usuario user = new Usuario();
      user.setNif(usuario.getIdentificador());
      user.setCodigoUnidadOrganica(usuario.getCodigoUnidadOrganica());
      user.setNumeroProcedimiento(usuario.getNumeroProcedimiento());
      user = adminInsideWS.altaUsuario(user);
      retorno = converDataInside(user);
    }
    return retorno;
  }

  @Override
  public UsuarioObject altaUsuario(String app, UsuarioObject usuario, String role)
      throws InsideWSException {
    UsuarioObject retorno = null;
    if (app.equals("Inside") && configureInside()) {
      Usuario user = new Usuario();
      user.setNif(usuario.getIdentificador());
      user.setCodigoUnidadOrganica(usuario.getCodigoUnidadOrganica());
      user.setNumeroProcedimiento(usuario.getNumeroProcedimiento());

      user.setIdRol(role);

      user = adminInsideWS.altaUsuario(user);
      retorno = converDataInside(user);
    }
    return retorno;
  }

}
