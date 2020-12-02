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

package es.mpt.dsic.inside.model.pagination;

import java.io.Serializable;
import java.util.List;

public class ResultadoBusqueda<T> implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private List<T> data;
  private int sEcho;
  private Integer iTotalRecords;
  private Integer iTotalDisplayRecords;

  public ResultadoBusqueda() {
    super();
  }

  public ResultadoBusqueda(List<T> data, int sEcho, Integer iTotalRecords,
      Integer iTotalDisplayRecords) {
    super();
    this.data = data;
    this.sEcho = sEcho;
    this.iTotalRecords = iTotalRecords;
    this.iTotalDisplayRecords = iTotalDisplayRecords;
  }

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }

  public int getsEcho() {
    return sEcho;
  }

  public void setsEcho(int sEcho) {
    this.sEcho = sEcho;
  }

  public Integer getiTotalRecords() {
    return iTotalRecords;
  }

  public void setiTotalRecords(Integer iTotalRecords) {
    this.iTotalRecords = iTotalRecords;
  }

  public Integer getiTotalDisplayRecords() {
    return iTotalDisplayRecords;
  }

  public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
    this.iTotalDisplayRecords = iTotalDisplayRecords;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
