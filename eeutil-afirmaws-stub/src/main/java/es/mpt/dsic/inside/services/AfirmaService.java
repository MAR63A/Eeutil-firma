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

package es.mpt.dsic.inside.services;

import es.mpt.dsic.inside.exception.AfirmaException;
import es.mpt.dsic.inside.model.ConfiguracionAmpliarFirmaAfirma;
import es.mpt.dsic.inside.model.InformacionFirmaAfirma;
import es.mpt.dsic.inside.model.ResultadoAmpliarFirmaAfirma;
import es.mpt.dsic.inside.model.ResultadoValidacionFirmaFormatoAAfirma;
import es.mpt.dsic.inside.model.ResultadoValidacionInfoAfirma;
import es.mpt.dsic.inside.model.ResultadoValidarCertificadoAfirma;

public interface AfirmaService {

  ResultadoValidacionInfoAfirma validarFirma(String aplicacion, String firmaElectronica,
      String datos, String hash, String algoritmo, String tipoFirma);

  ResultadoValidarCertificadoAfirma validarCertificado(String aplicacion, String certificado,
      Boolean infAmpliada);

  InformacionFirmaAfirma obtenerInformacionFirma(String aplicacion, byte[] firma,
      boolean obtenerFirmantes, boolean obtenerDatosFirmados, boolean obtenerTipoFirma,
      byte[] content) throws AfirmaException;

  ResultadoAmpliarFirmaAfirma ampliarFirma(String aplicacion, byte[] sign,
      ConfiguracionAmpliarFirmaAfirma configuracion) throws AfirmaException;

  String obtenerTipoFirmaDss(String aplicacion, byte[] firma, byte[] content)
      throws AfirmaException;

  ResultadoValidacionFirmaFormatoAAfirma validarFirmaFormatoA(String aplicacion,
      String firmaElectronica, String datos, String hash, String algoritmo, String tipoFirma);

}
