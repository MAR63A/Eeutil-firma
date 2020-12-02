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

package es.mpt.dsic.inside.security.interceptor;

import java.util.Date;
import javax.xml.namespace.QName;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxws.interceptors.HolderInInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageContentsList;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import es.mpt.dsic.inside.model.aplicacionPeticion.AplicacionOperacionObject;
import es.mpt.dsic.inside.model.peticion.PeticionObject;
import es.mpt.dsic.inside.security.model.ApplicationLogin;
import es.mpt.dsic.inside.security.service.AdministrationService;
import es.mpt.dsic.inside.security.service.AplicacionInfoService;

public class SoapAuthenticationObjectInterceptor extends AbstractPhaseInterceptor<Message> {

  protected final static Log logger = LogFactory.getLog(SoapAuthenticationObjectInterceptor.class);

  private AuthenticationManager authenticationManager;
  private boolean authenticationRequired = true;

  @Autowired
  private AplicacionInfoService aplicacionInfoService;

  @Autowired
  private AdministrationService administrationService;

  public SoapAuthenticationObjectInterceptor() {

    super(Phase.PRE_INVOKE);

    addAfter(HolderInInterceptor.class.getName());
  }

  /*
   * public void handleMessage(Message message) throws Fault {
   * logger.debug("SoapAuthenticationObjectInterceptor handleMessage"); MessageContentsList
   * inObjects = MessageContentsList.getContentsList(message);
   * 
   * boolean existe=false; for(Object obj:inObjects) { if(obj instanceof ApplicationLogin) {
   * ApplicationLogin info=(ApplicationLogin)obj;
   * 
   * authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
   * info.getIdApplicacion(), info.getPassword()));
   * 
   * message.getExchange().put(Authentication.class, new UsernamePasswordAuthenticationToken(
   * info.getIdApplicacion(), info.getPassword())); existe=true; } } if(!existe) {
   * message.getExchange().put(Authentication.class, new UsernamePasswordAuthenticationToken( "",
   * "")); } }
   */

  public void handleMessage(Message message) throws Fault {
    logger.debug("SoapAuthenticationObjectInterceptor handleMessage");
    MessageContentsList inObjects = MessageContentsList.getContentsList(message);
    String user = "";
    String password = "";

    for (Object obj : inObjects) {

      if (obj instanceof ApplicationLogin) {
        ApplicationLogin info = (ApplicationLogin) obj;

        user = info.getIdApplicacion();
        password = info.getPassword();
        logger.debug("Authenticating user: " + user);

      }
    }

    UsernamePasswordAuthenticationToken aut =
        new UsernamePasswordAuthenticationToken(user, password);
    authenticationManager.authenticate(aut);

    message.getExchange().put(Authentication.class, aut);

    QName qNampeSoap = (QName) message.get("javax.xml.ws.wsdl.operation");

    // incrementamos el numero de peticiones
    AplicacionOperacionObject appOperacion = administrationService.incrementarPeticionAccion(user,
        StringUtils.capitalize(qNampeSoap.getLocalPart()));

    byte[] xmlRequest = (byte[]) message.get("xmlRequest");
    if (appOperacion.isCapturar() && xmlRequest != null) {
      capturarPeticion(appOperacion, xmlRequest);
    }
  }

  public AuthenticationManager getAuthenticationManager() {
    return authenticationManager;
  }

  public void setAuthenticationManager(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  public boolean isAuthenticationRequired() {
    return authenticationRequired;
  }

  public void setAuthenticationRequired(boolean authenticationRequired) {
    this.authenticationRequired = authenticationRequired;
  }

  private void capturarPeticion(AplicacionOperacionObject appOperacion, byte[] data) {
    PeticionObject peticion = new PeticionObject(appOperacion.getAplicacion(),
        appOperacion.getOperacion(), new Date(), data);
    administrationService.capturarPeticion(peticion);
  }

}
