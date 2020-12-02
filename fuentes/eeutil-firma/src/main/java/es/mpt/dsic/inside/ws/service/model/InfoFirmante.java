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

package es.mpt.dsic.inside.ws.service.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import es.mpt.dsic.inside.util.firma.model.DatosFirma;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosFirmante",
    propOrder = {"nombreFirmante", "numeroIdentificacion", "mailFirmante", "entidadCertificadora"})

public class InfoFirmante {
  @XmlElement(required = false, name = "nombreFirmante")
  public String nombreFirmante;
  @XmlElement(required = false, name = "numeroIdentificacion")
  public String numeroIdentificacion;
  @XmlElement(required = false, name = "mailFirmante")
  public String mailFirmante;
  @XmlElement(required = false, name = "entidadCertificadora")
  public String entidadCertificadora;

  public InfoFirmante() {}

  public InfoFirmante(DatosFirma datosFirma) {
    this.nombreFirmante = datosFirma.getNombreFirmante();
    this.numeroIdentificacion = datosFirma.getNumeroIdentificacion();
    this.mailFirmante = datosFirma.getMailFirmante();
    this.entidadCertificadora = datosFirma.getEntidadCertificadora();
  }

  public String getNombreFirmante() {
    return nombreFirmante;
  }

  public void setNombreFirmante(String nombreFirmante) {
    this.nombreFirmante = nombreFirmante;
  }

  public String getNumeroIdentificacion() {
    return numeroIdentificacion;
  }

  public void setNumeroIdentificacion(String numeroIdentificacion) {
    this.numeroIdentificacion = numeroIdentificacion;
  }

  public String getMailFirmante() {
    return mailFirmante;
  }

  public void setMailFirmante(String mailFirmante) {
    this.mailFirmante = mailFirmante;
  }

  public String getEntidadCertificadora() {
    return entidadCertificadora;
  }

  public void setEntidadCertificadora(String entidadCertificadora) {
    this.entidadCertificadora = entidadCertificadora;
  }

  @Override
  public String toString() {
    return "InfoFirmante [nombreFirmante=" + nombreFirmante + ", numeroIdentificacion="
        + numeroIdentificacion + ", mailFirmante=" + mailFirmante + ", entidadCertificadora="
        + entidadCertificadora + "]";
  }



}

