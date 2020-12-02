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

package es.mpt.dsic.inside.util;

public class CodigosError {

  public static String COD_0000 = "COD_0000";
  public static String MSJ_0000 = "Error inesperado en el WS";

  public static String COD_0001 = "COD_0001";
  public static String MSJ_0001 =
      "Se ha procesado la firma y el formato de la misma no es un formato valido";

  public static String COD_0002 = "COD_0002";
  public static String MSJ_0002 =
      "El procesamiento de firmas XMLDSig Enveloping no está implementado";

  public static String COD_0003 = "COD_0003";
  public static String MSJ_0003 =
      "El servicio DSS de Afirma no reconoce la firma como un formato valido";

  public static String COD_0004 = "COD_0004";
  public static String MSJ_0004 = "Respuesta del servicio DSS de Afirma inesperado";

  public static String COD_0005 = "COD_0005";
  public static String MSJ_0005 =
      "El servicio DSS de Afirma no ha podido realizar la siguiente operaci�n de ampliaci�n de firma: ";

  public static String COD_0006 = "COD_0006";
  public static String MSJ_0006 =
      "El servicio DSS de Afirma no reconoce el formato de firma al que se desea ampliar para la operaci�n: ";

  public static String COD_0007 = "COD_0007";
  public static String MSJ_0007 =
      "El servicio DSS de Afirma no encuentra el formato de firma al que se desea ampliar para la operaci�n: ";

  public static String COD_0008 = "COD_0008";
  public static String MSJ_0008 = "No se puede incrustar sello de tiempo a las firmas PADES";

  public static String COD_0009 = "COD_0009";
  public static String MSJ_0009 =
      "No se ha podido crear un procesador para la firma. Por favor, compruebe que el formato de la firma se corresponda con uno de los reconocidos.";

  public static String COD_0010 = "COD_0010";
  public static String MSJ_0010 = "Ha ocurrido un error al realizar la llamada a los WS de Afirma.";

  public static String COD_0011 = "COD_0011";
  public static String MSJ_0011 = "No se puede extraer el contenido firmado";
}
