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

package es.mpt.dsic.inside.util.bbdd;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;


public class StringBooleanUserType implements UserType {

  @Override
  public int[] sqlTypes() {

    return new int[] {Types.CHAR};

  }

  @Override
  public Class returnedClass() {

    return Boolean.class;

  }

  @Override
  public boolean equals(Object x, Object y) throws HibernateException {

    return (x == y) || ((x != null) && (y != null) && (x.equals(y)));

  }

  @Override
  public Object nullSafeGet(ResultSet inResultSet, String[] names, Object o)

      throws HibernateException, SQLException {

    String val = (String) Hibernate.STRING.nullSafeGet(inResultSet, names[0]);

    return BDUtils.getBooleanValue(val);

  }

  @Override
  public void nullSafeSet(PreparedStatement inPreparedStatement, Object o, int i)

      throws HibernateException, SQLException {

    boolean boolValue = (o != null) && (o instanceof Boolean) && (Boolean) o;
    String val = BDUtils.getCharValue(boolValue);

    inPreparedStatement.setString(i, val);

  }

  @Override
  public Object deepCopy(Object o) throws HibernateException {

    if (o == null) {
      return null;
    }

    return new Boolean(((Boolean) o).booleanValue());

  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public Serializable disassemble(Object value) throws HibernateException {
    return (Serializable) value;
  }

  @Override
  public Object assemble(Serializable cached, Object owner) throws HibernateException {
    return cached;
  }

  @Override
  public Object replace(Object original, Object target, Object owner) throws HibernateException {
    return original;
  }

  @Override
  public int hashCode(Object x) throws HibernateException {
    return x.hashCode();
  }
}
