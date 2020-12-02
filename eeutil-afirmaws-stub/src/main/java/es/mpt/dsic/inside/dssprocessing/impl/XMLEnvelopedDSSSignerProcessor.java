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

import java.util.List;
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


public class XMLEnvelopedDSSSignerProcessor extends XMLDSSSignerProcessor {

  @Override
  public ContenidoFirmado getSignedData(ResponseBaseType verifyResponse, byte[] sign)
      throws ContentNotExtractedException {
    ContenidoFirmado contenidoFirmado = new ContenidoFirmado();


    try {
      // Obtenemos la estructura DOM del XML de la firma para obtener el valor del atributo id del
      // nodo que se ha firmado.
      // si es null asumimos firmado todo el xml.
      Document doc = XMLUtil.getDOMDocument(sign, false);
      String valorAtributoId = XMLUtil.getContentValorAtributoID(doc);

      // se comenta esto porque aun no saca correctamente el contenido firmado, pero deberia hacerse
      // asi eliminando el nodo signature que lleva dentro
      // String expresionNodoBuscado = "//*/Reference";
      // //cojo el primero que es la primera reference que lleva el uri del nodo firmado
      // List<String> lista = XMLUtil.getContentListAtributoURI(sign, expresionNodoBuscado);
      // String valorAtributoId = lista.get(0).replace('#',' ').trim();

      SignedDataRefType signedDataRef = null;
      String xpathExpr = "/"; // significa el raiz
      if (valorAtributoId != null) {
        SignedDataInfo signedDataInfo = (SignedDataInfo) DSSUtil
            .getObjectByClass(SignedDataInfo.class, verifyResponse.getOptionalOutputs().getAny());

        if (signedDataInfo == null) {
          throw new ContentNotExtractedException("No existe el nodo SignedDataInfo");
        }

        // Nos quedamos con el SignedDataRefType que tenga el elemnto xpath con el valor del
        // atributoId del nodo Firmado
        signedDataRef = DSSUtil.getSignedDataRef(signedDataInfo, valorAtributoId);

        if (signedDataRef == null) {
          throw new ContentNotExtractedException("No se ha encontrado ningun nodo SignedDataRef");
        }

        // Obtenemos la expresion xpath del nodo que tiene el contenido firmado.
        xpathExpr = DSSUtil.expresionXpathValida(signedDataRef.getXPath());
      }


      // si es la raiz o el atributoId nodo firmado es nulo esto es que es todo el xml asi que
      // eliminamos el nodo signature y lo que queda es lo firmado
      if ("/".equals(xpathExpr) || valorAtributoId == null) {
        // Buscamos los nodos SignatureValue y los eliminamos
        String xpathExpression = "//*/Signature";
        XMLUtil.removeNodesFromDocument(xpathExpression, doc);

        // Convertimos el documento a array bytes.Quiere decir que firma todo el xml desde el nodo
        // root y eliminamos el signature y devolvemos el xml entero
        byte[] bytesDocumento = XMLUtil.getBytesFromNode(doc, doc.getInputEncoding()).toByteArray();
        contenidoFirmado.setBytesDocumento(bytesDocumento);
        contenidoFirmado.setMimeDocumento("application/xml");
      } else {

        // En este caso obtenemos el nodoContenido para obtener el contenido del interior
        Node nodoContenido = XMLUtil.getNodeByXpathExpression(doc, xpathExpr);

        // Obtenemos el encoding del contenido
        String encoding =
            getEncodingDatosFirmados(nodoContenido, signedDataRef, doc.getInputEncoding());

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
      encoding = encod;
    }
    return encoding;
  }

}
