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

import java.io.IOException;
import java.io.InputStream;
import javax.jws.WebService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.mpt.dsic.inside.security.context.AplicacionContext;
import es.mpt.dsic.inside.security.model.ApplicationLogin;
import es.mpt.dsic.inside.service.FirmaService;
import es.mpt.dsic.inside.util.SecurityUtils;
import es.mpt.dsic.inside.util.firma.model.RespuestaFirmaFichero;
import es.mpt.dsic.inside.util.firma.service.MPTFirmaException;
import es.mpt.dsic.inside.utils.misc.MiscUtil;
import es.mpt.dsic.inside.ws.service.EeUtilFirmarServiceMtom;
import es.mpt.dsic.inside.ws.service.model.ContenidoSalidaErronea;
import es.mpt.dsic.inside.ws.service.model.ContenidoSalidaFicheroCorrectaMtom;
import es.mpt.dsic.inside.ws.service.model.DatosEntradaFichero;
import es.mpt.dsic.inside.ws.service.model.DatosEntradaFicheroMtom;
import es.mpt.dsic.inside.ws.service.model.DatosSalida;

@Service("eeUtilFirmarServiceMtom")
@WebService(endpointInterface = "es.mpt.dsic.inside.ws.service.EeUtilFirmarServiceMtom")
public class EeUtilFirmarServiceMtomImpl implements EeUtilFirmarServiceMtom {

  @Autowired(required = false)
  private AplicacionContext aplicacionContext;

  @Autowired
  FirmaService firmaService;

  @Override
  public DatosSalida firmaFichero(ApplicationLogin info, DatosEntradaFicheroMtom entrada) {
    DatosSalida salida = null;
    try {
      if (entrada.getAlgoritmoHuella() != null
          && StringUtils.isNotEmpty(entrada.getAlgoritmoHuella().getValue().value())
          && StringUtils.isNotEmpty(entrada.getHuellaDigital())) {
        // validacion de hash
        SecurityUtils.validateAlgorithm(entrada.getAlgoritmoHuella().getValue().value(),
            entrada.getContenido().getInputStream(), entrada.getHuellaDigital());
      }

      RespuestaFirmaFichero respuesta =
          firmaService.firmarFichero(aplicacionContext.getAplicacionInfo(),
              convertDatosEntradaFicheroMtomToDatosEntradaFichero(entrada));
      salida = new DatosSalida("OK");

      ContenidoSalidaFicheroCorrectaMtom contenido =
          new ContenidoSalidaFicheroCorrectaMtom(respuesta);

      contenido.setAlgoritmoHashContenido(MiscUtil.ALGORITMO_HASH_MD5);
      contenido.setHashContenido(MiscUtil.generateHash(MiscUtil.ALGORITMO_HASH_MD5,
          contenido.getContenido().getInputStream()));
      contenido.setAlgoritmoHashFirma(MiscUtil.ALGORITMO_HASH_MD5);
      contenido.setHashFirma(MiscUtil.generateHash(MiscUtil.ALGORITMO_HASH_MD5,
          contenido.getFirma().getInputStream()));
      salida.setSalida(contenido);
    } catch (SecurityException ex) {
      salida = new DatosSalida("ERROR");
      ContenidoSalidaErronea contenidoError = new ContenidoSalidaErronea();
      contenidoError.setMensaje("Error en Hash");
      contenidoError.setCausa(ex.getMessage());
      salida.setSalida(contenidoError);
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

  private DatosEntradaFichero convertDatosEntradaFicheroMtomToDatosEntradaFichero(
      DatosEntradaFicheroMtom entrada) throws IOException {
    InputStream stream = null;
    try {
      DatosEntradaFichero datos = new DatosEntradaFichero();
      datos.setAlgoritmoFirma(entrada.getAlgoritmoFirma());
      stream = entrada.getContenido().getInputStream();
      datos.setContenido(IOUtils.toByteArray(stream));
      datos.setModoFirma(entrada.getModoFirma());
      datos.setFormatoFirma(entrada.getFormatoFirma());
      datos.setCofirmarSiFirmado(entrada.isCofirmarSiFirmado());
      return datos;
    } catch (Exception e) {
      throw e;
    } finally {
      if (stream != null) {
        stream.close();
      }
    }
  }
}
