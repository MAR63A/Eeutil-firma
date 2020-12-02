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

package es.mpt.dsic.inside.util.firma.ext;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import es.gob.afirma.core.AOInvalidFormatException;
import es.gob.afirma.core.signers.AOSigner;
import es.gob.afirma.core.signers.AOSignerFactory;
import es.gob.afirma.signers.pades.AOPDFSigner;
import es.mpt.dsic.inside.utils.io.IOUtil;
import es.mpt.dsic.inside.utils.xml.XMLUtil;

public class SignatureUtil {

  protected static final Log logger = LogFactory.getLog(SignatureUtil.class);

  public static boolean checkIsSign(Object source, String formato) {
    logger.debug("checkIsSign init");
    boolean parseError = false;
    boolean isSigned = false;

    Document dom = null;
    try {
      dom = XMLUtil.getDOMDocument(source, true);
      // Si se produce alguna excepción al parsear, significará que no es un XML y por tanto no será
      // una firma XML.
    } catch (ParserConfigurationException pce) {
      parseError = true;
    } catch (SAXException se) {
      parseError = true;
    } catch (IOException ioe) {
      parseError = true;
    } catch (Exception e) {
      logger.debug("ERROR: ", e);
    }

    // Si se ha podido parsear el documento, comprobamos que se trata de una firma XML.
    if (!parseError) {
      logger.debug("not parseError");
      isSigned = isDocumentXMLSign(dom);
    } else {
      try {
        logger.debug("parseError");
        isSigned = isOtherSign(IOUtil.getBytesFromObject(source), formato);
      } catch (IOException e) {
        logger.error("Error al recuperar firma:" + e);
        isSigned = false;
      } catch (AOInvalidFormatException e) {
        logger.error("Error al recuperar firma:" + e);
        isSigned = false;
      }
    }
    logger.debug("checkIsSign end");
    return isSigned;
  }


  /**
   * Consideraremos que el árbol DOM representa a una firma XML con el documento implícito cuando
   * contenga el primer nodo con el documento firmado y, al mismo nivel, un nodo Signature.
   * 
   * @param dom árbol DOM de un documento XML.
   * @return true si representa una firma XML, false en caso contrario.
   */
  private static boolean isDocumentXMLSign(Document dom) {
    logger.debug("isDocumentXMLSign init");
    // comprobamos que tenga el nodo con el documento firmado y el nodo Signature.
    // Estos dos nodos tienen que colgar del nodo padre del documento.
    Element elementRoot = dom.getDocumentElement();

    // Obtenemos el primer hijo del nodo Root.
    Node child = elementRoot.getFirstChild() != null ? elementRoot.getFirstChild() : elementRoot;
    boolean lastChild = false;
    boolean tieneDocFirmado = false;
    boolean tieneSignature = false;

    boolean esXMLSign = false;

    while (!lastChild && !esXMLSign && child != null) {
      // Si no hemos encontrado el nodo que contiene al documento firmado, miramos si es el actual.
      if (!tieneDocFirmado) {
        tieneDocFirmado = XMLUtil.contieneIdEncoding(child);
      }
      // Si no hemos encontrado el nodo que contiene la firma, miramos si es el actual.
      if (!tieneSignature) {
        // tieneSignature = child.getNodeName().equalsIgnoreCase("ds:Signature");
        tieneSignature = child.getLocalName() != null && child.getLocalName().equals("Signature");
      }

      if (child == elementRoot.getLastChild()) {
        lastChild = true;
      } else {
        child = child.getNextSibling();
      }
      esXMLSign = tieneDocFirmado && tieneSignature;
    }
    logger.debug("isDocumentXMLSign end");
    return esXMLSign;
  }

  /**
   * Comprueba si unos bytes se corresponden con los de una firma electŕonica (que no sea firma
   * PDF).
   * 
   * @param bytesFirma
   * @return true si lo es, false en caso contrario.
   * @throws IOException
   * @throws AOInvalidFormatException
   */
  private static boolean isOtherSign(byte[] bytesFirma, String format)
      throws IOException, AOInvalidFormatException {
    logger.debug("isOtherSign init");

    es.gob.afirma.core.signers.AOSigner signer = obtenerSigner(format);

    if (!(signer instanceof AOPDFSigner) && signer.isSign(bytesFirma)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Se obtiene un objeto para manipular la firma.
   * 
   * @param bytes de la firma
   * @return Instancia de un objeto para manipular la firma.
   * @throws IOException
   */
  private static AOSigner obtenerSigner(String format) throws IOException {
    AOSigner signer = null;
    signer = AOSignerFactory.getSigner(format);
    return signer;
  }

  public static String traducirAFormatoValido(String format) {
    String formatoValido = format;

    if ("XAdES Manifest".equalsIgnoreCase(format.trim())) {
      formatoValido = format.toLowerCase().replace("manifest", "Detached");
    }

    return formatoValido;
  }


  public static boolean esXadesManifest(String format) {
    boolean isXadesManifest = false;

    if ("XAdES Manifest".equalsIgnoreCase(format.trim())) {
      isXadesManifest = true;
    }

    return isXadesManifest;
  }

}
