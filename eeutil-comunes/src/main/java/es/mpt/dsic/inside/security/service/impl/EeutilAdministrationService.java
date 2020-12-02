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

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.mpt.dsic.inside.convert.ConvertDataEeutil;
import es.mpt.dsic.inside.dao.EeutilDao;
import es.mpt.dsic.inside.model.EeutilAplicacionOperacion;
import es.mpt.dsic.inside.model.EeutilPeticion;
import es.mpt.dsic.inside.model.aplicacionPeticion.AplicacionOperacionObject;
import es.mpt.dsic.inside.model.peticion.PeticionObject;
import es.mpt.dsic.inside.security.service.AdministrationService;

@Service("administrationService")
public class EeutilAdministrationService implements AdministrationService {

  protected final static Log logger = LogFactory.getLog(EeutilAdministrationService.class);

  @Autowired
  private EeutilDao eeutilDao;

  @Override
  public AplicacionOperacionObject incrementarPeticionAccion(String idAplicacion,
      String operacion) {
    EeutilAplicacionOperacion databaseObject = new EeutilAplicacionOperacion();
    databaseObject.setIdAplicacion(idAplicacion);
    databaseObject.setNumeroPeticiones(1);
    databaseObject.setOperacion(operacion);
    databaseObject.setCapturar(false);
    eeutilDao.salvar(databaseObject);

    StringBuilder tmpBuild = new StringBuilder("call regenerar_aplicacion_operacion(");
    tmpBuild.append("'");
    tmpBuild.append(idAplicacion);
    tmpBuild.append("','");
    tmpBuild.append(operacion);
    tmpBuild.append("')");
    eeutilDao.executeStoreProcedure(tmpBuild.toString());

    // buscamos el elemento para obtener su configuracion
    List<Criterion> criterios = new ArrayList<Criterion>();
    criterios.add(Restrictions.eq("idAplicacion", idAplicacion));
    criterios.add(Restrictions.eq("operacion", operacion));
    List<Order> orden = new ArrayList<Order>();
    orden.add(Order.desc("numeroPeticiones"));
    List<EeutilAplicacionOperacion> eeutilAppList =
        eeutilDao.findObjetosWithOrder(EeutilAplicacionOperacion.class, criterios, orden);
    if (CollectionUtils.isNotEmpty(eeutilAppList)) {
      EeutilAplicacionOperacion aux = eeutilAppList.get(0);
      databaseObject.setNumeroPeticiones(aux.getNumeroPeticiones());
      databaseObject.setCapturar(aux.isCapturar());
    }

    return new AplicacionOperacionObject(databaseObject.getIdAplicacion(),
        databaseObject.getOperacion(), databaseObject.getNumeroPeticiones(),
        databaseObject.isCapturar());
  }

  private List<Criterion> criteriasPkPeticionAplicacion(String idAplicacion, String operacion) {
    List<Criterion> retorno = new ArrayList<Criterion>();
    retorno.add(Restrictions.eq("idAplicacion", idAplicacion));
    retorno.add(Restrictions.eq("operacion", operacion));
    return retorno;
  }

  @Override
  public void modificarAplicacionOperacion(AplicacionOperacionObject aplicacionOperacion) {
    EeutilAplicacionOperacion databaseObject =
        (EeutilAplicacionOperacion) eeutilDao.findObjeto(EeutilAplicacionOperacion.class,
            criteriasPkPeticionAplicacion(aplicacionOperacion.getAplicacion(),
                aplicacionOperacion.getOperacion()));
    databaseObject.setCapturar(aplicacionOperacion.isCapturar());
    eeutilDao.update(databaseObject);
  }

  @Override
  public void capturarPeticion(PeticionObject peticion) {
    EeutilPeticion peticionDatabase = new EeutilPeticion();
    peticionDatabase.setAplicacion(peticion.getAplicacion());
    peticionDatabase.setOperacion(peticion.getOperacion());
    peticionDatabase.setFecha(peticion.getFecha());
    peticionDatabase.setPeticion(peticion.getPeticion());
    eeutilDao.salvar(peticionDatabase);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void eliminarTodasPeticiones() {
    List<EeutilPeticion> peticiones = eeutilDao.getAllObjetos(EeutilPeticion.class);
    if (CollectionUtils.isNotEmpty(peticiones)) {
      for (EeutilPeticion peticion : peticiones) {
        eeutilDao.remove(peticion);
      }
    }
  }

  @Override
  public void eliminarPeticion(PeticionObject peticion) {
    List<EeutilPeticion> databaseObjects =
        (List<EeutilPeticion>) eeutilDao.findObjetos(EeutilPeticion.class,
            criteriasPkPeticion(peticion.getAplicacion(), peticion.getOperacion()));
    if (CollectionUtils.isNotEmpty(databaseObjects)) {
      for (EeutilPeticion databaseObject : databaseObjects) {
        eeutilDao.remove(databaseObject);
      }
    }
  }

  private List<Criterion> criteriasPkPeticion(String idAplicacion, String operacion) {
    List<Criterion> retorno = new ArrayList<Criterion>();
    retorno.add(Restrictions.eq("aplicacion", idAplicacion));
    retorno.add(Restrictions.eq("operacion", operacion));
    return retorno;
  }

  @Override
  public List<PeticionObject> consultarPeticiones(PeticionObject peticion) {
    List<PeticionObject> retorno = new ArrayList<PeticionObject>();
    List<Order> orden = new ArrayList<Order>();
    orden.add(Order.desc("fecha"));
    List<EeutilPeticion> databaseObjects =
        (List<EeutilPeticion>) eeutilDao.findObjetosWithOrder(EeutilPeticion.class,
            criteriasPkPeticion(peticion.getAplicacion(), peticion.getOperacion()), orden);
    if (CollectionUtils.isNotEmpty(databaseObjects)) {
      retorno.addAll(ConvertDataEeutil.converDataPeticion(databaseObjects));
    }
    return retorno;
  }

  @Override
  public byte[] descargarPeticion(PeticionObject peticion) {
    List<Criterion> criterios =
        criteriasPkPeticion(peticion.getAplicacion(), peticion.getOperacion());
    criterios.add(Restrictions.eq("fecha", peticion.getFecha()));

    EeutilPeticion databaseObject =
        (EeutilPeticion) eeutilDao.findObjeto(EeutilPeticion.class, criterios);

    return databaseObject.getPeticion();
  }
}
