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

package es.mpt.dsic.inside.security.wss4j;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ws.security.WSPasswordCallback;


public class ServerAuthPasswordCallback implements CallbackHandler {

  protected static final Log logger = LogFactory.getLog(ServerAuthPasswordCallback.class);


  private String passwordCertificado;


  @Override
  public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
    logger.debug("Inicio ServerCallback");

    for (int i = 0; i < callbacks.length; i++) {
      WSPasswordCallback pwcb = (WSPasswordCallback) callbacks[i];
      String id = pwcb.getIdentifier();
      int usage = pwcb.getUsage();

      switch (usage) {
        case WSPasswordCallback.UNKNOWN:
          logger.debug("ServerCallback UNKNOWN");
          break;
        case WSPasswordCallback.SIGNATURE:
          logger.debug("ServerCallback SIGNATURE");
          pwcb.setPassword(passwordCertificado);
          break;
      }
    }
    logger.debug("Fin ServerCallback");
  }



  public String getPasswordCertificado() {
    return passwordCertificado;
  }



  public void setPasswordCertificado(String passwordCertificado) {
    this.passwordCertificado = passwordCertificado;
  }


}
