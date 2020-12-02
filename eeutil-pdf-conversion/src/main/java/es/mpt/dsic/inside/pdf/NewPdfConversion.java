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

import java.awt.print.PageFormat;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import es.mpt.dsic.inside.pdf.exception.PdfConversionException;
import es.mpt.dsic.inside.utils.file.FdUtils;
import es.mpt.dsic.inside.utils.file.FileUtil;

@Service("newPdfConversion")
public class NewPdfConversion {

  protected static final Log logger = LogFactory.getLog(NewPdfConversion.class);

  private static final String REDUCED_PREFIX = "newreduced";


  public File copiaPDFReducidoStamper(File doc) throws PdfConversionException {
    return copiaPDFReducidoStamper(doc, null);
  }

  /**
   * Convierte una lista de documentos PDF a otro documento PDF en el que se concatenan todos, por
   * orden, y además se reduce el tamanio de las paginas.
   * 
   * @param listaDocs
   * @param options
   * @return
   * @throws Exception
   */
  public File copiaPDFReducidoStamper(File filePdf, PdfOptions options)
      throws PdfConversionException {
    File fileOut = null;
    try {

      if (options == null) {
        options = PdfOptions.createDefault();
        options.setPrintPageNumbers(false);
        options.setPagePercent(0);
      }

      logger.debug("Descriptores abiertos inicio copiaPDFReducidoStamper: " + FdUtils.getFdOpen());

      // byte[] filecopy = copyPdf(filePdf);
      // fileOut = resizePdf(filecopy, options);

      fileOut = resizePdf(FileUtils.readFileToByteArray(filePdf), options);

      logger.debug("Descriptores abiertos despues resizePdf: " + FdUtils.getFdOpen());

    } catch (BadPdfFormatException e) {
      logger.error("Error al obtener PDF reducido", e);
      throw new PdfConversionException("Error al obtener PDF reducido", e);
    } catch (FileNotFoundException e) {
      logger.error("Error al obtener PDF reducido", e);
      throw new PdfConversionException("Error al obtener PDF reducido", e);
    } catch (DocumentException e) {
      logger.error("Error al obtener PDF reducido", e);
      throw new PdfConversionException("Error al obtener PDF reducido", e);
    } catch (IOException e) {
      logger.error("Error al obtener PDF reducido", e);
      throw new PdfConversionException("Error al obtener PDF reducido", e);
    } catch (Exception e) {
      logger.error("Error al obtener PDF reducido", e);
      throw new PdfConversionException("Error al obtener PDF reducido", e);
    }
    return fileOut;
  }

  public byte[] copyPdf(File filePdf) throws DocumentException, IOException {
    Document doc = null;
    PdfSmartCopy copy = null;
    PdfReader reader = null;
    ByteArrayOutputStream salida = new ByteArrayOutputStream();
    try {
      logger.debug("[copyPdf] Descriptores abiertos inicio copyPdf :" + FdUtils.getFdOpen());
      doc = new Document();

      copy = new PdfSmartCopy(doc, salida);
      copy.setFullCompression();
      copy.setCompressionLevel(9);
      doc.open();

      logger.debug("[copyPdf] Descriptores abiertos despues doc.open :" + FdUtils.getFdOpen());

      int totalPages = getTotalPages(filePdf);

      logger.debug("[copyPdf] Descriptores abiertos antes getStamper :" + FdUtils.getFdOpen());
      byte[] stamperBytes = getStamper(filePdf);
      logger.debug("[copyPdf] Descriptores abiertos despues getStamper :" + FdUtils.getFdOpen());

      reader = new PdfReader(stamperBytes);

      for (int i = 0; i < totalPages; i++) {
        PdfImportedPage page = copy.getImportedPage(reader, i + 1);
        copy.addPage(page);
        logger.debug("[copyPdf] Descriptores abiertos despues copy.getImportedPage bucle :"
            + FdUtils.getFdOpen());

        logger.debug("CurrentPageNumber:" + copy.getCurrentPageNumber());
        logger.debug("CurrentDocumentSize:" + copy.getCurrentDocumentSize());
      }

    } finally {
      PdfUtils.close(reader);
      logger.debug("Descriptores abiertos despues cerrar reader :" + FdUtils.getFdOpen());
      PdfUtils.close(doc);
      logger.debug("Descriptores abiertos despues cerrar doc :" + FdUtils.getFdOpen());

      PdfUtils.close(copy);
      logger.debug("Descriptores abiertos despues cerrar copy :" + FdUtils.getFdOpen());
      FileUtil.close(salida);
      logger.debug("Descriptores abiertos despues cerrar salida :" + FdUtils.getFdOpen());
    }
    return salida.toByteArray();
  }

