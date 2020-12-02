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

package es.mpt.dsic.inside.security.service.impl;

import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.mpt.dsic.inside.dao.EeutilDao;
import es.mpt.dsic.inside.model.PeticionesPDFA;
import es.mpt.dsic.inside.security.service.AplicacionConversionService;


@Service("aplicacionConversionService")
public class EeutilAplicacionConversionService implements AplicacionConversionService {

  protected final static Log logger = LogFactory.getLog(EeutilAplicacionConversionService.class);

  @Autowired
  private EeutilDao eeutilDao;


  @Override
  public void saveAplicacionConversionInfo(String appId, int numberPages) {

    PeticionesPDFA peticion = new PeticionesPDFA();
    peticion.setIdAplicacion(appId);
    peticion.setFechaPeticion(new Date());
    peticion.setNumeroPaginas(numberPages);
    eeutilDao.salvar(peticion);
  }


}
