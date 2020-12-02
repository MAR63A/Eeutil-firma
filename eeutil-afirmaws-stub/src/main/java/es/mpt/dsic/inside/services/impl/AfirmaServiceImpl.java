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

package es.mpt.dsic.inside.services.impl;

import es.mpt.dsic.inside.afirma.ws.client.AfirmaWSClient;
import es.mpt.dsic.inside.exception.AfirmaException;
import es.mpt.dsic.inside.model.ConfiguracionAmpliarFirmaAfirma;
import es.mpt.dsic.inside.model.InformacionFirmaAfirma;
import es.mpt.dsic.inside.model.RequestAmpliarFirmaDSS;
import es.mpt.dsic.inside.model.RequestConfigAfirma;
import es.mpt.dsic.inside.model.RequestObtenerInformacionFirma;
import es.mpt.dsic.inside.model.RequestValidarCertificado;
import es.mpt.dsic.inside.model.RequestValidarFirma;
import es.mpt.dsic.inside.model.ResultadoAmpliarFirmaAfirma;
import es.mpt.dsic.inside.model.ResultadoValidacionFirmaFormatoAAfirma;
import es.mpt.dsic.inside.model.ResultadoValidacionInfoAfirma;
import es.mpt.dsic.inside.model.ResultadoValidarCertificadoAfirma;
import es.mpt.dsic.inside.services.AfirmaService;

public class AfirmaServiceImpl implements AfirmaService {

  private AfirmaWSClient afirmaClient;

  private String idAplicacionDefault;
  private String idaplicacionServidorDedicado;
  private String truststore;
  private String passTruststore;

  public AfirmaWSClient getAfirmaClient() {
    return afirmaClient;
  }

  public void setAfirmaClient(AfirmaWSClient afirmaClient) {
    this.afirmaClient = afirmaClient;
  }

  public String getIdAplicacionDefault() {
    return idAplicacionDefault;
  }

  public void setIdAplicacionDefault(String idAplicacionDefault) {
    this.idAplicacionDefault = idAplicacionDefault;
  }

  public String getIdaplicacionServidorDedicado() {
    return idaplicacionServidorDedicado;
  }

  public void setIdaplicacionServidorDedicado(String idaplicacionServidorDedicado) {
    this.idaplicacionServidorDedicado = idaplicacionServidorDedicado;
  }

  public String getTruststore() {
    return truststore;
  }

  public void setTruststore(String truststore) {
    this.truststore = truststore;
  }

  public String getPassTruststore() {
    return passTruststore;
  }

  public void setPassTruststore(String passTruststore) {
    this.passTruststore = passTruststore;
  }

  private RequestConfigAfirma checkSecurity(String aplicacion) {
    RequestConfigAfirma retorno = new RequestConfigAfirma();
    String idAppLower = aplicacion.toLowerCase();
    String idAppServidorDedicadoLower = idaplicacionServidorDedicado.toLowerCase();
    if (idAppLower.contains(idAppServidorDedicadoLower)) {
      retorno.setIdAplicacion(idaplicacionServidorDedicado);
    } else {
      retorno.setIdAplicacion(idAplicacionDefault);
    }
    retorno.setTruststore(truststore);
    retorno.setPassTruststore(passTruststore);
    return retorno;
  }

  @Override
  public ResultadoValidacionInfoAfirma validarFirma(String aplicacion, String firmaElectronica,
      String datos, String hash, String algoritmo, String tipoFirma) {
    RequestValidarFirma requestValidarFirma = new RequestValidarFirma(checkSecurity(aplicacion),
        firmaElectronica, datos, hash, algoritmo, tipoFirma);
    return afirmaClient.validarFirma(requestValidarFirma);
  }

  @Override
  public ResultadoValidarCertificadoAfirma validarCertificado(String aplicacion, String certificado,
      Boolean infAmpliada) {
    RequestValidarCertificado requestValidarCertificado =
        new RequestValidarCertificado(checkSecurity(aplicacion), certificado, infAmpliada, 3, true);
    return afirmaClient.validarCertificado(requestValidarCertificado);
  }

  @Override
  public InformacionFirmaAfirma obtenerInformacionFirma(String aplicacion, byte[] firma,
      boolean obtenerFirmantes, boolean obtenerDatosFirmados, boolean obtenerTipoFirma,
      byte[] content) throws AfirmaException {
    RequestObtenerInformacionFirma requestObtenerInformacionFirma =
        new RequestObtenerInformacionFirma(checkSecurity(aplicacion), firma, obtenerFirmantes,
            obtenerDatosFirmados, obtenerTipoFirma, content);
    // return afirmaClient
    // .obtenerInformacionFirma(requestObtenerInformacionFirma);
    InformacionFirmaAfirma informacionFirma =
        afirmaClient.obtenerInformacionFirma(requestObtenerInformacionFirma);

    if (firma.length < 20000 && (new String(firma)).contains("ds:Manifest>")) {
      informacionFirma.getTipoDeFirma().setTipoFirma("XADES MANIFEST");
    }

    return informacionFirma;
  }

  @Override
  public ResultadoAmpliarFirmaAfirma ampliarFirma(String aplicacion, byte[] sign,
      ConfiguracionAmpliarFirmaAfirma configuracion) throws AfirmaException {
    RequestAmpliarFirmaDSS requestAmpliarFirmaDSS =
        new RequestAmpliarFirmaDSS(checkSecurity(aplicacion), sign, null, configuracion);
    return afirmaClient.ampliarFirma(requestAmpliarFirmaDSS);
  }

  @Override
  public String obtenerTipoFirmaDss(String aplicacion, byte[] firma, byte[] content)
      throws AfirmaException {
    RequestObtenerInformacionFirma requestObtenerInformacionFirma =
        new RequestObtenerInformacionFirma(checkSecurity(aplicacion), firma, false, false, false,
            content);
    return afirmaClient.obtenerTipoFirmaDss(requestObtenerInformacionFirma);
  }

  @Override
  public ResultadoValidacionFirmaFormatoAAfirma validarFirmaFormatoA(String aplicacion,
      String firmaElectronica, String datos, String hash, String algoritmo, String tipoFirma) {
    RequestValidarFirma requestValidarFirma = new RequestValidarFirma(checkSecurity(aplicacion),
        firmaElectronica, datos, hash, algoritmo, tipoFirma);
    return afirmaClient.validarFirmaFormatoA(requestValidarFirma);
  }

}
