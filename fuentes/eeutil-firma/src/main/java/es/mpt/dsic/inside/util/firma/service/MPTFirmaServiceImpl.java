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

package es.mpt.dsic.inside.util.firma.service;

import java.io.File;
import java.io.IOException;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.security.auth.callback.PasswordCallback;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Layout;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import es.gob.afirma.core.signers.AOSigner;
import es.gob.afirma.core.signers.AOSignerFactory;
import es.gob.afirma.core.signers.CounterSignTarget;
import es.gob.afirma.keystores.AOKeyStore;
import es.gob.afirma.keystores.AOKeyStoreManager;
import es.gob.afirma.keystores.AOKeyStoreManagerFactory;
import es.mpt.dsic.inside.security.model.AppInfo;
import es.mpt.dsic.inside.util.Constantes;
import es.mpt.dsic.inside.util.firma.TextosErroresFirma;
import es.mpt.dsic.inside.util.firma.ext.SignatureUtil;
import es.mpt.dsic.inside.util.firma.model.DatosFirma;
import es.mpt.dsic.inside.util.firma.model.PeticionFirmaFichero;
import es.mpt.dsic.inside.util.firma.model.RespuestaFirmaFichero;
import es.mpt.dsic.inside.util.firma.util.UtilFirma;
import es.mpt.dsic.inside.utils.file.FileUtil;



@Service("mptFirmaService")
public class MPTFirmaServiceImpl implements MPTFirmaService {
  protected static final Log logger = LogFactory.getLog(MPTFirmaServiceImpl.class);

  private UtilFirma utilFirma;

  private TextosErroresFirma textosErroresFirma;

  private static PasswordCallback pc = new PasswordCallback(">", false);

  @Override
  public RespuestaFirmaFichero firmaFichero(AppInfo aplicacion, PeticionFirmaFichero peticion)
      throws MPTFirmaException {
    try {
      textosErroresFirma = TextosErroresFirma.getInstance();
    } catch (IOException e) {
      logger.error(e);
    }
    if (!peticion.verificaContenido()) {
      logger.error(this.textosErroresFirma.getProperty(Constantes.DATOS_INCORRECTOS_PROP));
      throw new MPTFirmaDatosIncorrectosException(
          this.textosErroresFirma.getProperty(Constantes.DATOS_INCORRECTOS_PROP));
    }
    byte[] datos = peticion.getContenido();

    if (peticion.getAlgoritmo() == null) {
      peticion.setAlgoritmo(
          aplicacion.getPropiedades().get(Constantes.ALGORITMO_FIRMA_DEFECTO_PROP_BBDD));
    }
    if (peticion.getFormato() == null) {
      peticion
          .setFormato(aplicacion.getPropiedades().get(Constantes.FORMATO_FIRMA_DEFECTO_PROP_BBDD));
    }
    if (peticion.getModo() == null) {
      peticion.setModo(aplicacion.getPropiedades().get(Constantes.MODO_FIRMA_DEFECTO_PROP_BBDD));
    }

    AOKeyStoreManager storeManager = getStoreManager(aplicacion);
    PrivateKeyEntry pke = getClavePrivada(storeManager, aplicacion);
    String operacion = getOperacion(peticion);


    byte[] firma = firmaFichero(storeManager, pke, peticion, operacion);

    RespuestaFirmaFichero respuesta = new RespuestaFirmaFichero();
    respuesta.setContenido(datos);

    respuesta.setFormato(peticion.getFormato());
    respuesta.setModo(peticion.getModo());
    respuesta.setAlgoritmo(peticion.getAlgoritmo());
    respuesta.setFirma(firma);
    DatosFirma datfir = getDatosFirmante(storeManager, pke);
    respuesta.setDatosFirma(datfir);
    return respuesta;
  }


