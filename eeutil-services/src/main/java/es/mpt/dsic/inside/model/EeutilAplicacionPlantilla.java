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
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "inside_aplicaciones_plantillas")
public class EeutilAplicacionPlantilla implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;


  @EmbeddedId
  private EeutilAplicacionPlantillaId id;

  @Column(name = "plantilla", nullable = false)
  @Lob
  private byte[] plantilla;


  public EeutilAplicacionPlantilla() {
    super();
    // TODO Auto-generated constructor stub
  }

  public EeutilAplicacionPlantilla(EeutilAplicacionPlantillaId id, byte[] plantilla) {
    super();
    this.id = id;
    this.plantilla = plantilla;
  }

  public EeutilAplicacionPlantillaId getId() {
    return id;
  }

  public void setId(EeutilAplicacionPlantillaId id) {
    this.id = id;
  }


  public byte[] getPlantilla() {
    return plantilla;
  }

  public void setPlantilla(byte[] plantilla) {
    this.plantilla = plantilla;
  }

  @Override
  public String toString() {
    return "EeutilAplicacionPlantilla [id=" + id + "]";
  }

}
