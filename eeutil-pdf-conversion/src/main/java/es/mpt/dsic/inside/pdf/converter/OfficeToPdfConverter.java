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
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import es.mpt.dsic.inside.pdf.exception.PdfConversionException;
import es.mpt.dsic.inside.utils.file.FdUtils;
import es.mpt.dsic.inside.utils.file.FileUtil;

@Component
public class OfficeToPdfConverter {

  protected final static Log logger = LogFactory.getLog(OfficeToPdfConverter.class);

  private static final String OFFICE_PREFIX = "office";

  public File convertOfficeToPDFByMIME(File entrada, String fileExtension, String ipOOffice,
      String portOOfice) throws PdfConversionException, IOException {
    if (StringUtils.isNotEmpty(ipOOffice) && StringUtils.isNotEmpty(portOOfice)) {
      int port = Integer.parseInt(portOOfice);
      return jodConverter(entrada, fileExtension, ipOOffice, port);
    } else {
      throw new PdfConversionException(
          "Debe definir correctamente el servicio de conversion office");
    }
  }

  /**
   * JODConverter automates all conversions supported by OpenOffice.org, including
   * 
   * Microsoft Office to OpenDocument, and viceversa o Word to OpenDocument Text (odt); OpenDocument
   * Text (odt) to Word o Excel to OpenDocument Spreadsheet (ods); OpenDocument Spreadsheet (ods) to
   * Excel o PowerPoint to OpenDocument Presentation (odp); OpenDocument Presentation (odp) to
   * PowerPoint Any format to PDF o OpenDocument (Text, Spreadsheet, Presentation) to PDF o Word to
   * PDF; Excel to PDF; PowerPoint to PDF o RTF to PDF; WordPerfect to PDF; ... And more o
   * OpenDocument Presentation (odp) to Flash; PowerPoint to Flash o RTF to OpenDocument;
   * WordPerfect to OpenDocument o Any format to HTML (with limitations) o Support for
   * OpenOffice.org 1.0 and old StarOffice formats o .
   * 
   * @throws IOException
   */
  private File jodConverter(File entrada, String fileExtension, String ipOOffice, int portOOfice)
      throws PdfConversionException, IOException {
    FileOutputStream out = null;
    FileInputStream fis = null;
    String filePath = FileUtil.createFilePath(OFFICE_PREFIX);
    FileDescriptor fdFis = null;
    FileDescriptor fdOut = null;
    try {
      logger.debug("Descriptores abiertos inicio jodConverter: " + FdUtils.getFdOpen());

      fis = new FileInputStream(entrada);
      fdFis = fis.getFD();

      out = new FileOutputStream(filePath);
      fdOut = out.getFD();

      OpenOfficeConnection connection = new SocketOpenOfficeConnection(ipOOffice, portOOfice);

      try {
        connection.connect();
      } catch (Exception e) {
        throw new PdfConversionException(
            "No se puede abrir la conexi�n con el servidor de openoffice", e);
      }
      DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
      DefaultDocumentFormatRegistry registry = new DefaultDocumentFormatRegistry();
      // DocumentFormat inFormat =
      // registry.getFormatByFileExtension("docx");
      DocumentFormat inFormat = registry.getFormatByMimeType(fileExtension);
      DocumentFormat outFormat = registry.getFormatByFileExtension("pdf");

      converter.convert(fis, inFormat, out, outFormat);
      connection.disconnect();

    } catch (PdfConversionException e) {
      throw e;
    } catch (Throwable t) {
      throw new PdfConversionException("Error convirtiendo a PDF from Office", t);
    } finally {
      FileUtil.close(fis);
      FileUtil.close(out);
    }
    logger.debug("Descriptores abiertos fin jodConverter: " + FdUtils.getFdOpen());
    return new File(filePath);
  }

}
