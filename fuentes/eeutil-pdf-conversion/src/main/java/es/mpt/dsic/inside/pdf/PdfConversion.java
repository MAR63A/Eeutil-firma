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

package es.mpt.dsic.inside.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import es.mpt.dsic.inside.pdf.exception.PdfConversionException;
import es.mpt.dsic.inside.utils.file.FileUtil;
import es.mpt.dsic.inside.utils.io.IOUtil;


@Service("pdfConversion")
@Deprecated
public class PdfConversion {

  protected final static Log logger = LogFactory.getLog(PdfConversion.class);

  private static final String REDUCED_PREFIX = "reduced";


  /**
   * A�ade un documento PDF en una determinada escala a otro documento ya creado.
   * 
   * @param doc Documento ya creado y abierto.
   * @param baos para construir el PdfReader necesario para que no se pierdan los campos de las
   *        plantillas
   * @param pw Writer donde escribiremos el nuevo documento.
   * @param readerEntrada documento que queremos escribir.
   * @param percent Porcentaje que deseamos para nuestro nuevo documento.
   * @param positionX Coordenada X de la posici�n absoluta donde vamos a estampar cada p�gina del
   *        documento.
   * @param positionY Coordenada Y de la posici�n absoluta donde vamos a estampar cada p�gina del
   *        documento.
   * @throws IOException
   * @throws DocumentException
   * @throws Exception
   */
  public void addPdfScaled(Document doc,
      // ByteArrayOutputStream baos,
      File entrada, String salidaPath, PdfWriter pw, PdfReader readerEntrada, PdfOptions options,
      PageNumberInfo pageNumberInfo) throws PdfConversionException, DocumentException, IOException {

    PdfStamper stamper = null;
    PdfReader readerForm = null;
    FileOutputStream salida = null;
    try {
      salida = new FileOutputStream(salidaPath);
      stamper = new PdfStamper(readerEntrada, salida);
      stamper.setFormFlattening(true);

      readerForm = PdfUtils.unlockPdf(new PdfReader(IOUtil.getBytesFromObject(entrada)));

      int n = readerEntrada.getNumberOfPages();

      for (int i = 1; i <= n; i++) {

        PdfImportedPage page = pw.getImportedPage(readerForm, i);

        Image img = Image.getInstance(page);


        img.scalePercent(options.getPagePercent());
        img.setAlignment(Image.ALIGN_CENTER);
        img.setAbsolutePosition(options.getPagePositionX(), options.getPagePositionY());

        doc.setPageSize(readerEntrada.getPageSize(i));
        doc.newPage();
        pw.getDirectContent().addImage(img);
        pw.freeReader(readerForm);

        if (options.isPrintPageNumbers()) {
          float x = options.getPageSepHoriz();
          float y = options.getPageSepVerti();

          if (options.getPageSepHoriz() < 0f) {
            x = page.getWidth() + options.getPageSepHoriz();
          }

          if (options.getPageSepVerti() < 0f) {
            y = page.getHeight() + options.getPageSepVerti();
          }

          int actualPage = i;
          int totalPages = n;

          if (pageNumberInfo != null) {
            actualPage = pageNumberInfo.getNumberLastPage() + i;
            totalPages = pageNumberInfo.getNumberTotalPages();
          }
          // add page numbers
          ColumnText.showTextAligned(pw.getDirectContent(), Element.ALIGN_CENTER,
              new Phrase(String.format("page %d of %d", actualPage, totalPages)), x, y, 0);

          // 297.5f, 28, 0);
        }

      }
    } catch (Throwable t) {
      throw new PdfConversionException("Error addPdfScaled", t);
    } finally {
      PdfUtils.close(stamper);
      PdfUtils.close(readerForm);
      salida.close();
    }
  }

}

