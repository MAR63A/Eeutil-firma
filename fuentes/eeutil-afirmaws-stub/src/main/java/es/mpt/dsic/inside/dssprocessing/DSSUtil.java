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

package es.mpt.dsic.inside.dssprocessing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.security.cert.X509Certificate;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import es.mpt.dsic.inside.model.FirmaInfoAfirma;
import es.mpt.dsic.inside.obtenerinformacionfirma.ReadableCertificateInfoAndTstContentType;
import es.mpt.dsic.inside.util.ComparadorFechas;
import es.mpt.dsic.inside.utils.misc.MiscUtil;
import es.mpt.dsic.inside.util.InformacionFirmante;
import afirmaws.services.dss.afirma.dss._1_0.profile.xss.schema.DataInfoType;
import afirmaws.services.dss.afirma.dss._1_0.profile.xss.schema.ReadableCertificateInfo;
import afirmaws.services.dss.afirma.dss._1_0.profile.xss.schema.ReadableFieldType;
import afirmaws.services.dss.afirma.dss._1_0.profile.xss.schema.SignedDataInfo;
import afirmaws.services.dss.afirma.dss._1_0.profile.xss.schema.SignedDataRefType;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.ResponseBaseType;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.core.schema.Result;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.profiles.verificationreport.schema.DetailedReportType;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.profiles.verificationreport.schema.IndividualSignatureReportType;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.profiles.verificationreport.schema.TimeStampValidityType;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.profiles.verificationreport.schema.TstContentType;
import afirmaws.services.dss.oasis.names.tc.dss._1_0.profiles.verificationreport.schema.VerificationReportType;


public class DSSUtil {

  protected static final Log logger = LogFactory.getLog(DSSUtil.class);

  /**
   * Busca en el VerificationReport de la respuesta el ReadableCertificateInfo que coincida en
   * número de serie con el X509Certificate pasado como parámetro
   * 
   * @param certificate certificado X509Certificate del que se quiere encontrar su equivalente en el
   *        VerificationReport
   * @param verificationReport
   * @return ReadableCertificateInfo que tenga el mismo número de serie que el X509, null si no se
   *         encuentra ninguno.
   */
  /*
   * public static ReadableCertificateInfo getReadableCertificateInfoCoincidente (X509Certificate
   * certificate, List<ReadableCertificateInfo> readableCertificateInfoList) {
   * //List<IndividualSignatureReportType> listaReports =
   * verificationReport.getIndividualSignatureReport();
   * 
   * int i=0; boolean found = false; ReadableCertificateInfo coincidente = null;
   * 
   * while (i < readableCertificateInfoList.size() && !found) { //IndividualSignatureReportType
   * report = listaReports.get(i); ReadableCertificateInfo certInfo =
   * readableCertificateInfoList.get(i);
   * 
   * String serialNumber = getFieldValue ("numeroSerie", certInfo); String idEmisor =
   * getFieldValue("idEmisor", certInfo);
   * 
   * if (certificate.getSerialNumber().toString().contentEquals(serialNumber)) {
   * logger.debug("Encontrado numero de serie coincidente"); found = true;
   * 
   * if (idEmisor != null) { if
   * (idEmisor.contentEquals(certificate.getIssuerX500Principal().getName()) ||
   * idEmisor.contentEquals(MiscUtil.getOrganizationIssuerX500Principal(certificate.
   * getIssuerX500Principal().getName()))) { logger.debug("Encontrado idEmisor coincidente"); } }
   * coincidente = certInfo;
   * 
   * }
   * 
   * i++; }
   * 
   * return coincidente;
   * 
   * }
   */

  /**
   * Devuelve la pareja ReadableCertificateInfo/TstContentType cuyo objeto readableCertificateInfo
   * tenga el mismo número de serie que el X509Certificate pasado como parámetro.
   * 
   * @param certificate
   * @param readableCertificateInfoAndTstContentList
   * @return pareja ReadableCertificateInfo/TstContentType que tenga el mismo número de serie que el
   *         X509, null si no se encuentra ninguno.
   */
  public static ReadableCertificateInfoAndTstContentType getReadableCertificateInfoAndTstContentTypeCoincidente(
      X509Certificate certificate,
      List<ReadableCertificateInfoAndTstContentType> readableCertificateInfoAndTstContentList) {
    int i = 0;
    boolean found = false;
    ReadableCertificateInfoAndTstContentType coincidente = null;

    while (i < readableCertificateInfoAndTstContentList.size() && !found) {
      // IndividualSignatureReportType report = listaReports.get(i);
      ReadableCertificateInfoAndTstContentType certInfoAndTstContent =
          readableCertificateInfoAndTstContentList.get(i);

      String serialNumber =
          getFieldValue("numeroSerie", certInfoAndTstContent.getReadableCertificateInfo());
      String idEmisor =
          getFieldValue("idEmisor", certInfoAndTstContent.getReadableCertificateInfo());

      if (certificate.getSerialNumber().toString().contentEquals(serialNumber)) {
        logger.debug("Encontrado numero de serie coincidente");
        found = true;

        if (idEmisor != null) {
          if (idEmisor.contentEquals(certificate.getIssuerX500Principal().getName())
              || idEmisor.contentEquals(MiscUtil.getOrganizationIssuerX500Principal(
                  certificate.getIssuerX500Principal().getName()))) {
            logger.debug("Encontrado idEmisor coincidente");
          }
        }
        coincidente = certInfoAndTstContent;

      }

      i++;
    }

    return coincidente;
  }


