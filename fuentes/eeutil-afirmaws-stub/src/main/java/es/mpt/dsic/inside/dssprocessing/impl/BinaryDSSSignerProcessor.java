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


import afirmaws.services.dss.afirma.dss._1_0.profile.xss.schema.DataInfoType;
import afirmaws.services.dss.afirma.dss._1_0.profile.xss.schema.SignedDataInfo;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.AnyType;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.Base64Data;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.Base64Signature;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.DocumentType;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.InputDocuments;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.ResponseBaseType;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.ReturnUpdatedSignature;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.SignatureObject;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.UpdatedSignatureType;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.VerifyRequest;
import es.mpt.dsic.inside.afirma.ws.client.AfirmaConstantes;
import es.mpt.dsic.inside.dssprocessing.DSSSignerProcessor;
import es.mpt.dsic.inside.dssprocessing.DSSUtil;
import es.mpt.dsic.inside.dssprocessing.constantes.DSSTiposFirmaConstantes;
import es.mpt.dsic.inside.obtenerinformacionfirma.ContenidoFirmado;
import es.mpt.dsic.inside.obtenerinformacionfirma.ContentNotExtractedException;
import es.mpt.dsic.inside.utils.mime.MimeUtil;

/**
 * Procesa las peticiones a los servicios DSS de afirma para firmas binarias (PDF, Cades, etc).
 * 
 * @author rus
 *
 */
public class BinaryDSSSignerProcessor extends DSSSignerProcessor {

  @Override
  public void fillSignatureObjectAndInputDocuments(VerifyRequest vf, final byte[] sign,
      final byte[] content) {
    vf.setSignatureObject(buildSignatureObject(sign));
    if (content != null) {
      vf.setInputDocuments(buildInputsDocuments(content));
    }
  }

  @Override
  public void fillReturnUpdatedSignature(AnyType at, String upgradeFormat) {
    ReturnUpdatedSignature returnUpdatedSignature = of_oasis_core.createReturnUpdatedSignature();

    // Se genera el nodo en base a la ampliaci�n solicitada
    if (AfirmaConstantes.UPGRADE_TIMESTAMP.equals(upgradeFormat)) {
      returnUpdatedSignature.setType(DSSTiposFirmaConstantes.DSS_SIGNATURE_MODE_T);
    } else if (AfirmaConstantes.UPGRADE_TIMESTAMP_PDF.equals(upgradeFormat)) {
      returnUpdatedSignature.setType(DSSTiposFirmaConstantes.SIGNATURE_MODE_LTV);
    } else {
      returnUpdatedSignature.setType(upgradeFormat);
    }

    at.getAny().add(returnUpdatedSignature);
  }

  @Override
  public ContenidoFirmado getSignedData(ResponseBaseType verifyResponse, final byte[] sign)
      throws ContentNotExtractedException {
    ContenidoFirmado contenidoFirmado = null;

    // Si el tipo de firma es PADES devolvemos el documento original de la firma.
    if (DSSTiposFirmaConstantes.PADES_TYPES.contains(DSSUtil.getSignatureType(verifyResponse))
        || DSSUtil.getSignatureType(verifyResponse)
            .equalsIgnoreCase(DSSTiposFirmaConstantes.DSS_SIGNATURE_TYPE_PDF)) {
      contenidoFirmado = new ContenidoFirmado();
      contenidoFirmado.setBytesDocumento(sign);
      contenidoFirmado.setMimeDocumento("application/pdf");

      // Si no es PADES, entonces obtenemos la información del contenido a partir del nodo
      // SignedDataInfo.
    } else {

      SignedDataInfo signedDataInfo = (SignedDataInfo) DSSUtil
          .getObjectByClass(SignedDataInfo.class, verifyResponse.getOptionalOutputs().getAny());

      if (signedDataInfo != null && signedDataInfo.getDataInfo() != null
          && signedDataInfo.getDataInfo().size() > 0) {
        contenidoFirmado = new ContenidoFirmado();
        DataInfoType dataInfo = signedDataInfo.getDataInfo().get(0);

        if (dataInfo.getDocumentHash() != null) {
          contenidoFirmado.setHash(dataInfo.getDocumentHash().getDigestValue());
          contenidoFirmado
              .setAlgoritmoHash(dataInfo.getDocumentHash().getDigestMethod().getAlgorithm());
        } else if (dataInfo.getContentData() != null) {
          contenidoFirmado.setBytesDocumento(dataInfo.getContentData().getBinaryValue());
          if (dataInfo.getContentData().getMimeType() != null) {
            contenidoFirmado.setMimeDocumento(dataInfo.getContentData().getMimeType());
          } else {
            contenidoFirmado.setMimeDocumento(
                MimeUtil.getMimeNotNull(dataInfo.getContentData().getBinaryValue()));
          }
        } else {
          throw new ContentNotExtractedException(
              "No se ha encontrado ni el nodo DocumentHash ni el nodo ContentData");
        }
      }
    }


    if (contenidoFirmado == null) {
      throw new ContentNotExtractedException(
          "No existe el nodo SignedDataInfo o alguno de sus hijos necesarios");
    }

    return contenidoFirmado;
  }

  @Override
  public byte[] getUpgradedSignature(ResponseBaseType verifyResponse) {

    // Se obtiene el nodo UpdatedSignatureType //
    UpdatedSignatureType updatedSignatureType = (UpdatedSignatureType) DSSUtil
        .getObjectByClass(UpdatedSignatureType.class, verifyResponse.getOptionalOutputs().getAny());

    SignatureObject signatureObject = updatedSignatureType.getSignatureObject();
    Base64Signature base64Signature = signatureObject.getBase64Signature();

    return base64Signature.getValue();
  }

  protected static SignatureObject buildSignatureObject(byte[] sign) {
    SignatureObject so = of_oasis_core.createSignatureObject();
    so.setBase64Signature(buildBase64Signature(sign));
    return so;
  }

  protected static Base64Signature buildBase64Signature(byte[] sign) {
    Base64Signature b64Signature = of_oasis_core.createBase64Signature();
    b64Signature.setValue(sign);
    return b64Signature;
  }

  protected static InputDocuments buildInputsDocuments(byte[] data) {
    InputDocuments retorno = new InputDocuments();
    DocumentType doc = new DocumentType();
    doc.setID("1");
    Base64Data dataB64 = new Base64Data();
    dataB64.setValue(data);
    doc.setBase64Data(dataB64);
    retorno.getDocumentOrTransformedDataOrDocumentHash().add(doc);
    return retorno;
  }

}