  private byte[] firmaFichero(AOKeyStoreManager storeManager, PrivateKeyEntry pke,
      PeticionFirmaFichero pet, String operacion) throws MPTFirmaException {

    logger.debug("Firmando fichero... init");

    try {
      textosErroresFirma = TextosErroresFirma.getInstance();
    } catch (IOException e) {

    }

    byte[] res2 = null;


    Properties propiedadesFirma = new Properties();
    String formato = utilFirma.checkFormato(pet.getFormato(), propiedadesFirma);
    logger.debug("Propiedades de la firma: Formato, Modo, Algoritmo, Operacion: " + formato + ", "
        + pet.getModo() + ", " + pet.getAlgoritmo() + ", " + operacion);

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PROPIEDADES DE FIRMA XADES MANIFEST
    // "format=XAdES Externally Detached"
    // "precalculatedHashAlgorithm=SHA-512"
    // "useManifest=true"
    // "uri=urn:id:nombrefichero"
    //
    // *Para no cambiar el servicio el partametro nodeToSign en firma XADES MANIFEST LO USAMOS PARA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// METER
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// EL
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// NOMBRE
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// DE
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// FICHERO.
    //
    // Si nos envian solicitud de firma manifest acomodamos los parametros para realizar dicha firma
    // la firma xades manifest es una firma detached y hay que cambiar el formato a uno que exista

    // if("XAdES Manifest".equalsIgnoreCase(formato.trim())) traducir a formato reconocido y valido
    if (SignatureUtil.esXadesManifest(pet.getFormato())) {
      formato = SignatureUtil.traducirAFormatoValido(pet.getFormato());

      // Segundo: sumamos las properties necesarias para la firma XADES manifest estas son las
      // extraparams
      propiedadesFirma.setProperty("format", "XAdES Externally Detached");
      propiedadesFirma.setProperty("mode", pet.getModo().toLowerCase());
      propiedadesFirma.setProperty("precalculatedHashAlgorithm", "SHA-512");
      propiedadesFirma.setProperty("useManifest", "true");
      propiedadesFirma.setProperty("uri", "urn:id:" + pet.getParams());
    } else {
      propiedadesFirma.setProperty("format", formato);
      propiedadesFirma.setProperty("mode", pet.getModo().toLowerCase());

      // el parametro del id del nodo a firmar
      if (pet.getParams() != null && !pet.getParams().trim().equals(""))
        propiedadesFirma.setProperty("nodeToSign", pet.getParams());
    }
    //
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    AOSigner signer = AOSignerFactory.getSigner(formato);

    if (signer == null) {
      String msg = textosErroresFirma.getProperty(Constantes.FORMATO_FIRMA_INCORRECTO_PROP);
      msg = msg.replace("#data#", pet.getFormato());
      throw new MPTFirmaOperacionFirmaException(msg);
    }

    try {

      if (Constantes.OPERACION_FIRMA.contentEquals(operacion)) {
        res2 = signer.sign(pet.getContenido(), pet.getAlgoritmo(), pke.getPrivateKey(),
            pke.getCertificateChain(), propiedadesFirma);
      } else if (Constantes.OPERACION_COFIRMA.contentEquals(operacion)) {
        res2 = signer.cosign(pet.getContenido(), pet.getAlgoritmo(), pke.getPrivateKey(),
            pke.getCertificateChain(), propiedadesFirma);
      } else if (Constantes.OPERACION_CONTRAFIRMAR_HOJAS.contentEquals(operacion)) {
        res2 = signer.countersign(pet.getContenido(), pet.getAlgoritmo(), CounterSignTarget.LEAFS,
            null, pke.getPrivateKey(), pke.getCertificateChain(), propiedadesFirma);
      } else if (Constantes.OPERACION_CONTRAFIRMAR_ARBOL.contentEquals(operacion)) {
        res2 = signer.countersign(pet.getContenido(), pet.getAlgoritmo(), CounterSignTarget.TREE,
            null, pke.getPrivateKey(), pke.getCertificateChain(), propiedadesFirma);
      }

      // eliminamos ficheros temporales de @firma "afirmazip...."
      FileUtil.deleteFilesStartWidth(FileUtil.getTmpDir(), "afirmazip");
      FileUtil.deleteFilesStartWidth(FileUtil.getTmpDir(), "sign");

    } catch (Throwable e) {
      logger.error("\n\n");
      logger.error(convertThrowable(e));
      logger.error("\n\n");

      if (e instanceof MPTFirmaException)
        throw (MPTFirmaException) e;
      throw new MPTFirmaOperacionFirmaException(
          textosErroresFirma.getProperty(Constantes.ERROR_PROCESO_FIRMA_PROP));
    }

    if (res2 == null || res2.length < 1) {
      throw new MPTFirmaOperacionFirmaException(
          textosErroresFirma.getProperty(Constantes.ERROR_PROCESO_FIRMA_PROP));
    }
    logger.debug("Firmando fichero... end");
    return res2;
  }

