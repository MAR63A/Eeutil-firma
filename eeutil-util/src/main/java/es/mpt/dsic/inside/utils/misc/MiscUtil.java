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

package es.mpt.dsic.inside.utils.misc;

import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

// import org.bouncycastle.util.encoders.Base64;

public class MiscUtil {

  public static String ALGORITMO_HASH_MD5 = "MD5";

  /**
   * Devuelve lo que está entre la cadena "O=" y la coma (",").
   * 
   * @param cadena
   * @return
   */
  public static String getOrganizationIssuerX500Principal(String issuer) {
    int index = issuer.indexOf("O=");
    int index2 = issuer.indexOf(",", index);
    if ((index < index2)) {
      return issuer.substring(index + 2, index2);
    } else {
      return "";
    }
  }

  /**
   * Introduce un asterisco entre los caracteres // y [, o si la entrada es "//", introduce un
   * asterico al final; Ejemplo: Entrada: //[@Id=CONTENT-1c5d50a7-f5e1-4171-9d10-d9c2909fcb72]
   * Salida: //*[@Id=CONTENT-1c5d50a7-f5e1-4171-9d10-d9c2909fcb72]
   * 
   * @param xpathExpression
   * @return
   */
  public static String meteAsteriscoXPath(String xpathExpression) {

    if (!"//".contentEquals(xpathExpression)) {
      String regexp = "//\\[";
      xpathExpression = xpathExpression.replaceAll(regexp, "//*[");
    } else {
      xpathExpression = xpathExpression + "*";
    }

    return xpathExpression;
  }

  /**
   * Introduce una comilla simple entre el carácter "=" y el primer dígito,número o guión
   * encontrado. Introduce otra comilla simple entre el último dígito, número o guión encontrado y
   * el carácter "]" Ejemplo: Entrada: //*[@Id=CONTENT-1c5d50a7-f5e1-4171-9d10-d9c2909fcb72] Salida:
   * //*[@Id='CONTENT-1c5d50a7-f5e1-4171-9d10-d9c2909fcb72']
   * 
   * @param xpathExpression
   * @return
   */
  public static String meteComillasXPath(String xpathExpression) {
    String regexp = "=[a-zA-Z|0-9|\\-]+\\]";
    Pattern p = Pattern.compile(regexp);
    Matcher m = p.matcher(xpathExpression);
    if (m.find()) {
      int i = m.start();
      int j = m.end();
      if (i > -1 && j > -1) {
        xpathExpression = xpathExpression.substring(0, i + 1) + "'"
            + xpathExpression.substring(i + 1, j - 1) + "'" + xpathExpression.substring(j - 1);
      }
    }
    return xpathExpression;
  }

  /**
   * Decodifica un String en un array de bytes, dependiendo del encoding que se le pasa por
   * parámetro hace una cosa u otra
   * 
   * @param original
   * @param encoding
   * @return
   * @throws CharacterCodingException
   */
  public static byte[] decodeByEncoding(String original, String encoding)
      throws CharacterCodingException {
    byte[] decod = null;
    String encodingLowerCase = StringUtils.isNotBlank(encoding) ? encoding.toLowerCase() : "";
    if (encodingLowerCase.contains("base64")) {
      decod = Base64.decodeBase64(original.getBytes());
    } else {
      Charset charset = Charset.forName(encodingLowerCase);
      CharsetEncoder encoder = charset.newEncoder();
      CharBuffer cbSalida = CharBuffer.wrap(original.toCharArray());
      ByteBuffer bbSalida = encoder.encode(cbSalida);
      decod = bbSalida.array();
    }
    return decod;
  }

  public static String dateToString(Date d, String dateFormat) {
    if (dateFormat == null) {
      dateFormat = "dd/MM/yyyy HH:mm";
    }

    return new SimpleDateFormat(dateFormat).format(d);
  }

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
  public static String generateHash(String algoritm, InputStream is) throws Exception {
    MessageDigest digest = MessageDigest.getInstance(algoritm);
    byte[] buffer = new byte[8192];
    int read = 0;
    while ((read = is.read(buffer)) > 0) {
      digest.update(buffer, 0, read);
    }
    byte[] md5sum = digest.digest();
    BigInteger bigInt = new BigInteger(1, md5sum);
    return bigInt.toString(16);
  }

  public static void main(String[] args) {
    System.out.println(meteComillasXPath(
        meteAsteriscoXPath("//[@Id=CONTENT-1c5d50a7-f5e1-4171-9d10-d9c2909fcb72]")));
  }
}
