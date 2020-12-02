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

package es.mpt.dsic.inside.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import es.mpt.dsic.inside.security.model.ApplicationLogin;
import es.mpt.dsic.inside.ws.service.model.DatosEntradaFichero;
import es.mpt.dsic.inside.ws.service.model.DatosSalida;

@WebService
public interface EeUtilFirmarService {

  @WebMethod(operationName = "firmaFichero", action = "urn:FirmaFichero")
  @WebResult(name = "datosSalida", partName = "datosSalida")
  public DatosSalida firmaFichero(
      @WebParam(name = "aplicacionLogin") @XmlElement(required = true,
          name = "aplicacionInfo") ApplicationLogin info,
      @WebParam(name = "datosEntrada") @XmlElement(required = true,
          name = "datosEntrada") DatosEntradaFichero entrada);

  @WebMethod(operationName = "firmaFicheroWithPropertie", action = "urn:firmaFicheroWithPropertie")
  @WebResult(name = "datosSalida", partName = "datosSalida")
  public DatosSalida firmaFicheroWithPropertie(
      @WebParam(name = "aplicacionLogin") @XmlElement(required = true,
          name = "aplicacionInfo") ApplicationLogin info,
      @WebParam(name = "datosEntrada") @XmlElement(required = true,
          name = "datosEntrada") DatosEntradaFichero entrada);
}
