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

package es.mpt.dsic.inside.model;

import java.util.List;


public class InformacionFirmaAfirma {


  private boolean esFirma;

  private TipoDeFirmaAfirma tipoDeFirma;

  private ContenidoInfoAfirma documentoFirmado;

  private String hashFirmado;

  private String algoritmoHashFirmado;

  private List<FirmaInfoAfirma> firmantes;


  public boolean isEsFirma() {
    return esFirma;
  }

  public void setEsFirma(boolean esFirma) {
    this.esFirma = esFirma;
  }

  public TipoDeFirmaAfirma getTipoDeFirma() {
    return tipoDeFirma;
  }

  public void setTipoDeFirma(TipoDeFirmaAfirma tipoDeFirma) {
    this.tipoDeFirma = tipoDeFirma;
  }

  public ContenidoInfoAfirma getDocumentoFirmado() {
    return documentoFirmado;
  }

  public void setDocumentoFirmado(ContenidoInfoAfirma documentoFirmado) {
    this.documentoFirmado = documentoFirmado;
  }

  public String getHashFirmado() {
    return hashFirmado;
  }

  public void setHashFirmado(String hashFirmado) {
    this.hashFirmado = hashFirmado;
  }

  public String getAlgoritmoHashFirmado() {
    return algoritmoHashFirmado;
  }

  public void setAlgoritmoHashFirmado(String algoritmoHashFirmado) {
    this.algoritmoHashFirmado = algoritmoHashFirmado;
  }

  public List<FirmaInfoAfirma> getFirmantes() {
    return firmantes;
  }

  public void setFirmantes(List<FirmaInfoAfirma> firmantes) {
    this.firmantes = firmantes;
  }


}
