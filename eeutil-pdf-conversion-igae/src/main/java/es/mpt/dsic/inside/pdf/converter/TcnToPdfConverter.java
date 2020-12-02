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

package es.mpt.dsic.inside.pdf.converter;

import java.io.File;
import org.springframework.stereotype.Component;
import es.mpt.dsic.inside.pdf.exception.PdfConversionException;
import es.mpt.dsic.inside.utils.file.FileUtil;
import ip2.gdoc.transformacion.transform.tcn.pdf.impl.TransformTcn;

@Component
public class TcnToPdfConverter {

  private static final String TELCON_PREFIX = "telcon";

  /**
   * Convierte un fichero TCN a PDF
   * 
   * @param entrada
   * @return
   * @throws Exception
   */
  public File convertTCNToPdf(byte[] entrada) throws PdfConversionException {
    try {
      // Se genera el pdf a partir del fichero TCN con ayuda de las
      // librer�as de IGAE
      TransformTcn tcn = new TransformTcn();
      byte[] salidaTCN = tcn.transformar(entrada);

      // Se copia el fichero generado al fichero que devolveremos
      String fileReturnPath = FileUtil.createFilePath(TELCON_PREFIX, salidaTCN);

      return new File(fileReturnPath);
    } catch (Throwable t) {
      throw new PdfConversionException("Error convirtiendo TCN a PDF", t);
    }
  }

  /**
   * Convierte un fichero TCN a PDF
   * 
   * @param entrada
   * @return
   * @throws Exception
   */
  public byte[] convertTCNToPdfBytes(byte[] entrada) throws PdfConversionException {
    try {
      // Se genera el pdf a partir del fichero TCN con ayuda de las
      // librer�as de IGAE
      TransformTcn tcn = new TransformTcn();
      byte[] salidaTCN = tcn.transformar(entrada);

      return salidaTCN;
    } catch (Throwable t) {
      throw new PdfConversionException("Error convirtiendo TCN a PDF", t);
    }
  }

}
