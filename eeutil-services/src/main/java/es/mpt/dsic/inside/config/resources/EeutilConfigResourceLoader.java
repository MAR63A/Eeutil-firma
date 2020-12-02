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

package es.mpt.dsic.inside.config.resources;

import java.io.File;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import es.mpt.dsic.inside.config.EeutilApplicationDataConfig;

public class EeutilConfigResourceLoader implements ResourceLoader {

  protected final static Log logger = LogFactory.getLog(EeutilConfigResourceLoader.class);

  /**
   * ResourceLoader a utilizar cuando no se encuentre un recurso
   */
  private ResourceLoader resourceLoader;

  /**
   * Constructor. Utiliza un DefaultResourceLoader para encontrar los recursos que no encuentre en
   * configuracion
   */
  public EeutilConfigResourceLoader() {
    this.resourceLoader = new DefaultResourceLoader();
  }

  /**
   * Constructor. Utiliza el resourceLoader indicado para encontrar los recursos que no encuentre en
   * configuracion
   *
   * @param resourceLoader ResourceLoader para encontrar los recursos que no encuentre en
   *        configuracion
   */
  public EeutilConfigResourceLoader(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  /**
   * Obtiene el recurso especificado a partir del directorio de configuracion, o utilizando el
   * ResourceLoader indicado en el constructor si no lo encuentra.
   *
   * @param location localizacion del recurso
   * @return recurso encontrado
   * @see org.springframework.core.io.ResourceLoader#getResource(java.lang.String)
   */
  public Resource getResource(String location) {

    Assert.notNull(location, "Location cannot be null");

    // Si no se definioel directorio de configuracion, carga utilizando el
    // ResourceLoader
    if (EeutilApplicationDataConfig.CONFIG_PATH == null) {
      return this.resourceLoader.getResource(location);
    }

    File configFile = new File(StringUtils.cleanPath(EeutilApplicationDataConfig.CONFIG_PATH));
    String configPath = configFile.getAbsolutePath();

    // Comprueba la existencia de los ficheros, probando la siguiente ruta:
    // <variable entorno CONFIG_PATH> / <nombre aplicacion> / <version aplicacion>
    // de mas especifico a mas general
    StringBuffer path = new StringBuffer(200);
    path.append(configPath);

    if ((EeutilApplicationDataConfig.APLICACION != null)
        && !EeutilApplicationDataConfig.APLICACION.equals("sin-definir")) {
      path.append(File.separator).append(EeutilApplicationDataConfig.APLICACION);


    }


    File parentFile = new File(path.toString());
    File searchedFile = new File(parentFile, location);

    while (!searchedFile.exists() && (parentFile != null)) {
      if (logger.isDebugEnabled()) {
        logger.debug("El fichero de configuracion " + location + " no existe en "
            + searchedFile.getAbsolutePath());
      }

      // Si ya estamos en el directorio de configuracion, salimos
      if (parentFile.equals(configFile)) {
        break;
      }

      parentFile = parentFile.getParentFile();
      searchedFile = new File(parentFile, location);

      if (logger.isDebugEnabled()) {
        logger.debug("Buscando fichero de configuracion " + searchedFile.getAbsolutePath());
      }

    }

    // Si no se encuentra, se busca usando el ResourceLoader alternativo
    if (!searchedFile.exists()) {
      logger.warn("No se encontro el fichero de configuracion " + location
          + "; se busca mediante el ResourceLoader en el CLASSPATH "
          + this.resourceLoader.getClass());

      return this.resourceLoader.getResource("classpath:" + location);
    }

    logger.info(
        "Usando configuracion " + location + " del fichero " + searchedFile.getAbsolutePath());

    // Se devuelve como un recurso de tipo FileSystemResource
    return new FileSystemResource(searchedFile);

  }

  /**
   * Devuelve el ClassLoader del ResourceLoader
   *
   * @return ClassLoader del ResourceLoader
   * @see org.springframework.core.io.ResourceLoader#getClassLoader()
   */
  public ClassLoader getClassLoader() {
    return this.resourceLoader.getClassLoader();
  }
}
