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

package es.mpt.dsic.inside.utils.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;


public class IOUtil {

  public static byte[] getBytesFromFile(File file) throws IOException {
    InputStream is = new FileInputStream(file);
    long length = file.length();

    if (length > Integer.MAX_VALUE) {
    }

    byte[] bytes = new byte[(int) length];

    int offset = 0;
    int numRead = 0;
    while (offset < bytes.length
        && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
      offset += numRead;
    }
    if (offset < bytes.length) {
      throw new IOException("Could not completely read file " + file.getName());
    }

    is.close();
    return bytes;
  }



  /**
   * Obtiene los bytes a partir de un objeto.
   * 
   * @param source Objeto del que se quieren obtener los bytes.
   * @return array de bytes
   * @throws IOException si no se puede leer la fuente.
   */
  public static byte[] getBytesFromObject(Object source) throws IOException {
    byte[] ret = null;
    InputStream is = null;
    if (source instanceof byte[]) {
      byte[] s = (byte[]) source;
      ret = s;
    } else if (source instanceof File) {
      File f = (File) source;
      is = new FileInputStream(f);
      ByteArrayOutputStream bout = new ByteArrayOutputStream();
      IOUtils.copyLarge(is, bout);
      ret = bout.toByteArray();
      bout.close();
      is.close();
    } else if (source instanceof InputStream) {
      is = (InputStream) source;
      ByteArrayOutputStream bout = new ByteArrayOutputStream();
      IOUtils.copyLarge(is, bout);
      ret = bout.toByteArray();
      bout.close();
      is.close();
    }
    return ret;
  }



  public static byte[] getBytes(InputStream is) throws IOException {

    return fromInputStreamToByteArray(is);
  }

  public static byte[] toByteArray(InputStream is) throws IOException {
    return fromInputStreamToByteArray(is);
  }

  public static InputStream toInputStream(byte[] ba) throws IOException {

    return new ByteArrayInputStream(ba);
  }



  public static byte[] fromInputStreamToByteArray(InputStream is) {

    byte[] copyToByteArray = null;
    try {
      if (is.markSupported() == true)
        is.reset();
      copyToByteArray = IOUtils.toByteArray(is);
      // copyToByteArray = FileCopyUtils.copyToByteArray(is);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return copyToByteArray;
  }

  /**
   * Formatea el csv: Cada "num" elementos escribe el String "ss" Ejemplo: csv="123456789", num=4,
   * ss="-" devolvería 1234-6789
   * 
   * @param csv csv a formatear
   * @param num cada cuantos elementos se desea escribir el String "ss"
   * @param ss cadena que se desea introducir
   * @return el csv formateado.
   */
  public static String formatCSV(String csv, int num, String ss) {
    String result = "";
    if (csv.length() < num) {
      result = csv;
    } else {
      int i = 0;
      while (i + num <= csv.length()) {
        String aux = csv.substring(i, i + num);
        result = result + ss + aux;
        i += num;
      }
      if (i < csv.length()) {
        String aux = csv.substring(i, csv.length());
        result = result + ss + aux;
      }
      result = result.substring(1);
    }
    return result;

  }

}