  /**
   * Devuelve el primer objeto de una lista que sea de una clase dada como parámetro
   * 
   * @param clase Clase del objeto que se quiere buscar
   * @param lista Lista de objetos sobre la que se quiere buscar
   * @return
   */
  /*
   * public static Object getObjectByClass (Class<?> clase, List<Object> lista) { int i=0; boolean
   * found = false; Object ret = null; while (i < lista.size() && !found) { if
   * (lista.get(i).getClass().equals(clase)) { ret = lista.get(i); found = true; } i++; } return
   * ret; }
   */

  /**
   * Devuelve la lista de objetos de una clase dada que se encuentre en otra lista de objetos dada
   * como parámetro
   * 
   * @param clase Clase de los objetos que se desean buscar
   * @param lista Lista de objetos sobre la que se quiere buscar.
   * @return
   */
  public static List<Object> getListObjectsByClass(Class<?> clase, List<Object> lista) {
    List<Object> founds = new ArrayList<Object>();
    for (Object obj : lista) {
      if (obj.getClass().equals(clase)) {
        founds.add(obj);
      }
    }
    return founds;
  }

  /**
   * Devuelve un objeto de tipo JAXBElement<?> cuyo nombre se corresponda con el QName dado como
   * parámetro
   * 
   * @param qname QName del objeto que se quiere buscar
   * @param lista Lista de objetos sobre la que se quiere buscar
   * @return
   */
  public static JAXBElement<?> getJAXBElementByQName(QName qname, List<Object> lista) {
    int i = 0;
    boolean found = false;
    JAXBElement<?> ret = null;
    while (i < lista.size() && !found) {
      if (lista.get(i) instanceof JAXBElement<?>) {
        ret = (JAXBElement<?>) lista.get(i);
        if (qname.equals(ret.getName())) {
          found = true;
        }
      }
      i++;
    }

    return ret;

  }

  @SuppressWarnings("unchecked")
  public static <J extends JAXBElement<Q>, Q> Q getObjectByClass(Class<Q> clase,
      List<Object> objetos) {

    Q ret = null;
    boolean found = false;
    int i = 0;

    while (i < objetos.size() && !found) {
      if (objetos.get(i) instanceof JAXBElement<?>) {
        JAXBElement<?> jbel = (JAXBElement<?>) objetos.get(i);
        if (jbel.getValue().getClass() == clase) {
          found = true;
          ret = (Q) jbel.getValue();
        }
      } else if (objetos.get(i).getClass() == clase) {
        found = true;
        ret = (Q) objetos.get(i);
      }
      i++;
    }

    return ret;
  }



  /**
   * Busca en un ReadableCertificateInfo el valor de un campo.
   * 
   * @param fieldIdentity Nombre del campo del que se quiere saber el valor.
   * @param certInfo
   * @return Valor del campo que se desea buscar, null si no se encuentra.
   */
  public static String getFieldValue(String fieldIdentity, ReadableCertificateInfo certInfo) {
    List<ReadableFieldType> fields = certInfo.getReadableField();
    int i = 0;
    boolean found = false;
    String value = null;
    while (i < fields.size() && !found) {
      if (fields.get(i).getFieldIdentity().equalsIgnoreCase(fieldIdentity)) {
        value = fields.get(i).getFieldValue();
        found = true;
      }
      i++;
    }
    return value;
  }

