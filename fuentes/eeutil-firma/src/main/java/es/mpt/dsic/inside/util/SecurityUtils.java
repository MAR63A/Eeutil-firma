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

package es.mpt.dsic.inside.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import es.mpt.dsic.inside.util.firma.TextosErroresFirma;

public class SecurityUtils {

  protected static final Log logger = LogFactory.getLog(SecurityUtils.class);

  private static TextosErroresFirma textosErroresFirma;

  /**
   * Metodo que valida la huella y el argoritmo huella de un fichero.
   * 
   * Utilizado para la validaci�n del SIP recibido en el servicio SipBusinessService.
   * 
   * @param algoritm String
   * @param file File
   * @param huella String
   * @return boolean
   * @throws UtilException
   */
  public static void validateAlgorithm(String algoritm, InputStream is, String huella)
      throws SecurityException {
    try {
      textosErroresFirma = TextosErroresFirma.getInstance();

      MessageDigest digest = MessageDigest.getInstance(algoritm);
      byte[] buffer = new byte[8192];
      int read = 0;
      while ((read = is.read(buffer)) > 0) {
        digest.update(buffer, 0, read);
      }
      byte[] md5sum = digest.digest();
      BigInteger bigInt = new BigInteger(1, md5sum);
      String output = bigInt.toString(16);
      logger.debug("Huella del fichero: " + output + " . Algoritmo: " + algoritm);
      if (!huella.equals(output)) {
        logger.error("Error al validar algorirtmo");
        throw new SecurityException(textosErroresFirma.getProperty(Constantes.HUELLA_NO_VALIDA));
      }
    } catch (NoSuchAlgorithmException e) {
      logger.error("Error al validar algorirtmo");
      throw new SecurityException(
          textosErroresFirma.getProperty(Constantes.ALGORITMO_HUELLA_NO_VALIDA));
    } catch (IOException e) {
      logger.error("Error al validar algorirtmo");
      throw new SecurityException(e.getMessage(), e);
    }
  }

}
