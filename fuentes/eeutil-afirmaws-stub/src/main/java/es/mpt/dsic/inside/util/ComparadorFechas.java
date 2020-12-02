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

package es.mpt.dsic.inside.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import es.mpt.dsic.inside.model.FirmaInfoAfirma;

public class ComparadorFechas implements Comparator<FirmaInfoAfirma> {

  @Override
  public int compare(FirmaInfoAfirma i1, FirmaInfoAfirma i2) {
    if ((i1.getFecha() == null || ("").equals(i1.getFecha()))
        && (i2.getFecha() == null || ("").equals(i2.getFecha()))) {
      return 0;
    }
    Date d1 = null;
    Date d2 = null;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    try {
      d1 = df.parse(i1.getFecha());
      d2 = df.parse(i2.getFecha());
    } catch (ParseException pe) {
      return 0;
    }

    return d1.compareTo(d2);
  }

}