  /**
   * Mezcla la información de dos listas de firmantes.
   * 
   * @param infoFirmantes Información de los firmantes obtenida con las librerías de afirma.
   * @param readableCertificateInfoList Información de los certificados firmantes obtenida de la
   *        llamada a los servicios DSS de Afirma.
   * @return Lista de firmantes con la información de ambas fuentes.
   */
  /*
   * public static List<FirmaInfo> mergeInfoFirmantes (List<InformacionFirmante> infoFirmantes,
   * List<ReadableCertificateInfo> readableCertificateInfoList) {
   * 
   * List<FirmaInfo> firmantes = new ArrayList<FirmaInfo> ();
   * 
   * for (InformacionFirmante info1 : infoFirmantes) {
   * 
   * FirmaInfo firmaInfo = new FirmaInfo ();
   * 
   * firmaInfo.setFecha(info1.getFecha()); firmaInfo.setExtras(info1.getTipo().toString());
   * 
   * fillFirmaInfo(firmaInfo, getReadableCertificateInfoCoincidente (info1.getCertificado(),
   * readableCertificateInfoList));
   * 
   * firmantes.add(firmaInfo); }
   * 
   * Collections.sort(firmantes, new ComparadorFechas ());
   * 
   * // El tipo de la firma del que firmó primero será Firma. if (firmantes != null) {
   * firmantes.get(0).setExtras((InformacionFirmante.TipoFirma.F).toString()); }
   * 
   * return firmantes;
   * 
   * }
   */

  /**
   * Mezcla la información de dos listas de firmantes. *
   * 
   * @param infoFirmantes Información de los firmantes obtenida con las librerías de afirma.
   * @param readableCertificateInfoAndTstContentList Información de los certificados firmantes
   *        obtenida de la llamada a los servicios DSS de Afirma, junto con los sellos de tiempo.
   * @return Lista de firmantes con la información de ambas fuentes.
   */
  /*
   * public static List<FirmaInfo> mergeInfoFirmantes (List<InformacionFirmante> infoFirmantes,
   * List<ReadableCertificateInfoAndTstContentType> readableCertificateInfoAndTstContentList) {
   * 
   * List<FirmaInfo> firmantes = new ArrayList<FirmaInfo> ();
   * 
   * for (InformacionFirmante info1 : infoFirmantes) {
   * 
   * FirmaInfo firmaInfo = new FirmaInfo ();
   * 
   * firmaInfo.setFecha(info1.getFecha()); firmaInfo.setExtras(info1.getTipo().toString());
   * 
   * 
   * //fillFirmaInfo(firmaInfo, getReadableCertificateInfoCoincidente (info1.getCertificado(),
   * readableCertificateInfoAndTstContentList)); ReadableCertificateInfoAndTstContentType
   * certTstInfo = getReadableCertificateInfoAndTstContentTypeCoincidente (info1.getCertificado(),
   * readableCertificateInfoAndTstContentList); fillFirmaInfo(firmaInfo,
   * certTstInfo.getReadableCertificateInfo(), certTstInfo.getTimeStampContent());
   * 
   * firmantes.add(firmaInfo); }
   * 
   * Collections.sort(firmantes, new ComparadorFechas ());
   * 
   * // El tipo de la firma del que firmó primero será Firma. if (firmantes != null) {
   * //firmantes.get(0).setExtras((InformacionFirmante.TipoFirma.F).toString());
   * firmantes.get(0).setExtras(firmantes.get(0).getExtras().replaceFirst((InformacionFirmante.
   * TipoFirma.CF).toString(), (InformacionFirmante.TipoFirma.F).toString()));
   * 
   * }
   * 
   * return firmantes;
   * 
   * }
   */


  /**
   * Mezcla la información de dos listas de firmantes. *
   * 
   * @param infoFirmantes Información de los firmantes obtenida con las librerías de afirma.
   * @param readableCertificateInfoAndTstContentList Información de los certificados firmantes
   *        obtenida de la llamada a los servicios DSS de Afirma, junto con los sellos de tiempo.
   * @return Lista de firmantes con la información de ambas fuentes.
   */
  public static List<FirmaInfoAfirma> mergeInfoFirmantes(List<InformacionFirmante> infoFirmantes,
      List<ReadableCertificateInfoAndTstContentType> readableCertificateInfoAndTstContentList) {

    List<FirmaInfoAfirma> firmantes = new ArrayList<FirmaInfoAfirma>();

    for (InformacionFirmante info1 : infoFirmantes) {

      FirmaInfoAfirma firmaInfo = new FirmaInfoAfirma();

      firmaInfo.setFecha(info1.getFecha());
      firmaInfo.setExtras(info1.getTipo().toString());


      // fillFirmaInfo(firmaInfo, getReadableCertificateInfoCoincidente (info1.getCertificado(),
      // readableCertificateInfoAndTstContentList));
      ReadableCertificateInfoAndTstContentType certTstInfo =
          getReadableCertificateInfoAndTstContentTypeCoincidente(info1.getCertificado(),
              readableCertificateInfoAndTstContentList);
      fillFirmaInfoAfirma(firmaInfo, certTstInfo.getReadableCertificateInfo(),
          certTstInfo.getTimeStampContent());

      firmantes.add(firmaInfo);
    }

    Collections.sort(firmantes, new ComparadorFechas());

    // El tipo de la firma del que firmó primero será Firma.
    if (firmantes != null) {
      // firmantes.get(0).setExtras((InformacionFirmante.TipoFirma.F).toString());
      firmantes.get(0)
          .setExtras(firmantes.get(0).getExtras().replaceFirst(
              (InformacionFirmante.TipoFirma.CF).toString(),
              (InformacionFirmante.TipoFirma.F).toString()));

    }

    return firmantes;

  }


