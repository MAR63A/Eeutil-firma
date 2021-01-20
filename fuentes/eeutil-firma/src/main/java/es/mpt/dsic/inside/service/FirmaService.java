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

package es.mpt.dsic.inside.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.mpt.dsic.inside.security.model.AppInfo;
import es.mpt.dsic.inside.util.firma.model.PeticionFirmaFichero;
import es.mpt.dsic.inside.util.firma.model.RespuestaFirmaFichero;
import es.mpt.dsic.inside.util.firma.service.MPTFirmaException;
import es.mpt.dsic.inside.ws.service.model.DatosEntradaFichero;

@Service
public class FirmaService {

  @Autowired
  private es.mpt.dsic.inside.util.firma.service.MPTFirmaService mptFirmaService;

  public RespuestaFirmaFichero firmarFichero(AppInfo aplicacion, DatosEntradaFichero entrada)
      throws MPTFirmaException {
    PeticionFirmaFichero peticion = new PeticionFirmaFichero(entrada);
    return mptFirmaService.firmaFichero(aplicacion, peticion);
  }
}
