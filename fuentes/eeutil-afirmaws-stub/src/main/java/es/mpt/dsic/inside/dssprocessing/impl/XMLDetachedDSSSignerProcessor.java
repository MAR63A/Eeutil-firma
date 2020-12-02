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

package es.mpt.dsic.inside.dssprocessing.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import afirmaws.services.dss.afirma.dss._1_0.profile.xss.schema.SignedDataInfo;
import afirmaws.services.dss.afirma.dss._1_0.profile.xss.schema.SignedDataRefType;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.ResponseBaseType;
import es.mpt.dsic.inside.dssprocessing.DSSUtil;
import es.mpt.dsic.inside.obtenerinformacionfirma.ContenidoFirmado;
import es.mpt.dsic.inside.obtenerinformacionfirma.ContentNotExtractedException;
import es.mpt.dsic.inside.utils.mime.MimeUtil;
import es.mpt.dsic.inside.utils.misc.MiscUtil;
import es.mpt.dsic.inside.utils.xml.XMLUtil;

public class XMLDetachedDSSSignerProcessor extends XMLDSSSignerProcessor {

  protected static final Log logger = LogFactory.getLog(XMLDetachedDSSSignerProcessor.class);

  @Override
  public ContenidoFirmado getSignedData(ResponseBaseType verifyResponse, byte[] sign)
      throws ContentNotExtractedException {

    SignedDataInfo signedDataInfo = (SignedDataInfo) DSSUtil.getObjectByClass(SignedDataInfo.class,
        verifyResponse.getOptionalOutputs().getAny());

    if (signedDataInfo == null) {
      throw new ContentNotExtractedException("No existe el nodo SignedDataInfo");
    }

    // Nos quedamos con el SignedDataRefType que tenga un elemento "Encoding" dentro
    SignedDataRefType signedDataRefConEncodingOrFirst =
        DSSUtil.getSignedDataRefConEncodingOrFirst(signedDataInfo);

    if (signedDataRefConEncodingOrFirst == null) {
      throw new ContentNotExtractedException("No se ha encontrado ningun nodo SignedDataRef");
    }

    ContenidoFirmado contenidoFirmado = new ContenidoFirmado();

    // Obtenemos la expresión xpath del nodo que tiene el contenido firmado.
    String xpathExpr = DSSUtil.expresionXpathValida(signedDataRefConEncodingOrFirst.getXPath());

    // Obtenemos la estructura DOM del XML de la firma
    try {
      Document doc = XMLUtil.getDOMDocument(sign, false);

      // En este caso obtenemos el nodoContenido para obtener el contenido del interior
      Node nodoContenido = XMLUtil.getNodeByXpathExpression(doc, xpathExpr);

      // Obtenemos el encoding del contenido
      String encoding = getEncodingDatosFirmados(nodoContenido, signedDataRefConEncodingOrFirst,
          doc.getInputEncoding());

      // Obtenemos el contenido de ese nodo.
      // Si es hash
      if (XMLUtil.isHashMimeType(nodoContenido)) {
        // contenidoFirmado.setHash(MiscUtil.decodeByEncoding(nodoContenido.getTextContent(),
        // signedDataRefConEncodingOrFirst.getEncoding()));
        contenidoFirmado
            .setHash(MiscUtil.decodeByEncoding(nodoContenido.getTextContent(), encoding));
        contenidoFirmado.setAlgoritmoHash(XMLUtil.getHashAlgorithm(nodoContenido));
        // Si no es hash
      } else {
        // Si lo que se ha firmado es un XML
        if (XMLUtil.isXMLMimeType(nodoContenido)) {
          // String xml = XMLUtil.getStringFromNode(nodoContenido.getFirstChild(),
          // encodingSignedData);
          // byte[] bytesDocumento = XMLUtil.getBytesFromNode(nodoContenido.getFirstChild(),
          // signedDataRefConEncodingOrFirst.getEncoding()).toByteArray();
          byte[] bytesDocumento =
              XMLUtil.getBytesFromNode(nodoContenido.getFirstChild(), encoding).toByteArray();
          contenidoFirmado.setBytesDocumento(bytesDocumento);
          contenidoFirmado.setMimeDocumento("application/xml");
          // Si no lo es
        } else {
          // byte[] bytesDocumento = MiscUtil.decodeByEncoding(nodoContenido.getTextContent(),
          // signedDataRefConEncodingOrFirst.getEncoding());
          byte[] bytesDocumento =
              MiscUtil.decodeByEncoding(nodoContenido.getTextContent(), encoding);
          contenidoFirmado.setBytesDocumento(bytesDocumento);
          contenidoFirmado.setMimeDocumento(MimeUtil.getMimeNotNull(bytesDocumento));
        }
      }
    } catch (Exception e) {
      throw new ContentNotExtractedException(
          "No se puede obtener el nodo que contiene el contenido firmado" + e.getMessage(), e);
    }

    return contenidoFirmado;
  }

  /**
   * Obtenemos el encoding del contenido firmado. En primer lugar obtenemso el atributo del nodo
   * signedDataRef de la respuesta de afirma, si no tiene miraremos si el nodo contenido tien este
   * atributo.
   * 
   * @param nodoContenido
   * @param signedDataRef
   * @return
   */
  private String getEncodingDatosFirmados(Node nodoContenido, SignedDataRefType signedDataRef,
      String encod) {
    String encoding = null;
    if (signedDataRef.getEncoding() != null) {
      encoding = signedDataRef.getEncoding();
    } else {
      encoding = XMLUtil.getEncoding(nodoContenido);
    }
    if (encoding == null) {
      logger.info(
          "No se ha podido obtener el Encoding ni del nodo contenido ni del nodo signedDataRef");
      encoding = encod;
    }
    return encoding;
  }
}