  /**
   * Rellena algunos campos de un objeto FirmaInfo
   * 
   * @param firmaInfo objeto del que se quieren rellenar algunos campos.
   * @param certInfo Objeto devuelto en la petición al servico DSS de afirma.
   */
  /*
   * public static void fillFirmaInfo (FirmaInfo firmaInfo, final ReadableCertificateInfo certInfo)
   * { String nifResponsable = null; String NIFCIF = null; String nombreResponsable = null; String
   * razonSocial = null;
   * 
   * // apellido1 y apellido2 son required, así que les ponemos valor cadena vacía, por si no
   * estuvieran en la información del certificado firmante. firmaInfo.setApellido1("");
   * firmaInfo.setApellido2("");
   * 
   * List<ReadableFieldType> fields = certInfo.getReadableField();
   * 
   * for (ReadableFieldType field : fields) {
   * 
   * logger.debug("IdCampo: " + field.getFieldIdentity()); logger.debug("ValorCampos: " +
   * field.getFieldValue());
   * 
   * if (field.getFieldIdentity().equals("nombreResponsable")) {
   * 
   * logger.debug("nombreResponsable: " + field.getFieldValue()); nombreResponsable =
   * field.getFieldValue(); //e.setNombre(c.getValorCampo()); }
   * 
   * else if (field.getFieldIdentity().equals("razonSocial")) {
   * 
   * logger.debug("razonSocial: " + field.getFieldValue()); razonSocial = field.getFieldValue();
   * //e.setNombre(c.getValorCampo()); }
   * 
   * else if (field.getFieldIdentity().equals("primerApellidoResponsable")) {
   * logger.debug("Apellido1: " + field.getFieldValue());
   * firmaInfo.setApellido1(field.getFieldValue()); }
   * 
   * else if (field.getFieldIdentity().equals("segundoApellidoResponsable")) {
   * logger.debug("Apellido2: " + field.getFieldValue());
   * firmaInfo.setApellido2(field.getFieldValue()); }
   * 
   * else if (field.getFieldIdentity().equals("NIFResponsable")) { logger.debug("NIFResponsable: " +
   * field.getFieldValue()); nifResponsable = field.getFieldValue();
   * //e.setNifcif(c.getValorCampo()); }
   * 
   * else if (field.getFieldIdentity().equals("NIF-CIF")) { logger.debug("NIF-CIF: " +
   * field.getFieldValue()); NIFCIF = field.getFieldValue(); //e.setNifcif(c.getValorCampo()); } }
   * 
   * if (nifResponsable != null) { firmaInfo.setNifcif(nifResponsable); } else {
   * firmaInfo.setNifcif(NIFCIF); }
   * 
   * if (nombreResponsable != null) { firmaInfo.setNombre(nombreResponsable); } else {
   * firmaInfo.setNombre(razonSocial); }
   * 
   * }
   */

