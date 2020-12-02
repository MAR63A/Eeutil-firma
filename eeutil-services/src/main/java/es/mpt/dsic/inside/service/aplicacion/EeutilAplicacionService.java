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

package es.mpt.dsic.inside.service.aplicacion;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import es.mpt.dsic.csvstorage.admin.model.CSVStorageException;
import es.mpt.dsic.csvstorage.admin.model.CSVStorageException_Exception;
import es.mpt.dsic.eeutil.csvbroker.admin.model.CSVBrokerException;
import es.mpt.dsic.eeutil.inside.admin.model.InsideWSException;
import es.mpt.dsic.inside.model.aplicacion.AplicacionObject;

public interface EeutilAplicacionService {

  List<AplicacionObject> getAplicaciones(String app)
      throws InsideWSException, CSVStorageException, CSVBrokerException;

  AplicacionObject desactivar(String app, AplicacionObject aplicacion)
      throws InsideWSException, CSVStorageException, CSVBrokerException;

  AplicacionObject activar(String app, AplicacionObject aplicacion)
      throws InsideWSException, CSVStorageException, CSVBrokerException;

  void eliminarAplicacion(String app, AplicacionObject aplicacion, Locale locale)
      throws InsideWSException, CSVStorageException, CSVBrokerException;

  AplicacionObject altaAplicacion(String app, AplicacionObject aplicacion)
      throws InsideWSException, CSVStorageException, CSVBrokerException;

  Map<String, String> getInfAdicional(String app) throws InsideWSException,
      CSVStorageException_Exception, CSVStorageException, CSVBrokerException;
}
