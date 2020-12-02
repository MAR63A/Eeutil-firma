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

package es.mpt.dsic.inside.util.firma.util;

import java.util.Properties;
import es.mpt.dsic.inside.util.Constantes;

public class UtilFirma {

  private String policyIdentifierKey;
  private String signatureSubFilter;
  private String policyIdentifierHashKey;
  private String policyIdentifierHashAlgoritmKey;
  private String policyQualifierKey;
  private String policyDescriptionKey;
  private String policySignatureSubFilterKey;

  private String policySignatureSubFilterPades;
  private String policyIdentifierPades;
  private String policyIdentifierHashPades;
  private String policyIdentifierHashAlgorithmPades;
  private String policyQualifierPades;

  private String policyIdentifierXades;
  private String policyDescriptionXades;
  private String policyIdentifierHashXades;
  private String policyIdentifierHashAlgoritmXades;
  private String policyQualifierXades;

  private String policyIdentifierCades;
  private String policyIdentifierHashCades;
  private String policyIdentifierHashAlgoritmCades;
  private String policyQualifierCades;

  public String checkFormato(String formato, Properties propiedadesFirma) {
    String retorno = formato;
    if (formato.equalsIgnoreCase(Constantes.FORMATO_XADES_DETACHED)) {
      propiedadesFirma.setProperty(policyIdentifierKey, policyIdentifierXades);
      propiedadesFirma.setProperty(policyIdentifierHashKey, policyIdentifierHashXades);
      propiedadesFirma.setProperty(policyIdentifierHashAlgoritmKey,
          policyIdentifierHashAlgoritmXades);
      propiedadesFirma.setProperty(policyQualifierKey, policyQualifierXades);
      propiedadesFirma.setProperty(policyDescriptionKey, policyDescriptionXades);
    }
    if (formato.contains(Constantes.FORMATO_CADES)) {
      propiedadesFirma.setProperty(policyIdentifierKey, policyIdentifierCades);
      propiedadesFirma.setProperty(policyIdentifierHashKey, policyIdentifierHashCades);
      propiedadesFirma.setProperty(policyIdentifierHashAlgoritmKey,
          policyIdentifierHashAlgoritmCades);
      propiedadesFirma.setProperty(policyQualifierKey, policyQualifierCades);
    }

    if (formato.contains(Constantes.FORMATO_ADOBE_PDF)) {
      propiedadesFirma.setProperty(policySignatureSubFilterKey, policySignatureSubFilterPades);
      propiedadesFirma.setProperty(policyIdentifierKey, policyIdentifierPades);
      propiedadesFirma.setProperty(policyIdentifierHashKey, policyIdentifierHashPades);
      propiedadesFirma.setProperty(policyIdentifierHashAlgoritmKey,
          policyIdentifierHashAlgorithmPades);
      propiedadesFirma.setProperty(policyQualifierKey, policyQualifierPades);
    }


    retorno = retorno.replaceFirst(" " + Constantes.FORMATO_EPES, "");

    return retorno;
  }

  public String getPolicyIdentifierKey() {
    return policyIdentifierKey;
  }

  public void setPolicyIdentifierKey(String policyIdentifierKey) {
    this.policyIdentifierKey = policyIdentifierKey;
  }

  public String getSignatureSubFilter() {
    return signatureSubFilter;
  }

  public void setSignatureSubFilter(String signatureSubFilter) {
    this.signatureSubFilter = signatureSubFilter;
  }

  public String getPolicyIdentifierHashKey() {
    return policyIdentifierHashKey;
  }

  public void setPolicyIdentifierHashKey(String policyIdentifierHashKey) {
    this.policyIdentifierHashKey = policyIdentifierHashKey;
  }

  public String getPolicyIdentifierHashAlgoritmKey() {
    return policyIdentifierHashAlgoritmKey;
  }

  public void setPolicyIdentifierHashAlgoritmKey(String policyIdentifierHashAlgoritmKey) {
    this.policyIdentifierHashAlgoritmKey = policyIdentifierHashAlgoritmKey;
  }

  public String getPolicyQualifierKey() {
    return policyQualifierKey;
  }

  public void setPolicyQualifierKey(String policyQualifierKey) {
    this.policyQualifierKey = policyQualifierKey;
  }