  /**
   * Rellena algunos campos de un objeto FirmaInfo
   * 
   * @param firmaInfo objeto del que se quieren rellenar algunos campos.
   * @param certInfo Objeto devuelto en la petición al servico DSS de afirma.
   */
  /*
   * public static void fillFirmaInfo (FirmaInfo firmaInfo, final ReadableCertificateInfo certInfo,
   * final TstContentType tstContent) { String nifResponsable = null; String NIFCIF = null; String
   * NIFEntidadSuscriptora = null; String entidadSuscriptora = null; String nombreResponsable =
   * null; String razonSocial = null;
   * 
   * // apellido1 y apellido2 son required, así que les ponemos valor cadena vacía, por si no
   * estuvieran en la información del certificado firmante. firmaInfo.setApellido1("");
   * firmaInfo.setApellido2("");
   * 
   * List<ReadableFieldType> fields = certInfo.getReadableField();
   * 
   * for (ReadableFieldType field : fields) {
   * 
   * logger.debug("IdCampo: " + field.getFieldIdentity()); logger.debug("ValorCampos: " +
   * field.getFieldValue());
   * 
   * if (field.getFieldIdentity().equals("nombreResponsable")) {
   * 
   * logger.debug("nombreResponsable: " + field.getFieldValue()); nombreResponsable =
   * field.getFieldValue(); //e.setNombre(c.getValorCampo()); }
   * 
   * else if (field.getFieldIdentity().equals("razonSocial")) {
   * 
   * logger.debug("razonSocial: " + field.getFieldValue()); razonSocial = field.getFieldValue();
   * //e.setNombre(c.getValorCampo()); }
   * 
   * else if (field.getFieldIdentity().equals("primerApellidoResponsable")) {
   * logger.debug("Apellido1: " + field.getFieldValue());
   * firmaInfo.setApellido1(field.getFieldValue()); }
   * 
   * else if (field.getFieldIdentity().equals("segundoApellidoResponsable")) {
   * logger.debug("Apellido2: " + field.getFieldValue());
   * firmaInfo.setApellido2(field.getFieldValue()); }
   * 
   * else if (field.getFieldIdentity().equals("NIFResponsable")) { logger.debug("NIFResponsable: " +
   * field.getFieldValue()); nifResponsable = field.getFieldValue();
   * //e.setNifcif(c.getValorCampo()); }
   * 
   * else if (field.getFieldIdentity().equals("NIF-CIF")) { logger.debug("NIF-CIF: " +
   * field.getFieldValue()); NIFCIF = field.getFieldValue(); //e.setNifcif(c.getValorCampo()); }
   * else if (field.getFieldIdentity().equals("entidadSuscriptora")) { logger.debug
   * ("entidadSuscriptora: " + field.getFieldValue()); entidadSuscriptora = field.getFieldValue(); }
   * 
   * else if (field.getFieldIdentity().equals("NIFEntidadSuscriptora")) { logger.debug
   * ("NIFEntidadSuscriptora: " + field.getFieldValue()); NIFEntidadSuscriptora =
   * field.getFieldValue(); } }
   * 
   * if (nifResponsable != null && !nifResponsable.contentEquals("")) {
   * firmaInfo.setNifcif(nifResponsable); } else if (NIFCIF != null && !NIFCIF.contentEquals("")){
   * firmaInfo.setNifcif(NIFCIF); } else if (NIFEntidadSuscriptora != null &&
   * !NIFEntidadSuscriptora.contentEquals("")) { firmaInfo.setNifcif(NIFEntidadSuscriptora); } else
   * { firmaInfo.setNifcif(""); }
   * 
   * if (nombreResponsable != null && !nombreResponsable.contentEquals("")) {
   * firmaInfo.setNombre(nombreResponsable); } else if (razonSocial != null &&
   * !razonSocial.contentEquals("")){ firmaInfo.setNombre(razonSocial); } else if
   * (entidadSuscriptora != null && !entidadSuscriptora.contentEquals("")){
   * firmaInfo.setNombre(entidadSuscriptora); } else { firmaInfo.setNombre(""); }
   * 
   * // Información del timestamp if (tstContent != null) { if (tstContent.getCreationTime() !=
   * null) { XMLGregorianCalendar greg = tstContent.getCreationTime();
   * firmaInfo.setExtras(firmaInfo.getExtras() + " - (Sello de Tiempo: " +
   * MiscUtil.dateToString(greg.toGregorianCalendar().getTime(), "dd/MM/yyyy HH:mm") + ")"); }
   * 
   * }
   * 
   * }
   */

