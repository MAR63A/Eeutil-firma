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

import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.stereotype.Component;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Document;
import es.mpt.dsic.inside.pdf.PdfUtils;
import es.mpt.dsic.inside.pdf.exception.PdfConversionException;
import es.mpt.dsic.inside.utils.file.FileUtil;
import es.mpt.dsic.inside.utils.io.IOUtil;

@Component
public class GenericPdfConverter {

  private static final String GENERIC_PREFIX = "generic";

  public File convertPdfGeneric(File inputFile, boolean isPictureFile)
      throws PdfConversionException, IOException {
    Document pdfDocument = new Document();
    FileOutputStream fos = null;
    PdfWriter writer = null;
    String filePath = null;
    try {
      filePath = FileUtil.createFilePath(GENERIC_PREFIX);
      fos = new FileOutputStream(filePath);

      writer = PdfWriter.getInstance(pdfDocument, fos);
      writer.open();
      pdfDocument.open();
      /**
       * Proceed if the file given is a picture file
       */
      if (isPictureFile) {
        java.awt.Image awtImage =
            Toolkit.getDefaultToolkit().createImage(IOUtil.getBytesFromObject(inputFile));
        Image img = Image.getInstance(awtImage, null);

        float imgHeight = img.getPlainHeight() + 100;
        float imgWidth = img.getPlainWidth() + 150;
        Rectangle mipagina = new Rectangle(imgWidth, imgHeight);
        if (mipagina.getHeight() > PageSize.A4.getHeight()
            || mipagina.getWidth() > PageSize.A4.getWidth()) {
          pdfDocument = new Document(mipagina, 100, 30, 30, 30);
        } else {
          pdfDocument = new Document();
        }
        writer = PdfWriter.getInstance(pdfDocument, fos);
        writer.open();
        pdfDocument.open();
        pdfDocument.add(img);
      }

      else {
        pdfDocument.add(new Paragraph(new String(IOUtil.getBytesFromObject(inputFile))));
      }

    } catch (Throwable t) {
      throw new PdfConversionException("Error convertPdfGeneric", t);
    } finally {
      PdfUtils.close(pdfDocument);
      PdfUtils.close(writer);
      FileUtil.close(fos);
    }
    return new File(filePath);
  }

}
