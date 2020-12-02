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

package es.mpt.dsic.inside.util.bbdd;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class VariableConversor {

  protected final static Log logger = LogFactory.getLog(VariableConversor.class);

  public final static String convertir(String value) {
    String retorno = value;

    Matcher matcher = Pattern.compile("\\$\\{([\\w.]+)\\}").matcher(value);
    while (matcher.find()) {
      logger.debug("cadena encontrada:" + matcher.group());
      for (int i = 1; i <= matcher.groupCount(); i++) {
        logger.debug("cadena encontrada:" + matcher.group(i));

        String envValue = System.getProperty(matcher.group(i));
        if (envValue == null) {
          envValue = System.getenv(matcher.group(i));
        }

        if (envValue != null) {
          Pattern subexpr = Pattern.compile("\\$\\{" + matcher.group(i) + "\\}");
          retorno = subexpr.matcher(retorno).replaceAll(envValue);
        } else {
          logger.warn("variable de sistema no encontrada:" + matcher.group(i));
        }
      }
    }

    return retorno;
  }


  /**
   * Devuelve la cadena correspondiente a un objeto Calendar seg�n el est�ndar ISO8601
   * 
   * @param calendar
   * @return
   */
  public static String dateToStringISO8601(Date date) {
    String retorno = "";
    if (date != null) {
      DateTime dt = new DateTime(date.getTime());
      DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
      retorno = dt.toString(fmt);
    }
    return retorno;
  }


  public static void main(String[] args) {
    System.out.println("main");

    String cadena = "${kk}/${kk2}/${local_home_app}";
    System.out.println("cadena:" + cadena);
    String cadenaConvertida = VariableConversor.convertir(cadena);
    System.out.println("cadenaConvertida:" + cadenaConvertida);
  }
}