  /**
   * Rellena algunos campos de un objeto FirmaInfo
   * 
   * @param firmaInfo objeto del que se quieren rellenar algunos campos.
   * @param certInfo Objeto devuelto en la petición al servico DSS de afirma.
   */
  public static void fillFirmaInfoAfirma(FirmaInfoAfirma firmaInfo,
      final ReadableCertificateInfo certInfo, final TstContentType tstContent) {
    String nifResponsable = null;
    String NIFCIF = null;
    String NIFEntidadSuscriptora = null;
    String entidadSuscriptora = null;
    String nombreResponsable = null;
    String razonSocial = null;

    // Se suma este campo por la posibilidad de firma con certificado seudonimo
    String seudonimo = null;

    String apellidos = null;

    // apellido1 y apellido2 son required, así que les ponemos valor cadena vacía, por si no
    // estuvieran en la información del certificado firmante.
    firmaInfo.setApellido1("");
    firmaInfo.setApellido2("");

    List<ReadableFieldType> fields = certInfo.getReadableField();

    for (ReadableFieldType field : fields) {

      logger.debug("IdCampo: " + field.getFieldIdentity());
      logger.debug("ValorCampos: " + field.getFieldValue());

      if (field.getFieldIdentity().equals("nombreResponsable")) {

        logger.debug("nombreResponsable: " + field.getFieldValue());
        nombreResponsable = field.getFieldValue();
        // e.setNombre(c.getValorCampo());
      }

      else if (field.getFieldIdentity().equals("razonSocial")) {

        logger.debug("razonSocial: " + field.getFieldValue());
        razonSocial = field.getFieldValue();
        // e.setNombre(c.getValorCampo());
      }

      else if (field.getFieldIdentity().equals("primerApellidoResponsable")) {
        logger.debug("Apellido1: " + field.getFieldValue());
        firmaInfo.setApellido1(field.getFieldValue());
      }

      else if (field.getFieldIdentity().equals("segundoApellidoResponsable")) {
        logger.debug("Apellido2: " + field.getFieldValue());
        firmaInfo.setApellido2(field.getFieldValue());
      }

      else if (field.getFieldIdentity().equals("NIFResponsable")) {
        logger.debug("NIFResponsable: " + field.getFieldValue());
        nifResponsable = field.getFieldValue();
        // e.setNifcif(c.getValorCampo());
      }

      else if (field.getFieldIdentity().equals("NIF-CIF")) {
        logger.debug("NIF-CIF: " + field.getFieldValue());
        NIFCIF = field.getFieldValue();
        // e.setNifcif(c.getValorCampo());
      } else if (field.getFieldIdentity().equals("entidadSuscriptora")) {
        logger.debug("entidadSuscriptora: " + field.getFieldValue());
        entidadSuscriptora = field.getFieldValue();
      }

      else if (field.getFieldIdentity().equals("NIFEntidadSuscriptora")) {
        logger.debug("NIFEntidadSuscriptora: " + field.getFieldValue());
        NIFEntidadSuscriptora = field.getFieldValue();
      }

      else if (field.getFieldIdentity().equals("seudonimo")) {
        logger.debug("seudonimo: " + field.getFieldValue());
        seudonimo = field.getFieldValue();
      }

      else if (field.getFieldIdentity().equals("ApellidosResponsable")) {
        apellidos = field.getFieldValue();
      }

    } // end for

    // aniade tratar posibilidad seudonimo. Si existe seudonimo, es el prioritario en informar
    // dejando los demas fuera
    if (seudonimo != null && !seudonimo.contentEquals("")) {
      firmaInfo.setNifcif("seudonimo");
    } else if (nifResponsable != null && !nifResponsable.contentEquals("")) {
      firmaInfo.setNifcif(nifResponsable);
    } else if (NIFCIF != null && !NIFCIF.contentEquals("")) {
      firmaInfo.setNifcif(NIFCIF);
    } else if (NIFEntidadSuscriptora != null && !NIFEntidadSuscriptora.contentEquals("")) {
      firmaInfo.setNifcif(NIFEntidadSuscriptora);
    } else {
      firmaInfo.setNifcif("");
    }

    if (seudonimo != null && !seudonimo.contentEquals("")) {
      firmaInfo.setNombre(seudonimo);
    } else if (nombreResponsable != null && !nombreResponsable.contentEquals("")) {
      firmaInfo.setNombre(nombreResponsable);
    } else if (razonSocial != null && !razonSocial.contentEquals("")) {
      firmaInfo.setNombre(razonSocial);
    } else if (entidadSuscriptora != null && !entidadSuscriptora.contentEquals("")) {
      firmaInfo.setNombre(entidadSuscriptora);
    } else {
      firmaInfo.setNombre("");
    }

    // Informacion del timestamp
    if (tstContent != null) {
      if (tstContent.getCreationTime() != null) {
        XMLGregorianCalendar greg = tstContent.getCreationTime();
        firmaInfo.setExtras(firmaInfo.getExtras() + " - (Sello de Tiempo: "
            + MiscUtil.dateToString(greg.toGregorianCalendar().getTime(), "dd/MM/yyyy HH:mm")
            + ")");
      }

    }

    // Si no llegan los apellidos pero se informan en el campo ApellidosResponsable,
    // los metemos en el primer apellido, para evitar problemas con los apellidos compuestos E.G.
    // "De la Paz"
    if (siApellidosJuntos(firmaInfo, apellidos)) {
      firmaInfo.setApellido1(apellidos);
    }
  }

