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

package es.mpt.dsic.inside.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "inside_aplicaciones_propiedad")
public class EeutilAplicacionPropiedad implements Serializable {
  @EmbeddedId
  private EeutilAplicacionPropiedadId id;
  private String valor;


  public EeutilAplicacionPropiedad() {
    super();
    // TODO Auto-generated constructor stub
  }

  public EeutilAplicacionPropiedad(EeutilAplicacionPropiedadId id, String valor) {
    super();
    this.id = id;
    this.valor = valor;
  }

  public EeutilAplicacionPropiedadId getId() {
    return id;
  }

  public void setId(EeutilAplicacionPropiedadId id) {
    this.id = id;
  }

  public String getValor() {
    return valor;
  }

  public void setValor(String valor) {
    this.valor = valor;
  }

  @Override
  public String toString() {
    return "EeutilAplicacionPropiedad [id=" + id + ", valor=" + valor + "]";
  }

}
