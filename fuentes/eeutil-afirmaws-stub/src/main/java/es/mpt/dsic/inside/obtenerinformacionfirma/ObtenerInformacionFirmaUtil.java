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

package es.mpt.dsic.inside.obtenerinformacionfirma;

import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactoryConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import es.mpt.dsic.inside.dssprocessing.constantes.DSSTiposFirmaConstantes;
import es.mpt.dsic.inside.utils.xml.XMLUtil;

public class ObtenerInformacionFirmaUtil {

  protected final static Log logger = LogFactory.getLog(ObtenerInformacionFirmaUtil.class);

  public static boolean isXMLDSigSignature(Document doc, javax.xml.xpath.XPath xpath)
      throws XPathFactoryConfigurationException, XPathExpressionException {
    // Existe el nodo Signature, que tiene como hijo SignedInfo. La primera es para XMLDSig Detached
    // y Enveloped, la segunda para Enveloping
    // Existe el nodo Signature, que tiene como hijo SignedValue. La primera es para XMLDSig
    // Detached y Enveloped, la segunda para Enveloping
    // String[] xpathExpressions = new String[]{"Signature/SignedInfo | /Signature/SignedInfo",
    // "Signature/SignatureValue | /Signature/SignatureValue"};

    // Existe el nodo Signature, que tiene como hijo SignedInfo, y SignedValue
    String[] xpathExpressions =
        new String[] {"//Signature/SignedInfo", "//Signature/SignatureValue"};

    return XMLUtil.seCumplen(doc, xpathExpressions, xpath);
  }

  public static boolean isXMLDSigEnvelopedOrDetached(Document doc, javax.xml.xpath.XPath xpath)
      throws XPathFactoryConfigurationException, XPathExpressionException {

    // Nodo SignedInfo y SignatureValue, hijo de Signature, que no tiene por qué ser root del XML.
    String[] xpathExpressions = new String[] {"Signature/SignedInfo", "Signature/SignatureValue"};

    return XMLUtil.seCumplen(doc, xpathExpressions, xpath);
  }

  public static boolean isXMLDSigEnveloping(Document doc, javax.xml.xpath.XPath xpath)
      throws XPathFactoryConfigurationException, XPathExpressionException {

    // Nodo SignedInfo y SignatureValue, hijo de Signature, que tiene que ser root del XML.
    String[] xpathExpressions = new String[] {"/Signature/SignedInfo", "/Signature/SignatureValue"};

    return XMLUtil.seCumplen(doc, xpathExpressions, xpath);

  }

  public static boolean isXMLDSigEnveloped(Document doc, javax.xml.xpath.XPath xpath)
      throws XPathFactoryConfigurationException, XPathExpressionException {
    String[] xpathExpressions = new String[] {"//Signature/SignedInfo",
        "//Signature/SignatureValue",
        "//Transforms/Transform[(@Algorithm|@algorithm|@ALGORITHM)='http://www.w3.org/2000/09/xmldsig#enveloped-signature']"};
    return XMLUtil.seCumplen(doc, xpathExpressions, xpath);
  }

  public static String getTipoFirma(String tipoDSS) {
    String tipo = null;
    if (tipoDSS.equals(DSSTiposFirmaConstantes.DSS_SIGNATURE_TYPE_XMLDSIG)) {
      tipo = "XMLDSIG";
    } else if (DSSTiposFirmaConstantes.XADES_TYPES.contains(tipoDSS)) {
      tipo = "XADES";
    } else if (tipoDSS.equals(DSSTiposFirmaConstantes.DSS_SIGNATURE_TYPE_PKCS_CMS_T)) {
      tipo = "CMS";
    } else if (tipoDSS.equals(DSSTiposFirmaConstantes.DSS_SIGNATURE_TYPE_PKCS_CMS)) {
      tipo = "CMS";
    } else if (tipoDSS.equals(DSSTiposFirmaConstantes.DSS_SIGNATURE_TYPE_PKCS_7_1_5)) {
      tipo = "PKCS";
    } else if (tipoDSS.equals(DSSTiposFirmaConstantes.DSS_SIGNATURE_TYPE_PDF)) {
      tipo = "PDF";
    } else if (DSSTiposFirmaConstantes.PADES_TYPES.contains(tipoDSS)) {
      tipo = "PADES";
    } else if (tipoDSS.equals(DSSTiposFirmaConstantes.DSS_SIGNATURE_TYPE_ODF_T)) {
      tipo = "ODF";
    } else if (tipoDSS.equals(DSSTiposFirmaConstantes.DSS_SIGNATURE_TYPE_ODF)) {
      tipo = "ODF";
    } else if (DSSTiposFirmaConstantes.CADES_TYPES.contains(tipoDSS)) {
      tipo = "CADES";
    } else if (tipoDSS.equals(DSSTiposFirmaConstantes.DSS_SIGNATURE_TYPE_OOXML)) {
      tipo = "OOXML";
    }
    return tipo;
  }

}