  private static boolean siApellidosJuntos(FirmaInfoAfirma firmaInfo, String apellidos) {
    return "".equals(firmaInfo.getApellido1()) && "".equals(firmaInfo.getApellido2())
        && apellidos != null;
  }

  /**
   * Obtiene la lista de ReadableCertificateInfo de una respuesta DSS de verificación de firma.
   * 
   * @param verifyResponse
   * @return
   */
  /*
   * public static List<ReadableCertificateInfo> getReadableCertificateInfoList (ResponseBaseType
   * verifyResponse) {
   * 
   * List<ReadableCertificateInfo> readableCertificateInfoList = new
   * ArrayList<ReadableCertificateInfo> ();
   * 
   * // Obtenemos el VerificationReport del OptionalOutputs List<Object> optionalOutputs =
   * verifyResponse.getOptionalOutputs().getAny();
   * 
   * VerificationReportType vrt = (VerificationReportType)
   * DSSUtil.getObjectByClass(VerificationReportType.class, optionalOutputs);
   * 
   * List<IndividualSignatureReportType> indReportList = vrt.getIndividualSignatureReport();
   * 
   * for (IndividualSignatureReportType report : indReportList) { List<Object> details =
   * report.getDetails().getAny(); ReadableCertificateInfo certInfo = (ReadableCertificateInfo)
   * DSSUtil.getObjectByClass(ReadableCertificateInfo.class, details); if (certInfo != null) {
   * readableCertificateInfoList.add(certInfo); } }
   * 
   * return readableCertificateInfoList; }
   */

  /**
   * Obtiene la lista de ReadableCertificateInfo y de TstContent de una respuesta DSS de
   * verificación de firma.
   * 
   * @param verifyResponse
   * @return
   */
  public static List<ReadableCertificateInfoAndTstContentType> getReadableCertificateInfoAndTimeStampContentList(
      ResponseBaseType verifyResponse) {

    List<ReadableCertificateInfoAndTstContentType> readableCertificateInfoAndTimeStampContentList =
        new ArrayList<ReadableCertificateInfoAndTstContentType>();

    // Obtenemos el VerificationReport del OptionalOutputs
    List<Object> optionalOutputs = verifyResponse.getOptionalOutputs().getAny();

    VerificationReportType vrt = (VerificationReportType) DSSUtil
        .getObjectByClass(VerificationReportType.class, optionalOutputs);

    List<IndividualSignatureReportType> indReportList = vrt.getIndividualSignatureReport();

    for (IndividualSignatureReportType report : indReportList) {
      ReadableCertificateInfoAndTstContentType certInfoAndTst =
          new ReadableCertificateInfoAndTstContentType();
      // Añadiremos el elemento si encontramos info del certificado o del timestamp
      boolean add = false;

      // Buscamos info del certificado
      List<Object> details = report.getDetails().getAny();
      ReadableCertificateInfo certInfo = (ReadableCertificateInfo) DSSUtil
          .getObjectByClass(ReadableCertificateInfo.class, details);
      if (certInfo != null) {
        certInfoAndTst.setReadableCertificateInfo(certInfo);
        add = true;
      }

      // Info del sello de tiempo
      DetailedReportType dtr =
          (DetailedReportType) DSSUtil.getObjectByClass(DetailedReportType.class, details);
      TstContentType tstContent = getTimeStampContent(dtr);
      if (tstContent != null) {
        certInfoAndTst.setTimeStampContent(tstContent);
        add = true;
      }

      if (add) {
        readableCertificateInfoAndTimeStampContentList.add(certInfoAndTst);
      }
    }

    return readableCertificateInfoAndTimeStampContentList;

  }

  /**
   * Obtiene el tipo de firma
   * 
   * @param verifyResponse
   * @return
   */
  public static String getSignatureType(ResponseBaseType verifyResponse) {

    List<Object> optionalOutputs = verifyResponse.getOptionalOutputs().getAny();

    JAXBElement<?> jaxbElement = getJAXBElementByQName(
        new QName("urn:oasis:names:tc:dss:1.0:core:schema", "SignatureType"), optionalOutputs);

    return jaxbElement.getValue().toString();
  }

