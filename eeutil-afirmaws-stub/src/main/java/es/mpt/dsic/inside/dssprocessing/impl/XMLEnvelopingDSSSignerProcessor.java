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

import org.springframework.security.crypto.codec.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.AnyType;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.DocumentType;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.InputDocuments;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.ResponseBaseType;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.ReturnUpdatedSignature;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.SignatureObject;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.SignaturePtr;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.VerifyRequest;
import es.mpt.dsic.inside.afirma.ws.client.AfirmaConstantes;
import es.mpt.dsic.inside.dssprocessing.DSSSignerProcessor;
import es.mpt.dsic.inside.dssprocessing.constantes.DSSTiposFirmaConstantes;
import es.mpt.dsic.inside.obtenerinformacionfirma.ContenidoFirmado;
import es.mpt.dsic.inside.obtenerinformacionfirma.ContentNotExtractedException;
import es.mpt.dsic.inside.utils.xml.XMLUtil;

/**
 * Procesa las peticiones a los servicios DSS de afirma para firmas XMLDSig Enveloping
 * 
 * @author rus
 *
 */
public class XMLEnvelopingDSSSignerProcessor extends DSSSignerProcessor {


  @Override
  public void fillSignatureObjectAndInputDocuments(VerifyRequest vf, byte[] sign, byte[] content) {

    vf.setInputDocuments(buildInputDocuments(sign));
    vf.setSignatureObject(buildSignatureObject());

    vf.getSignatureObject().getSignaturePtr().setWhichDocument(
        vf.getInputDocuments().getDocumentOrTransformedDataOrDocumentHash().get(0));

  }

  protected static InputDocuments buildInputDocuments(byte[] sign) {
    InputDocuments id = of_oasis_core.createInputDocuments();

    DocumentType dt = of_oasis_core.createDocumentType();
    dt.setID("ID_DOCUMENTO");

    dt.setBase64XML(sign);
    id.getDocumentOrTransformedDataOrDocumentHash().add(dt);
    return id;

  }

  protected static SignatureObject buildSignatureObject() {
    SignatureObject so = of_oasis_core.createSignatureObject();
    so.setSignaturePtr(buildSignaturePtr());
    return so;
  }

  protected static SignaturePtr buildSignaturePtr() {
    SignaturePtr sptr = of_oasis_core.createSignaturePtr();
    return sptr;
  }

  @Override
  public ContenidoFirmado getSignedData(ResponseBaseType verifyResponse, final byte[] sign)
      throws ContentNotExtractedException {


    ContenidoFirmado contenidoFirmado = new ContenidoFirmado();

    try {

      // obtener el objeto y el mime del objeto del nodo
      // <ds:Object Encoding="http://www.w3.org/2000/09/xmldsig#base64"
      // Id="Object-d294d524-d53a-4030-9386-d91d7269732d" MimeType="application/pdf">

      Document doc = XMLUtil.getDOMDocument(sign, false);

      // Buscamos los nodos SignatureValue
      String xpathExpression = "//*/Object";


      org.w3c.dom.Node nodoObjet = XMLUtil.getNodeByXpathExpression(doc, xpathExpression);
      String nodoObjectString = XMLUtil.getStringFromNode(nodoObjet, doc.getInputEncoding());

      String xpathExpressionFichero = "//*[local-name()='Object']";
      String fichero = XMLUtil.getvalorNodoDatosXML(nodoObjectString, xpathExpressionFichero);

      // si viene en base64 hay que decodificarlo porque lo van a codificar mas adelante
      byte[] decodificado = null;
      if (Base64.isBase64(fichero.getBytes()))
        decodificado = Base64.decode(fichero.getBytes());
      else
        decodificado = fichero.getBytes();

      contenidoFirmado.setBytesDocumento(decodificado);



      NamedNodeMap atributos = nodoObjet.getAttributes();
      int i = 0;
      String mimetype = null;
      // Este nodo contendra el documento en b64 cuando el atributo encoding lo marque y cuando el
      // mymetipe no sea "hash/algo".
      while (i < atributos.getLength()) {
        Node atributo = atributos.item(i);
        if (atributo.getNodeName().equalsIgnoreCase("mimetype")) {
          mimetype = atributo.getNodeValue();
        }

        i++;
      }

      contenidoFirmado.setMimeDocumento(mimetype);

    } catch (Exception e) {
      throw new ContentNotExtractedException(
          "No se puede obtener el nodo que contiene el contenido firmado" + e.getMessage(), e);
    }

    return contenidoFirmado;



  }


  @Override
  public void fillReturnUpdatedSignature(AnyType at, String upgradeFormat) {

    ReturnUpdatedSignature returnUpdatedSignature = of_oasis_core.createReturnUpdatedSignature();

    // Se genera el nodo en base a la ampliaci�n solicitada
    if (AfirmaConstantes.UPGRADE_TIMESTAMP.equals(upgradeFormat)) {
      returnUpdatedSignature.setType(DSSTiposFirmaConstantes.DSS_SIGNATURE_MODE_T);
    } // Aqu� se ir�n a�adiendo las diferentes posibilidades de ampliaci�n de firmas

    at.getAny().add(returnUpdatedSignature);
  }


  @Override
  public byte[] getUpgradedSignature(ResponseBaseType verifyResponse) {
    // TODO Auto-generated method stub
    return null;
  }


}
