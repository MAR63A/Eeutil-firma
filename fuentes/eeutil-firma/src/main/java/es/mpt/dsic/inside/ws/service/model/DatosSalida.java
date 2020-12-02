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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosSalida", propOrder = {"estado", "resultado", "salida"})

public class DatosSalida {

  private static String RES_OK = "OK";
  private static String RES_OK_DES = "Ejecucion de firma correcta.";
  private static String RES_ER = "ERROR";
  private static String RES_ER_DES = "Error en la ejecucion de la firma.";

  @XmlElement(required = true, name = "estado")
  private String estado;
  @XmlElement(required = true, name = "resultado")
  private String resultado;
  @XmlElement(required = true, name = "datosResultado")
  private ContenidoSalida salida;

  public DatosSalida() {}

  public DatosSalida(String cadena) {
    this.estado = cadena;
    if (RES_OK.equals(cadena))
      this.resultado = this.RES_OK_DES;
    else
      this.resultado = this.RES_ER_DES;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getResultado() {
    return resultado;
  }

  public void setResultado(String resultado) {
    this.resultado = resultado;
  }

  public ContenidoSalida getSalida() {
    return salida;
  }

  public void setSalida(ContenidoSalida salida) {
    this.salida = salida;
  }

  @Override
  public String toString() {
    return "DatosSalida [estado=" + estado + ", resultado=" + resultado + ", salida=" + salida
        + "]";
  }

}
