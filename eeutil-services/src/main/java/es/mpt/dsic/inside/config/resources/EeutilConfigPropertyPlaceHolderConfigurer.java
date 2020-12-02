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
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class EeutilConfigPropertyPlaceHolderConfigurer extends PropertyPlaceholderConfigurer {

  /**
   * ResourceLoader utilizado para cargar los ficheros de propiedades
   */
  private ResourceLoader resourceLoader;
  private String fichero = "";

  @Override
  protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
      Properties props) throws BeansException {

    super.processProperties(beanFactoryToProcess, props);
  }


  /**
   * Constructor
   */
  public EeutilConfigPropertyPlaceHolderConfigurer() {
    this.resourceLoader = new EeutilConfigResourceLoader(new DefaultResourceLoader());

    // Por defecto, permite que haya placeholders sin resolver (para que se resuelvan mediante
    // otros)
    setIgnoreUnresolvablePlaceholders(true);
  }

  /**
   * Establece la localizacion del recurso del que leer las propiedades
   * 
   * @param location localizacion del recurso del que leer las propiedades
   */
  public void setFichero(String location) {
    this.fichero = location;
    setFicheros(new String[] {location});
  }

  /**
   * Establece la localizacion de los recurso de los que leer las propiedades
   * 
   * @param locations localizacion de los recurso de los que leer las propiedades
   */

  public void setFicheros(String[] locations) {
    // Obtiene las localizaciones usando el ConfigResourceLoader, y las inyecta en
    // el configurador
    Resource[] configLocations = new Resource[locations.length];
    for (int i = 0; i < locations.length; i++) {
      configLocations[i] = this.resourceLoader.getResource(locations[i]);
    }

    setLocations(configLocations);
  }

  /**
   * Establece el ResourceLoader que se utilizara como <i>fallback</i> para encontrar los recursos
   * si no se encuentran mediante el ConfigResourceLoader
   * 
   * @param resourceLoader ResourceLoader a utilizar
   * @see org.springframework.context.ResourceLoaderAware#setResourceLoader(org.springframework.core.io.ResourceLoader)
   */
  public void setFallbackResourceLoader(ResourceLoader resourceLoader) {
    this.resourceLoader = new EeutilConfigResourceLoader(resourceLoader);
  }

}