  public String getPolicyIdentifierXades() {
    return policyIdentifierXades;
  }

  public void setPolicyIdentifierXades(String policyIdentifierXades) {
    this.policyIdentifierXades = policyIdentifierXades;
  }

  public String getPolicyDescriptionXades() {
    return policyDescriptionXades;
  }

  public void setPolicyDescriptionXades(String policyDescriptionXades) {
    this.policyDescriptionXades = policyDescriptionXades;
  }

  public String getPolicyIdentifierHashXades() {
    return policyIdentifierHashXades;
  }

  public void setPolicyIdentifierHashXades(String policyIdentifierHashXades) {
    this.policyIdentifierHashXades = policyIdentifierHashXades;
  }

  public String getPolicyIdentifierHashAlgoritmXades() {
    return policyIdentifierHashAlgoritmXades;
  }

  public void setPolicyIdentifierHashAlgoritmXades(String policyIdentifierHashAlgoritmXades) {
    this.policyIdentifierHashAlgoritmXades = policyIdentifierHashAlgoritmXades;
  }

  public String getPolicyQualifierXades() {
    return policyQualifierXades;
  }

  public void setPolicyQualifierXades(String policyQualifierXades) {
    this.policyQualifierXades = policyQualifierXades;
  }

  public String getPolicyIdentifierCades() {
    return policyIdentifierCades;
  }

  public void setPolicyIdentifierCades(String policyIdentifierCades) {
    this.policyIdentifierCades = policyIdentifierCades;
  }

  public String getPolicyIdentifierHashCades() {
    return policyIdentifierHashCades;
  }

  public void setPolicyIdentifierHashCades(String policyIdentifierHashCades) {
    this.policyIdentifierHashCades = policyIdentifierHashCades;
  }

  public String getPolicyIdentifierHashAlgoritmCades() {
    return policyIdentifierHashAlgoritmCades;
  }

  public void setPolicyIdentifierHashAlgoritmCades(String policyIdentifierHashAlgoritmCades) {
    this.policyIdentifierHashAlgoritmCades = policyIdentifierHashAlgoritmCades;
  }

  public String getPolicyQualifierCades() {
    return policyQualifierCades;
  }

  public void setPolicyQualifierCades(String policyQualifierCades) {
    this.policyQualifierCades = policyQualifierCades;
  }

  public String getPolicyDescriptionKey() {
    return policyDescriptionKey;
  }

  public void setPolicyDescriptionKey(String policyDescriptionKey) {
    this.policyDescriptionKey = policyDescriptionKey;
  }

  public String getPolicySignatureSubFilterKey() {
    return policySignatureSubFilterKey;
  }

  public void setPolicySignatureSubFilterKey(String policySignatureSubFilterKey) {
    this.policySignatureSubFilterKey = policySignatureSubFilterKey;
  }

  public String getPolicySignatureSubFilterPades() {
    return policySignatureSubFilterPades;
  }

  public void setPolicySignatureSubFilterPades(String policySignatureSubFilterPades) {
    this.policySignatureSubFilterPades = policySignatureSubFilterPades;
  }

  public String getPolicyIdentifierPades() {
    return policyIdentifierPades;
  }

  public void setPolicyIdentifierPades(String policyIdentifierPades) {
    this.policyIdentifierPades = policyIdentifierPades;
  }

  public String getPolicyIdentifierHashPades() {
    return policyIdentifierHashPades;
  }

  public void setPolicyIdentifierHashPades(String policyIdentifierHashPades) {
    this.policyIdentifierHashPades = policyIdentifierHashPades;
  }

  public String getPolicyIdentifierHashAlgorithmPades() {
    return policyIdentifierHashAlgorithmPades;
  }

  public void setPolicyIdentifierHashAlgorithmPades(String policyIdentifierHashAlgorithmPades) {
    this.policyIdentifierHashAlgorithmPades = policyIdentifierHashAlgorithmPades;
  }

  public String getPolicyQualifierPades() {
    return policyQualifierPades;
  }

  public void setPolicyQualifierPades(String policyQualifierPades) {
    this.policyQualifierPades = policyQualifierPades;
  }

}