  private int getTotalPages(File filePdf) throws IOException {
    PdfReader reader = null;
    int totalPages = 0;
    try {
      reader = new PdfReader(filePdf.getAbsolutePath());
      totalPages = reader.getNumberOfPages();
    } finally {
      PdfUtils.close(reader);
    }
    return totalPages;
  }

  private byte[] getStamper(File filePdf) throws IOException, DocumentException {
    PdfStamper stamper = null;
    ByteArrayOutputStream baos = null;
    PdfReader reader = null;
    try {
      logger.debug("[getStamper] Descriptores abiertos inicio :" + FdUtils.getFdOpen());

      reader = new PdfReader(filePdf.getAbsolutePath());

      baos = new ByteArrayOutputStream();
      stamper = new PdfStamper(reader, baos);
      logger.debug("[getStamper] Descriptores abiertos despues new PdfStamper bucle :"
          + FdUtils.getFdOpen());

      stamper.setFullCompression();

      stamper.setFormFlattening(true);
    } finally {
      FileUtil.close(baos);
      logger.debug("[getStamper] Descriptores abiertos despues close baos :" + FdUtils.getFdOpen());

      PdfUtils.close(stamper);
      logger.debug(
          "[getStamper] Descriptores abiertos despues cerrar stamper :" + FdUtils.getFdOpen());

      PdfUtils.close(reader);
      logger.debug(
          "[getStamper] Descriptores abiertos despues cerrar reader :" + FdUtils.getFdOpen());
    }
    return baos.toByteArray();
  }


  private int obtenerOrientacionPagina(PdfReader reader, int pagina) {
    Rectangle rectangle = reader.getPageSizeWithRotation(pagina);

    if (rectangle.getHeight() >= rectangle.getWidth())
      return PageFormat.PORTRAIT;
    else
      return PageFormat.LANDSCAPE;

  }



  public class Rotate extends PdfPageEventHelper {

    protected PdfNumber orientation = PdfPage.PORTRAIT;

    public void setOrientation(PdfNumber orientation) {
      this.orientation = orientation;
    }

    @Override
    public void onStartPage(PdfWriter writer, Document document) {
      writer.addPageDictEntry(PdfName.ROTATE, orientation);
    }
  }

  private File resizePdf(byte[] file, PdfOptions options) throws IOException, DocumentException {
    Document document = null;
    PdfWriter writer = null;
    PdfReader reader = null;
    String fileout = null;
    try {
      fileout = FileUtil.createFilePath(REDUCED_PREFIX) + ".pdf";

      reader = new PdfReader(file);

      document = new Document();
      writer = PdfWriter.getInstance(document, new FileOutputStream(fileout));
      writer.setFullCompression();
      writer.setCompressionLevel(9);
      document.open();



      int n = reader.getNumberOfPages();
      Image img;
      for (int p = 1; p <= n; p++) {

        // inicializar el write a vertical siempre
        Rotate eventInicializador = new Rotate();
        eventInicializador.setOrientation(PdfPage.PORTRAIT);
        writer.setPageEvent(eventInicializador);

        // calculamos la orientacion de la pagina
        int orientation = obtenerOrientacionPagina(reader, p);

        // si la horientacion es horizontal se cambia en el write
        if (orientation == PageFormat.LANDSCAPE) {
          Rotate event = new Rotate();
          event.setOrientation(PdfPage.LANDSCAPE);
          writer.setPageEvent(event);
        }


        // img = getImgImportPage(reader, writer, p, document);
        img = getImgImportPage(reader, writer, p, document, orientation);

        // img.setAbsolutePosition(options.getPagePositionX(), options.getPagePositionY());
        img.setAlignment(Image.ALIGN_TOP);

        // calculatePercent(options.getPagePercent(), img, document);
        // calculatePercent(options.getPagePercent(), img, document, orientation);
        colocarImagen(img, orientation);

        /*
         * document.setPageSize(reader.getPageSize(p)); document.newPage();
         * writer.getDirectContent().addImage(img); writer.freeReader(reader);
         */

        document.newPage();
        document.add(img);
        // writer.getDirectContent().addImage(img);
        writer.freeReader(reader);

        logger.debug("CurrentPageNumber:" + writer.getCurrentPageNumber());
        logger.debug("CurrentDocumentSize:" + writer.getCurrentDocumentSize());
      }

    } finally {
      // PdfUtils.close(writer);
      PdfUtils.close(reader);
      PdfUtils.close(document);
      PdfUtils.close(writer);
    }
    return new File(fileout);
  }

