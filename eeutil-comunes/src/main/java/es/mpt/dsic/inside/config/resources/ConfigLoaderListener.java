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

import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import es.mpt.dsic.inside.config.EeutilApplicationDataConfig;
import es.mpt.dsic.inside.config.EeutilConfigPath;


public class ConfigLoaderListener implements ServletContextListener {

  // Variable de configuraci�n para el uso de Clave
  public static final String CONFIG_PATH_VAR_FOR_CLAVE = "clave.config.path";


  public void contextInitialized(ServletContextEvent event) {
    Properties props =
        EeutilApplicationDataConfig.loadProperties(EeutilApplicationDataConfig.APP_PROPERTIES_FILE);

    // Si no encuentra la variable la toma del par�metro establecido en el web.xml
    String configPathProperty =
        System.getProperty(props.getProperty(EeutilApplicationDataConfig.APPLICATION_NAME_PROPERTY)
            + "." + EeutilConfigPath.CONFIG_PATH_VAR);
    if (configPathProperty == null || configPathProperty.contentEquals("")) {

      ServletContext servletContext = event.getServletContext();
      String configPathParam = servletContext.getInitParameter(configPathProperty);
      System.setProperty(configPathProperty, configPathParam);
    }
    System.setProperty(CONFIG_PATH_VAR_FOR_CLAVE, configPathProperty);
  }

  public void contextDestroyed(ServletContextEvent event) {

  }

}
