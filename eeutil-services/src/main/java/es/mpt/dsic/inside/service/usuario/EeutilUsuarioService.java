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

package es.mpt.dsic.inside.service.usuario;

import java.util.List;
import es.mpt.dsic.eeutil.inside.admin.model.InsideWSException;
import es.mpt.dsic.inside.model.pagination.FilterPageRequest;
import es.mpt.dsic.inside.model.pagination.ResultadoBusqueda;
import es.mpt.dsic.inside.model.procedimiento.ProcedimientoObject;
import es.mpt.dsic.inside.model.unidad.UnidadObject;
import es.mpt.dsic.inside.model.usuario.UsuarioObject;

public interface EeutilUsuarioService {

  ResultadoBusqueda<UsuarioObject> getUsuarios(String app, FilterPageRequest filterEeutil)
      throws InsideWSException;

  UsuarioObject bajaUsuario(String app, UsuarioObject usuario) throws InsideWSException;

  UsuarioObject altaUsuario(String app, UsuarioObject usuario) throws InsideWSException;

  UsuarioObject altaUsuario(String app, UsuarioObject usuario, String role)
      throws InsideWSException;

  List<UnidadObject> getUsuariosFromNif(String nifUsuario) throws InsideWSException;

  List<ProcedimientoObject> getListaProcedimientos(String nifUsuario) throws InsideWSException;

  void borrarUnidadUsuario(String nifUsuario, String codigoUnidadOrganica,
      String numeroProcedimiento) throws InsideWSException;


}