  private Image getImgImportPage(PdfReader reader, PdfWriter writer, int page, Document document)
      throws BadElementException {
    PdfImportedPage importPage = writer.getImportedPage(reader, page);
    Image img = Image.getInstance(importPage);
    int rotation = importPage.getRotation();

    if (rotation == 270 || rotation == 90) {
      if (img.getWidth() == PageSize.A4.getWidth() && img.getHeight() == PageSize.A4.getHeight()) {
        document.setPageSize(PageSize.A4.rotate());
      } else {
        document.setPageSize(PageSize.A4);
      }
    } else {
      if (img.getWidth() > img.getHeight()) {
        if (img.getWidth() < PageSize.A4.getWidth() && img.getHeight() < PageSize.A4.getHeight()) {
          document.setPageSize(PageSize.A4);
        } else {
          document.setPageSize(PageSize.A4.rotate());
        }
      } else {
        document.setPageSize(PageSize.A4);
      }
    }

    switch (rotation) {
      case 90: {
        img.setRotationDegrees(270);
        break;
      }
      case 180: {
        img.setRotationDegrees(180);
        break;
      }
      case 270: {
        img.setRotationDegrees(90);
        break;
      }
      default: {
        img.setRotationDegrees(0);
        break;
      }
    }
    img.setCompressionLevel(9);
    return img;
  }

  private Image getImgImportPage(PdfReader reader, PdfWriter writer, int page, Document document,
      int orientation) throws BadElementException {
    PdfImportedPage importPage = writer.getImportedPage(reader, page);
    Image img = Image.getInstance(importPage);
    int rotation = importPage.getRotation();
    //
    // if (rotation == 270
    // || rotation == 90) {
    // if (img.getWidth() == PageSize.A4.getWidth()
    // && img.getHeight() == PageSize.A4.getHeight()) {
    // document.setPageSize(PageSize.A4.rotate());
    // } else {
    // document.setPageSize(PageSize.A4);
    // }
    // } else {
    // if (img.getWidth() > img.getHeight()) {
    // if (img.getWidth() < PageSize.A4.getWidth()
    // && img.getHeight() < PageSize.A4.getHeight()) {
    // document.setPageSize(PageSize.A4);
    // } else {
    // document.setPageSize(PageSize.A4.rotate());
    // }
    // } else {
    // document.setPageSize(PageSize.A4);
    // }
    // }


    if (orientation == PageFormat.LANDSCAPE) {
      document.setPageSize(PageSize.A4.rotate());
    } else if (orientation == PageFormat.PORTRAIT) {
      document.setPageSize(PageSize.A4);
    }


    switch (rotation) {
      case 90: {
        img.setRotationDegrees(270);
        break;
      }
      case 180: {
        img.setRotationDegrees(180);
        break;
      }
      case 270: {
        img.setRotationDegrees(90);
        break;
      }
      default: {
        img.setRotationDegrees(0);
        break;
      }
    }
    img.setCompressionLevel(9);
    return img;
  }