  private String convertThrowable(Throwable throwable) {
    if (throwable == null) {
      return "";
    }
    StringBuffer out = new StringBuffer();
    out.append(throwable + Layout.LINE_SEP);
    StackTraceElement[] stackTraces = throwable.getStackTrace();
    for (int i = 0; i < stackTraces.length; i++) {
      StackTraceElement trace = stackTraces[i];
      out.append("\tat " + trace + Layout.LINE_SEP);
    }
    out.append(convertThrowable(throwable.getCause()));
    return out.toString();
  }

  private AOKeyStoreManager getStoreManager(AppInfo app) throws MPTFirmaException {
    logger.debug("Obteniendo keystoreManager...: init");
    try {
      textosErroresFirma = TextosErroresFirma.getInstance();
    } catch (IOException e) {
      logger.error(e);
    }

    File certfile = null;

    try {
      certfile = ResourceUtils
          .getFile(app.getPropiedades().get(Constantes.RUTA_ALMACEN_CERTIFICADO_PROP_BBDD));
      logger.debug("Encontrado almacén de certificados");

      if (!certfile.exists()) {
        logger.debug("No existe el fichero " + certfile.getAbsolutePath());
        throw new MPTFirmaOpcionesCertificadoException(
            textosErroresFirma.getProperty(Constantes.CERT_NO_ENCONTRADO_PROP));
      }
    } catch (Throwable e) {
      if (e instanceof MPTFirmaException) {
        logger.error(e);
        throw (MPTFirmaException) e;
      }
      logger.error(e);
      throw new MPTFirmaOpcionesCertificadoException(
          textosErroresFirma.getProperty(Constantes.CERT_NO_ENCONTRADO_PROP));
    }

    pc.setPassword(
        app.getPropiedades().get(Constantes.PASS_ALMACEN_CERTIFICADO_PROP_BBDD).toCharArray());
    try {
      AOKeyStoreManager ksm = AOKeyStoreManagerFactory.getAOKeyStoreManager(
          AOKeyStore
              .valueOf(app.getPropiedades().get(Constantes.TIPO_ALMACEN_CERTIFICADO_PROP_BBDD)),
          certfile.getAbsolutePath(), null, pc, null);

      if (ksm == null) {
        logger.error("Cert no abierto");
        throw new MPTFirmaOpcionesCertificadoException(
            textosErroresFirma.getProperty(Constantes.CERT_NO_ABIERTO_PROP));
      }

      logger.debug("Obteniendo keystoreManager...: end");
      return ksm;
    } catch (Throwable e) {
      if (e instanceof MPTFirmaException) {
        logger.error(e);
        throw (MPTFirmaException) e;
      }
      logger.error(e);
      logger.error(e.getCause());
      logger.error(e.getCause().getCause());
      throw new MPTFirmaOpcionesCertificadoException(Constantes.CERT_NO_ABIERTO_PROP);
    }

  }

