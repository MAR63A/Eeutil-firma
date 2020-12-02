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

package es.mpt.dsic.inside.ws.service.impl;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.mpt.dsic.inside.security.context.AplicacionContext;
import es.mpt.dsic.inside.security.model.AppInfo;
import es.mpt.dsic.inside.service.FirmaService;
import es.mpt.dsic.inside.util.firma.model.PeticionFirmaFichero;
import es.mpt.dsic.inside.util.firma.model.RespuestaFirmaFichero;
import es.mpt.dsic.inside.util.firma.service.MPTFirmaException;
import es.mpt.dsic.inside.ws.service.EeUtilFirmarUserNameTokenService;
import es.mpt.dsic.inside.ws.service.model.ContenidoSalidaErronea;
import es.mpt.dsic.inside.ws.service.model.ContenidoSalidaFicheroCorrecta;
import es.mpt.dsic.inside.ws.service.model.DatosEntradaFichero;
import es.mpt.dsic.inside.ws.service.model.DatosSalida;

@Service("eeUtilFirmarUserNameTokenService")
@WebService(endpointInterface = "es.mpt.dsic.inside.ws.service.EeUtilFirmarUserNameTokenService")
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.BARE, use = Use.LITERAL)
public class EeUtilFirmarUserNameTokenServiceImpl implements EeUtilFirmarUserNameTokenService {

  @Autowired
  private es.mpt.dsic.inside.util.firma.service.MPTFirmaService mptFirmaService;

  @Autowired(required = false)
  private AplicacionContext aplicacionContext;

  @Autowired
  FirmaService firmaService;

  @Override
  public DatosSalida firmaFichero(DatosEntradaFichero entrada) {
    DatosSalida salida = null;
    try {
      RespuestaFirmaFichero respuesta =
          firmaService.firmarFichero(aplicacionContext.getAplicacionInfo(), entrada);
      salida = new DatosSalida("OK");
      ContenidoSalidaFicheroCorrecta contenido = new ContenidoSalidaFicheroCorrecta(respuesta);
      salida.setSalida(contenido);
    } catch (Throwable ex) {
      MPTFirmaException mptEr = null;
      if (ex instanceof MPTFirmaException)
        mptEr = (MPTFirmaException) ex;
      else
        mptEr = new MPTFirmaException(ex);
      salida = new DatosSalida("ERROR");
      ContenidoSalidaErronea contenidoError = new ContenidoSalidaErronea();
      contenidoError.setMensaje(mptEr.getTipoError());
      contenidoError.setCausa(mptEr.getMessage());
      salida.setSalida(contenidoError);
    }
    return salida;

  }

  @Override
  public DatosSalida firmaFicheroWithPropertie(DatosEntradaFichero entrada) {
    DatosSalida salida = null;
    try {

      AppInfo aplicacion = aplicacionContext.getAplicacionInfo();
      PeticionFirmaFichero peticion = new PeticionFirmaFichero(entrada);
      RespuestaFirmaFichero respuesta = mptFirmaService.firmaFichero(aplicacion, peticion);
      salida = new DatosSalida("OK");
      ContenidoSalidaFicheroCorrecta contenido = new ContenidoSalidaFicheroCorrecta(respuesta);
      salida.setSalida(contenido);
    } catch (Throwable ex) {
      MPTFirmaException mptEr = null;
      if (ex instanceof MPTFirmaException)
        mptEr = (MPTFirmaException) ex;
      else
        mptEr = new MPTFirmaException(ex);
      salida = new DatosSalida("ERROR");
      ContenidoSalidaErronea contenidoError = new ContenidoSalidaErronea();
      contenidoError.setMensaje(mptEr.getTipoError());
      contenidoError.setCausa(mptEr.getMessage());
      salida.setSalida(contenidoError);
    }
    return salida;

  }

}