  private void calculatePercent(float pagePercent, Image img, Document document) {
    float retorno = PdfOptions.DEFAULT_PAGE_PERCENT;
    if (pagePercent == 0) {
      if (img.getScaledWidth() > document.getPageSize().getWidth()
          || img.getScaledHeight() > document.getPageSize().getHeight()) {
        float newPercentWidth = (document.getPageSize().getWidth() - 60) / img.getWidth();
        float newPercentHeight = (document.getPageSize().getHeight() - 120) / img.getHeight();
        if (newPercentWidth <= newPercentHeight) {
          retorno = newPercentWidth * 100;
        } else {
          retorno = newPercentHeight * 100;
        }
      }
    } else {
      retorno = pagePercent;
    }
    img.scalePercent(retorno);
  }


  private void calculatePercent(float pagePercent, Image img, Document document, int orientation) {
    float retorno = PdfOptions.DEFAULT_PAGE_PERCENT;
    if (pagePercent == 0) {
      if (img.getScaledWidth() > document.getPageSize().getWidth()
          || img.getScaledHeight() > document.getPageSize().getHeight()) {

        // Por defecto dejamos calculo para si la orientacion es vertical (orientation
        // ==PageFormat.PORTRAIT)
        float newPercentWidth = (document.getPageSize().getWidth() - 60) / img.getWidth();
        float newPercentHeight = (document.getPageSize().getHeight() - 120) / img.getHeight();

        // si la orientacion es horizontal se cambia el calculo
        if (orientation == PageFormat.LANDSCAPE) {// no se como calcular esto. le ponemos a 0.57 un
                                                  // tamanio que entra sin ponerse encima de csv
                                                  // newPercentWidth =
                                                  // (document.getPageSize().getHeight() - 60)/
                                                  // img.getHeight();
                                                  // newPercentHeight =
                                                  // (document.getPageSize().getWidth() - 120)/
                                                  // img.getWidth();
          newPercentWidth = 0.57f;
          newPercentHeight = 0.57f;
        }


        if (newPercentWidth <= newPercentHeight) {
          retorno = newPercentWidth * 100;
        } else {
          retorno = newPercentHeight * 100;
        }
      }
    } else {
      retorno = pagePercent;
    }
    img.scalePercent(retorno);
  }


  private void colocarImagen(Image img, int orientation) {

    if (orientation == PageFormat.LANDSCAPE) {
      // horizontal
      img.scaleToFit(770, 523);
      float offsetX = (770 - img.getScaledWidth()) / 2;
      float offsetY = (523 - img.getScaledHeight()) / 2;
      img.setAbsolutePosition(40 + offsetX, 70 + offsetY);
    } else {
      // vertical
      img.scaleToFit(523, 770);
      float offsetX = (523 - img.getScaledWidth()) / 2;
      float offsetY = (770 - img.getScaledHeight()) / 2;
      img.setAbsolutePosition(40 + offsetX, 55 + offsetY);
    }


  }

  public File shrinkPdf(File src, PdfOptions options) throws IOException, DocumentException {
    String fileout = FileUtil.createFilePath(REDUCED_PREFIX) + ".pdf";
    PdfReader reader = null;
    PdfStamper stamper = null;
    try {
      reader = new PdfReader(src.getAbsolutePath());
      stamper = new PdfStamper(reader, new FileOutputStream(fileout));

      int n = reader.getNumberOfPages();

      float percentage = options.getPagePercent() / 100;
      for (int p = 1; p <= n; p++) {
        stamper.setRotateContents(false);

        float offsetX = (reader.getPageSize(p).getWidth() * (1 - percentage)) / 2;
        float offsetY = (reader.getPageSize(p).getHeight() * (1 - percentage)) / 2;
        stamper.getUnderContent(p).setLiteral(
            String.format("\nq %s 0 0 %s %s %s cm\nq\n", percentage, percentage, offsetX, offsetY));
        stamper.getOverContent(p).setLiteral("\nQ\nQ\n");
      }
    } finally {
      stamper.close();
      reader.close();
    }
    return new File(fileout);
  }

}
