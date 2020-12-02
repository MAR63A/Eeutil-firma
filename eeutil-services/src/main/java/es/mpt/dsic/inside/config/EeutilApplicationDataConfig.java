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

package es.mpt.dsic.inside.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Log4jConfigurer;


public class EeutilApplicationDataConfig {

  public static String APLICACION = "sin-definir";
  public static final String APPLICATION_NAME_PROPERTY = "application.name";

  public static String CONFIG_PATH = null;
  private static final String CONFIG_PATH_SYSTEM_VAR = EeutilConfigPath.CONFIG_PATH_VAR;
  // private static final String CONFIG_PATH_SYSTEM_VAR = "config.path";
  public final static String APP_PROPERTIES_FILE = "application.properties";
  private static boolean CARGADO_PATH = false;

  private static String LOAD_LOGGER_NAME_PROPERTY = "log.load";
  private static String LOAD_LOGGER_DEFUALT_FILE_NAME = "/log4j.properties";

  private static boolean LOAD_LOGGER = false;



  protected final static Log logger = LogFactory.getLog(EeutilApplicationDataConfig.class);
  static {
    if (!CARGADO_PATH) {
      // Busca fichero de propiedades
      Properties props = loadProperties(APP_PROPERTIES_FILE);

      // Comprueba la existencia de la variable de sistema para el path de configuracion
      String configPath = System
          .getProperty(props.getProperty(APPLICATION_NAME_PROPERTY) + "." + CONFIG_PATH_SYSTEM_VAR);
      if (configPath == null) {
        // Si no se ha especificado mediante propiedad de sistema, se busca en el
        // entorno
        configPath = System
            .getenv(props.getProperty(APPLICATION_NAME_PROPERTY) + "." + CONFIG_PATH_SYSTEM_VAR);
      }

      if (configPath == null) {
        System.out.println("No se especifico la variable de sistema o de entorno "
            + props.getProperty(APPLICATION_NAME_PROPERTY) + "." + CONFIG_PATH_SYSTEM_VAR
            + "; los ficheros de configuracion se cargaran utilizando el DefaultResourceLoader");
      } else {
        setConfigPath(configPath, props);
        configLog(props);
        logger.debug("Encontrada variable " + props.getProperty(APPLICATION_NAME_PROPERTY) + "."
            + CONFIG_PATH_SYSTEM_VAR + ": " + configPath);
      }
      CARGADO_PATH = true;
    }
  }

  static void configLog(Properties props) {
    if (LOAD_LOGGER) {
      try {
        logger.debug("CAMBIANDO EL FICHERO DE LOGGER");
        File fileLog = new File(CONFIG_PATH + System.getProperty("file.separator") + "log4j-"
            + props.getProperty(APPLICATION_NAME_PROPERTY) + ".properties");
        if (fileLog.exists()) {
          logger.debug("Estableciendo sistema de log por defecto:" + LOAD_LOGGER_DEFUALT_FILE_NAME);
          Log4jConfigurer.initLogging(fileLog.getAbsolutePath());
        } else {
          logger.debug("Estableciendo sistema de log por defecto:" + LOAD_LOGGER_DEFUALT_FILE_NAME);
          Log4jConfigurer.initLogging(CONFIG_PATH + LOAD_LOGGER_DEFUALT_FILE_NAME);
        }
      } catch (FileNotFoundException e) {
        System.out.println("ERROR CARGANDO EL FICHERO DE TRAZAS " + e);
      }
    }
  }

  static void setConfigPath(String configPath, Properties props) {
    logger.debug("Directorio de configuracion: " + configPath);

    CONFIG_PATH = configPath;

    // Carga las propiedades de nombre de version y aplicacion
    APLICACION = props.getProperty(APPLICATION_NAME_PROPERTY);
    System.setProperty("config.path", configPath);
    String sLog = props.getProperty(LOAD_LOGGER_NAME_PROPERTY);
    if (sLog == null) {
      sLog = "N";
    }
    LOAD_LOGGER = sLog.equals("N") ? false : true;

    // DE momento no nos metemos con la version
    /*
     * ApplicationConfig.setApplicationVersion(props .getProperty(APPLICATION_VERSION_PROPERTY));
     */
    if ((APLICACION == null) || APLICACION.equals("sin-definir")) {
      logger.warn("No se encontro la propiedad " + APPLICATION_NAME_PROPERTY + " en el fichero "
          + APP_PROPERTIES_FILE + "; los ficheros de configuracion se buscaron en el directorio "
          + CONFIG_PATH);
    }
  }

  public static void setAplicacion(String aplicacion) {
    APLICACION = aplicacion;
  }

  public static String getString() {
    return "APLICACION " + APLICACION;
  }

  public static Properties loadProperties(String file) {
    ResourceLoader loader = new DefaultResourceLoader();
    Resource configFileResource = loader.getResource(file);
    Properties props = new Properties();
    try {
      props.load(configFileResource.getInputStream());
      configFileResource.getInputStream().close();
    } catch (Exception e) {
      logger.warn("No se encontro el fichero " + APP_PROPERTIES_FILE
          + "; los ficheros de configuracion se buscaran solo en el directorio " + CONFIG_PATH);
    }
    return props;
  }
}
