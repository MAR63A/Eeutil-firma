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

package es.mpt.dsic.inside.utils.mime;

import java.util.Collection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import eu.medsea.mimeutil.MimeUtil2;
import eu.medsea.mimeutil.detector.MagicMimeMimeDetector;
import eu.medsea.util.EncodingGuesser;

public class MimeUtil {

  protected final static Log logger = LogFactory.getLog(MimeUtil.class);

  public static String DEFAULT_MIME = "application/octect-stream";
  public static String ZIP_MIME_2 = "application/x-zip";
  public static String ZIP_MIME_1 = "application/zip";
  public static String WORD_EXCEL_97_MIME = "application/msword";

  private static MagicMimeMimeDetector magicMimeMimeDetector;

  static MimeUtil2 mimeUtil = new MimeUtil2();

  /**
   * Obtiene el tipo mime en base al contenido de un fichero. Si no se puede obtener con ninguna
   * utilidad devuelve el mime por defecto.
   * 
   * @param data bytes de los datos.
   * @return El mime obtenido con la utilidad, si no se detecta ninguno devuelve el mime por
   *         defecto.
   */
  public static String getMimeNotNull(byte[] data) {
    String mimeType = getMimeType(data);
    logger.debug("Mime detectado por MIMEUTIL: " + mimeType);
    if (ZIP_MIME_1.equalsIgnoreCase(mimeType) || ZIP_MIME_2.equalsIgnoreCase(mimeType)
        || WORD_EXCEL_97_MIME.equalsIgnoreCase(mimeType)) {
      mimeType = OfficeAnalizer.getMimeType(data);
      logger.debug("Mime detectado por OfficeAnalizer: " + mimeType);
    } ;
    return mimeType != null ? mimeType : DEFAULT_MIME;
  }

  /**
   * Obtiene el tipo mime en base al contenido de un fichero @param data @return null si no se
   * detecta el mime. @throws
   */
  public static String getMimeType(byte[] data) {
    String resultmime;

    if (magicMimeMimeDetector == null) {
      magicMimeMimeDetector = new MagicMimeMimeDetector();
    }
    // MagicMimeMimeDetector magicMimeMimeDetector = new MagicMimeMimeDetector ();
    resultmime = getFirstMimeType(magicMimeMimeDetector.getMimeTypes(data));

    if (resultmime == null || DEFAULT_MIME.equals(resultmime)) {
      EncodingGuesser
          .setSupportedEncodings(EncodingGuesser.getCanonicalEncodingNamesSupportedByJVM());
      resultmime = getFirstMimeType(mimeUtil.getMimeTypes(data));
      String aux[] = resultmime.split(";");
      if (aux.length > 1)
        resultmime = aux[0];

    }

    return resultmime;
  }


  /**
   * Dada una colección de tipos mime, da el primero
   * 
   * @param mimeTypes
   * @return null si su tamaño es 0.
   * @throws RepositoryCmisException
   */
  private static String getFirstMimeType(Collection<?> mimeTypes) {
    String first = null;
    if (mimeTypes.size() >= 1) {
      first = mimeTypes.iterator().next() + "";
    }
    return first;
  }


}
