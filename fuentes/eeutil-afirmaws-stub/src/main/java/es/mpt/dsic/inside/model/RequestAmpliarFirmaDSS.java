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

import es.mpt.dsic.inside.dssprocessing.DSSSignerProcessor;

public class RequestAmpliarFirmaDSS extends RequestConfigAfirma {

  private byte[] sign;
  private DSSSignerProcessor processor;
  private ConfiguracionAmpliarFirmaAfirma configuracion;

  public RequestAmpliarFirmaDSS(RequestConfigAfirma config, byte[] sign,
      DSSSignerProcessor processor, ConfiguracionAmpliarFirmaAfirma configuracion) {
    super(config.getIdAplicacion(), config.getTruststore(), config.getPassTruststore());
    this.sign = sign;
    this.processor = processor;
    this.configuracion = configuracion;
  }

  public byte[] getSign() {
    return sign;
  }

  public void setSign(byte[] sign) {
    this.sign = sign;
  }

  public DSSSignerProcessor getProcessor() {
    return processor;
  }

  public void setProcessor(DSSSignerProcessor processor) {
    this.processor = processor;
  }

  public ConfiguracionAmpliarFirmaAfirma getConfiguracion() {
    return configuracion;
  }

  public void setConfiguracion(ConfiguracionAmpliarFirmaAfirma configuracion) {
    this.configuracion = configuracion;
  }

}
