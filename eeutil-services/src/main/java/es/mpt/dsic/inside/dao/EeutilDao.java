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

package es.mpt.dsic.inside.dao;

import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public interface EeutilDao {
  void salvar(Object bean);

  Object update(Object bean);

  Object getObjeto(Class bean, Object id);

  List getAllObjetos(Class bean);

  EntityManager getEntityManager();

  <T> Object findObjeto(Class<T> bean, List<Criterion> criterias);

  // public List findByExample(final Object exampleInstance);
  void executeUpdate(String query, String... args);

  void remove(Object bean);

  <T> List<T> findObjetos(Class<T> bean, List<Criterion> criterias);

  void executeStoreProcedure(String procedureName);

  <T> List<T> findObjetosWithOrder(Class<T> bean, List<Criterion> criterias,
      List<Order> fieldsOrder);
}