  private PrivateKeyEntry getClavePrivada(AOKeyStoreManager ksm, AppInfo app)
      throws MPTFirmaException {
    logger.debug("getClavePrivada... init");
    try {
      String alias = app.getPropiedades().get(Constantes.ALIAS_CERTIFICADO_PROP_BBDD);
      PrivateKeyEntry pek = ksm.getKeyEntry(alias);

      if (pek == null) {
        pek = ksm.getKeyEntry(alias.toLowerCase());
        if (pek == null) {
          pek = ksm.getKeyEntry(alias.toUpperCase());
        }
        if (pek == null) {

          if (ksm.getAliases() != null && ksm.getAliases().length == 1) {
            pek = ksm.getKeyEntry(ksm.getAliases()[0]);
          }

          if (pek == null) {
            throw new MPTFirmaOpcionesCertificadoException(
                getTextosProperties().getProperty(Constantes.PK_NO_ABIERTO_PROP));
          }
        }
      }
      logger.debug("getClavePrivada... end");
      return pek;
    } catch (Throwable e) {
      if (e instanceof MPTFirmaException) {
        throw (MPTFirmaException) e;
      }
      throw new MPTFirmaOpcionesCertificadoException(
          getTextosProperties().getProperty(Constantes.PK_NO_ABIERTO_PROP));
    }
  }


  private DatosFirma getDatosFirmante(AOKeyStoreManager ksm, PrivateKeyEntry pp)
      throws MPTFirmaException {
    logger.debug("Obteniendo datos firmante... init");
    try {
      DatosFirma datfir = new DatosFirma();
      X509Certificate t = (X509Certificate) pp.getCertificate();
      Map<String, String> datosPrin = formateaCadenaPrincipal(t.getSubjectDN().toString());
      Map<String, String> datosRaiz = formateaCadenaPrincipal(t.getIssuerDN().toString());
      datfir.setNombreFirmante(datosPrin.get("CN"));
      datfir.setNumeroIdentificacion(datosPrin.get("SERIALNUMBER"));
      datfir.setFechaFirma(new Date());
      datfir.setMailFirmante(datosPrin.get("EMAILADDRESS"));
      datfir.setEntidadCertificadora(datosRaiz.get("CN"));

      logger.debug("Obteniendo datos firmante... end");
      return datfir;
    } catch (Throwable e) {
      if (e instanceof MPTFirmaException) {
        throw (MPTFirmaException) e;
      }
      throw new MPTFirmaOpcionesCertificadoException(
          getTextosProperties().getProperty(Constantes.PK_NO_ABIERTO_PROP));
    }
  }


  private Map<String, String> formateaCadenaPrincipal(String principal) {
    Map<String, String> datos = new HashMap<String, String>();
    String[] dat = StringUtils.tokenizeToStringArray(principal, ",", true, true);
    for (String dato : dat) {
      String[] attr = StringUtils.tokenizeToStringArray(dato, "=", true, true);
      datos.put(attr[0], attr[1]);
    }

    return datos;

  }

  private TextosErroresFirma getTextosProperties() {
    if (textosErroresFirma == null) {
      try {
        textosErroresFirma = TextosErroresFirma.getInstance();
      } catch (IOException e) {

      }
    }
    return textosErroresFirma;
  }

  private String getOperacion(PeticionFirmaFichero peticion) {
    String operacion = Constantes.OPERACION_FIRMA;

    // hay que cambiar manifest por Detached
    // String formato = peticion.getFormato();
    // if("XAdES Manifest".equalsIgnoreCase(formato.trim()))
    // {
    // formato = formato.toLowerCase().replace("manifest", "Detached");
    // }

    String formato = SignatureUtil.traducirAFormatoValido(peticion.getFormato());

    boolean isSigned = SignatureUtil.checkIsSign(peticion.getContenido(), formato);

    if (isSigned) {
      if (peticion.isCofirmarSiFirmado()) {
        operacion = Constantes.OPERACION_COFIRMA;
      } else {
        operacion = Constantes.OPERACION_CONTRAFIRMAR_HOJAS;
      }
    }

    return operacion;
  }

  public UtilFirma getUtilFirma() {
    return utilFirma;
  }

  public void setUtilFirma(UtilFirma utilFirma) {
    this.utilFirma = utilFirma;
  }

}
