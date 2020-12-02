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

package es.mpt.dsic.inside.security.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import es.mpt.dsic.inside.security.model.AppInfo;
import es.mpt.dsic.inside.security.service.AplicacionInfoService;

@Service("aplicacionContext")
@Lazy(value = false)
public class EeutilAplicacionContext implements AplicacionContext {

  @Autowired
  private AplicacionInfoService aplicacionInfoService;

  @Override
  public String getAplicacionId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      return null;
    }

    return authentication.getName();
  }

  @Override
  public AppInfo getAplicacionInfo() {
    String appName = getAplicacionId();
    if (appName == null) {
      return null;
    }
    return this.aplicacionInfoService.getAplicacionInfo(appName);
  }

}
