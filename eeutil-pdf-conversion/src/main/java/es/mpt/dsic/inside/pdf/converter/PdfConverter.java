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

package es.mpt.dsic.inside.pdf.converter;

import java.io.File;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import es.mpt.dsic.inside.pdf.exception.PdfConversionException;
import es.mpt.dsic.inside.utils.io.IOUtil;
import es.mpt.dsic.inside.utils.mime.MimeUtil;
import es.mpt.dsic.inside.utils.string.StringUtil;

@Component
public class PdfConverter {

  protected final static Log logger = LogFactory.getLog(PdfConverter.class);

  @Autowired
  OfficeToPdfConverter officeToPdfConverter;

  @Autowired
  TiffToPdfConverter tiffToPdfConverter;

  @Autowired
  GenericPdfConverter genericPdfConverter;

  @Autowired
  TcnToPdfConverter tcnToPdfConverter;

  /**
   * Convierte a PDF
   */
  public File convertir(String ipOO, String portOO, File contenido, String mimeType)
      throws PdfConversionException {
    String mime = mimeType;

    File pdf = null;

    logger.debug("Mime recibido de la peticion : " + mimeType);

    try {
      if (mime == null) {

        mime = MimeUtil.getMimeNotNull(IOUtil.getBytesFromObject(contenido));
        pdf = convertir(ipOO, portOO, contenido, mime);

      } else if (mime.contentEquals(
          "application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {

        logger.debug("INTENTANDO CONVERTIR DOCX A PDF LLAMANDO A OPENOFFICE O LIBREOFFICE");
        logger.debug("IP  : " + ipOO);
        logger.debug("PORT: " + portOO);

        pdf = officeToPdfConverter.convertOfficeToPDFByMIME(contenido, mime, ipOO, portOO);

      } else if (mime.contentEquals("text-tcn/html")) {

        pdf = tcnToPdfConverter.convertTCNToPdf(IOUtil.getBytesFromObject(contenido));

      } else if (StringUtil.contiene(mime, "text") || StringUtil.contiene(mime, "doc")
          || StringUtil.contiene(mime, "ppt") || StringUtil.contiene(mime, "ms")
          || StringUtil.contiene(mime, "msword") || StringUtil.contiene(mime, "xls")
          || StringUtil.contiene(mime, "rtf") || StringUtil.contiene(mime, "wpd")) {

        if (StringUtil.contiene(mime, "xml"))
          mime = "text/plain";

        pdf = officeToPdfConverter.convertOfficeToPDFByMIME(contenido, mime, ipOO, portOO);


      } else if (StringUtil.contiene(mime, "pdf")) {

        pdf = contenido;

      } else if (StringUtil.contiene(mime, "tiff") || StringUtil.contiene(mime, "jpg")
          || StringUtil.contiene(mime, "BMP") || StringUtil.contiene(mime, "image")
          || StringUtil.contiene(mime, "jpeg") || StringUtil.contiene(mime, "tif")
          || StringUtil.contiene(mime, "gif") || StringUtil.contiene(mime, "bmp")
          || StringUtil.contiene(mime, "gif") || StringUtil.contiene(mime, "png")) {

        if (StringUtil.contiene(mime, "tiff") || StringUtil.contiene(mime, "tif")) {
          pdf = tiffToPdfConverter.convertTiffToPDF(contenido);
        } else {
          pdf = genericPdfConverter.convertPdfGeneric(contenido, true);
        }
      } else {
        pdf = genericPdfConverter.convertPdfGeneric(contenido, false);
      }
      return pdf;
    } catch (PdfConversionException e) {
      throw e;
    } catch (Throwable t) {
      throw new PdfConversionException("Error en metodo convertir", t);
    }

  }

}
