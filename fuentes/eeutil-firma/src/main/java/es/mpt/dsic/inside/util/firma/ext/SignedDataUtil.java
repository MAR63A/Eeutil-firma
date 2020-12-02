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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import es.gob.afirma.core.signers.AOSigner;
import es.gob.afirma.core.signers.AOSignerFactory;
import es.gob.afirma.core.util.tree.AOTreeModel;
import es.gob.afirma.signers.pades.AOPDFSigner;
import es.mpt.dsic.inside.utils.xml.XMLUtil;

public class SignedDataUtil {

  protected static final Log logger = LogFactory.getLog(SignedDataUtil.class);
  /**
   * Método para detectar si un documento es, en realidad, una firma (una firma que no sea firma
   * PDF).
   * 
   * @param document Documento a comprobar
   * @return true si se trata de una firma (no PDF), false en caso contrario.
   * @throws IOException en caso de no poder parsear el documento por haber algún problema con los
   *         bytes de entrada.
   */
  /*
   * public static boolean checkIsSign (Object source) { logger.debug("checkIsSign init"); boolean
   * parseError = false; boolean isSigned = false;
   * 
   * Document dom = null; try { dom = XMLUtil.getDOMDocument(source, true); // Si se produce alguna
   * excepción al parsear, significará que no es un XML y por tanto no será una firma XML. } catch
   * (ParserConfigurationException pce) { parseError = true; } catch (SAXException se){ parseError =
   * true; } catch (IOException ioe) { parseError = true; } catch (Exception e) {
   * logger.debug("ERROR: " , e); }
   * 
   * // Si se ha podido parsear el documento, comprobamos que se trata de una firma XML. if
   * (!parseError) { logger.debug("not parseError"); isSigned = isDocumentXMLSign(dom); } else { try
   * { logger.debug("parseError"); isSigned = isOtherSign(getBytes(source)); } catch (IOException e)
   * { isSigned = false; } } logger.debug("checkIsSign end"); return isSigned; }
   */


  /**
   * Consideraremos que el árbol DOM representa a una firma XML con el documento implícito cuando
   * contenga el primer nodo con el documento firmado y, al mismo nivel, un nodo Signature.
   * 
   * @param dom árbol DOM de un documento XML.
   * @return true si representa una firma XML, false en caso contrario.
   */
  /*
   * private static boolean isDocumentXMLSign (Document dom) { logger.debug
   * ("isDocumentXMLSign init"); //comprobamos que tenga el nodo con el documento firmado y el nodo
   * Signature. //Estos dos nodos tienen que colgar del nodo padre del documento. Element
   * elementRoot = dom.getDocumentElement();
   * 
   * // Obtenemos el primer hijo del nodo Root. Node child = elementRoot.getFirstChild(); boolean
   * lastChild = false; boolean tieneDocFirmado = false; boolean tieneSignature = false;
   * 
   * boolean esXMLSign = false;
   * 
   * while (!lastChild && !esXMLSign) { // Si no hemos encontrado el nodo que contiene al documento
   * firmado, miramos si es el actual. if (!tieneDocFirmado) { tieneDocFirmado =
   * contieneIdEncoding(child); } // Si no hemos encontrado el nodo que contiene la firma, miramos
   * si es el actual. if (!tieneSignature) { //tieneSignature =
   * child.getNodeName().equalsIgnoreCase("ds:Signature"); tieneSignature = child.getLocalName() !=
   * null && child.getLocalName().equals("Signature"); }
   * 
   * if (child == elementRoot.getLastChild()) { lastChild = true; } else { child =
   * child.getNextSibling(); } esXMLSign = tieneDocFirmado && tieneSignature; } logger.debug
   * ("isDocumentXMLSign end"); return esXMLSign; }
   */

  /**
   * Comprueba que un nodo XML tenga el atributo Id y el atributo Encoding.
   * 
   * @param nodo
   * @return
   */
  /*
   * private static boolean contieneIdEncoding (Node nodo) {
   * 
   * NamedNodeMap atributos = nodo.getAttributes();
   * 
   * // Comprobamos que tenga el atributo Id y el atributo Encoding: int i=0; boolean encontrados =
   * false; boolean idFound = false; boolean encodingFound = false;
   * 
   * while (atributos != null && i < atributos.getLength() && !encontrados) { if
   * (atributos.item(i).getNodeName().equalsIgnoreCase("id")){ idFound = true; } else if
   * (atributos.item(i).getNodeName().equalsIgnoreCase("encoding") &&
   * atributos.item(i).getNodeValue().contains("base64")) { encodingFound = true; }
   * 
   * encontrados = idFound && encodingFound; i++; }
   * 
   * return encontrados; }
   * 
   * /** Obtiene los bytes a partir de un objeto.
   * 
   * @param source Objeto del que se quieren obtener los bytes.
   * 
   * @return array de bytes
   * 
   * @throws IOException si no se puede leer la fuente.
   */
  /*
   * private static byte[] getBytes(Object source) throws IOException{ byte[] ret = null;
   * InputStream is = null; if (source instanceof byte[]) { byte[] s = (byte[]) source; ret = s;
   * }else if (source instanceof File) { File f = (File) source; is = new FileInputStream(f);
   * ByteArrayOutputStream bout = new ByteArrayOutputStream (); IOUtils.copyLarge(is, bout); ret =
   * bout.toByteArray(); } else if (source instanceof InputStream) { is = (InputStream) source;
   * ByteArrayOutputStream bout = new ByteArrayOutputStream (); IOUtils.copyLarge(is, bout); ret =
   * bout.toByteArray(); } return ret; }
   */

  /**
   * Comprueba si unos bytes se corresponden con los de una firma electŕonica (que no sea firma
   * PDF).
   * 
   * @param bytesFirma
   * @return true si lo es, false en caso contrario.
   */
  /*
   * @SuppressWarnings("static-access") private static boolean isOtherSign (byte[] bytesFirma) {
   * logger.debug ("isOtherSign init"); boolean isSigned = false; //byte[] bytesDatosFirmados =
   * null; AOTreeModel arbolFirmantes = null;
   * 
   * es.gob.afirma.core.signers.AOSigner signer = obtenerSigner(bytesFirma); // La firma PDF se
   * trata como un PDF normal, así que no lo consideraremos como documento firmado. if (signer !=
   * null && (!(signer instanceof AOPDFSigner))) { // Consideraremos que se trata de una firma si se
   * puede obtener el árbol de firmantes. //bytesDatosFirmados = signer.getData(bytesFirma);
   * arbolFirmantes = signer.getSignersStructure(bytesFirma, true); }
   * 
   * //isSigned = (bytesDatosFirmados != null && arbolFirmantes != null && arbolFirmantes.getCount()
   * != 0); isSigned = (arbolFirmantes != null &&
   * arbolFirmantes.getChildCount(arbolFirmantes.getRoot()) != 0); logger.debug ("isOtherSign end");
   * return isSigned; }
   */


  /**
   * Se obtiene un objeto para manipular la firma.
   * 
   * @param bytes de la firma
   * @return Instancia de un objeto para manipular la firma.
   */
  /*
   * private static AOSigner obtenerSigner (byte[] bytes) { AOSigner signer = null; signer =
   * AOSignerFactory.getSigner(bytes); return signer; }
   * 
   * public static void main (String[] args) { try { FileInputStream fis = new FileInputStream
   * ("/home/rus/desarrollo/firmas/firmaServidorE.cades"); byte[] b = getBytes(fis); boolean es =
   * checkIsSign(b); } catch (Exception e) { e.printStackTrace(); } }
   */
}
