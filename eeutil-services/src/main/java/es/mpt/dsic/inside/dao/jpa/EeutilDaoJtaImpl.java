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

package es.mpt.dsic.inside.dao.jpa;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import es.mpt.dsic.inside.dao.EeutilDao;

@Repository("inSideDao")
public class EeutilDaoJtaImpl implements EeutilDao {

  @PersistenceContext
  private EntityManager em;


  @Override
  @Transactional
  public void salvar(Object bean) {
    this.em.persist(bean);
    this.em.flush();
  }

  @Override
  @Transactional
  public Object update(Object bean) {
    return this.em.merge(bean);
  }

  @Override
  @Transactional
  public Object getObjeto(Class bean, Object id) {
    return this.em.find(bean, id);
  }

  @Override
  public EntityManager getEntityManager() {
    return this.em;
  }

  @Override
  @Transactional(readOnly = true)
  public List getAllObjetos(Class bean) {

    Session session = (Session) getEntityManager().getDelegate();
    Criteria crit = session.createCriteria(bean);
    return crit.list();
  }


  @Override
  @Transactional(readOnly = true)
  public <T> Object findObjeto(Class<T> bean, List<Criterion> criterias) {
    Session session = (Session) this.em.getDelegate();
    Criteria crit = session.createCriteria(bean);
    for (Criterion criteria : criterias) {
      crit.add(criteria);
    }
    return crit.uniqueResult();

    /*
     * ejemplo de uso List<Criterion> criterias = new ArrayList<Criterion>(); ObjetoInsideUsuario
     * usuarioInside = null; criterias.add(Restrictions.eq("nombreColumna", valorColumna));
     * eeutilServiceJta.findObjeto(Bean.class, criterias);
     */
  }

  @Override
  @Transactional(readOnly = true)
  public void executeUpdate(String queryString, String... args) {
    Query query = em.createQuery(queryString);
    int position = 1;
    for (String arg : args) {
      query.setParameter(position, arg);
      position++;
    }
    query.executeUpdate();
  }

  @Override
  @Transactional(readOnly = true)
  public void remove(Object bean) {
    this.em.remove(this.em.contains(bean) ? bean : em.merge(bean));
    this.em.flush();
  }

  @SuppressWarnings("unchecked")
  @Override
  @Transactional(readOnly = true)
  public <T> List<T> findObjetos(Class<T> bean, List<Criterion> criterias) {
    Session session = (Session) this.em.getDelegate();
    Criteria crit = session.createCriteria(bean);
    for (Criterion criteria : criterias) {
      crit.add(criteria);
    }
    return crit.list();
  }

  @Override
  @Transactional(readOnly = true)
  public void executeStoreProcedure(String procedureName) {
    Query storeProcedure = em.createNativeQuery(procedureName);
    storeProcedure.executeUpdate();
  }

  @SuppressWarnings("unchecked")
  @Override
  @Transactional(readOnly = true)
  public <T> List<T> findObjetosWithOrder(Class<T> bean, List<Criterion> criterias,
      List<Order> fieldsOrder) {
    Session session = (Session) this.em.getDelegate();
    Criteria crit = session.createCriteria(bean);
    for (Criterion criteria : criterias) {
      crit.add(criteria);
    }
    if (CollectionUtils.isNotEmpty(fieldsOrder)) {
      for (Order fieldOrder : fieldsOrder) {
        crit.addOrder(fieldOrder);
      }
    }
    return crit.list();
  }

}