  /**
   * Devuelve el objeto de tipo TstContentType a partir del elemento DetailedReport
   * 
   * @param detailedReport
   * @return null si no lo encuentra.
   */
  public static TstContentType getTimeStampContent(DetailedReportType detailedReport) {

    TstContentType tstContent = null;

    if (detailedReport != null) {

      if (detailedReport.getProperties() != null) {

        if (detailedReport.getProperties().getUnsignedProperties() != null) {

          if (detailedReport.getProperties().getUnsignedProperties()
              .getUnsignedSignatureProperties() != null) {

            if (detailedReport.getProperties().getUnsignedProperties()
                .getUnsignedSignatureProperties()
                .getCounterSignatureOrSignatureTimeStampOrCompleteCertificateRefs() != null) {

              List<Object> objs = detailedReport.getProperties().getUnsignedProperties()
                  .getUnsignedSignatureProperties()
                  .getCounterSignatureOrSignatureTimeStampOrCompleteCertificateRefs();

              TimeStampValidityType tsvt = (TimeStampValidityType) DSSUtil
                  .getObjectByClass(TimeStampValidityType.class, objs);

              if (tsvt != null) {
                tstContent = tsvt.getTimeStampContent();
              }
            }
          }

        }
      }
    }

    return tstContent;

  }


  /**
   * Obtiene una lista de SignedDataRefType de dentro de un SignedDataInfo
   * 
   * @param signedDataInfo objeto donde se quieren buscar SignedDataRefType
   * @return
   */
  public static List<SignedDataRefType> getSignedDataRefListFromSignedDataInfo(
      SignedDataInfo signedDataInfo) {

    List<SignedDataRefType> signedDataRefList = new ArrayList<SignedDataRefType>();

    if (signedDataInfo != null && signedDataInfo.getDataInfo() != null) {

      for (DataInfoType dataInfo : signedDataInfo.getDataInfo()) {

        if (dataInfo.getSignedDataRefs() != null
            && dataInfo.getSignedDataRefs().getSignedDataRef() != null) {

          for (SignedDataRefType signedDataRef : dataInfo.getSignedDataRefs().getSignedDataRef()) {

            signedDataRefList.add(signedDataRef);

          }

        }

      }
    }

    return signedDataRefList;
  }

  public static SignedDataRefType getSignedDataRefConEncodingOrFirst(
      SignedDataInfo signedDataInfo) {

    List<SignedDataRefType> signedDataRefList =
        DSSUtil.getSignedDataRefListFromSignedDataInfo(signedDataInfo);
    int i = 0;
    SignedDataRefType signedDataRefConEncodingOrFirst = null;

    // Nos quedamos con el que tenga un elemento "Encoding" dentro
    while (i < signedDataRefList.size() && signedDataRefConEncodingOrFirst == null) {
      if (signedDataRefList.get(i).getEncoding() != null) {
        signedDataRefConEncodingOrFirst = signedDataRefList.get(i);
      }
      i++;
    }


    // Si no lo encontramos, nos quedamos con el primero de la lista
    if (signedDataRefConEncodingOrFirst == null && signedDataRefList.size() > 0) {
      logger.debug(
          "No se ha encontrado elemento signedDataRef con un elemento Encoding. Se selecciona el primero de la lista");
      signedDataRefConEncodingOrFirst = signedDataRefList.get(0);
    }
    return signedDataRefConEncodingOrFirst;
  }


  public static SignedDataRefType getSignedDataRef(SignedDataInfo signedDataInfo,
      String atributoIDNodoFirmado) {

    List<SignedDataRefType> signedDataRefList =
        DSSUtil.getSignedDataRefListFromSignedDataInfo(signedDataInfo);
    int i = 0;
    SignedDataRefType signedDataRefAtributoIDFirmado = null;

    // Nos quedamos con el que tenga un elemento "Encoding" dentro
    while (i < signedDataRefList.size() && signedDataRefAtributoIDFirmado == null) {
      if (signedDataRefList.get(i).getXPath() != null
          && signedDataRefList.get(i).getXPath().contains(atributoIDNodoFirmado)) {
        signedDataRefAtributoIDFirmado = signedDataRefList.get(i);
      }
      i++;
    }

    return signedDataRefAtributoIDFirmado;
  }


  public static String getInfoResult(Result result) {
    StringBuffer sb = new StringBuffer("");
    sb.append(" ResultMajor: " + result.getResultMajor());
    sb.append(" ResultMinor: " + result.getResultMinor());
    sb.append(" Descripcion: " + result.getResultMessage().getValue());
    return sb.toString();
  }



  /**
   * Devuelve una expresión xpath válida, a partir de una devuelta por el servicio de Afirma.
   * 
   * @param xpathExpression
   * @return
   */
  public static String expresionXpathValida(String xpathExpression) {
    return MiscUtil.meteComillasXPath(MiscUtil.meteAsteriscoXPath(xpathExpression));
  }

}
