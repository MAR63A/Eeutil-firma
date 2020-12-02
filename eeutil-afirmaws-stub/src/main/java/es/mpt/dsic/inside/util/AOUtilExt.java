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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import es.gob.afirma.core.misc.AOUtil;
import es.gob.afirma.core.signers.AOSimpleSignInfo;
import es.gob.afirma.core.util.tree.AOTreeModel;
import es.gob.afirma.core.util.tree.AOTreeNode;

/*
 * import es.gob.afirma.misc.AOUtil; import es.gob.afirma.misc.tree.TreeNode; import
 * es.gob.afirma.signers.beans.AOSimpleSignInfo; import
 * es.mpr.sgtic.ext.arrobafirma.beans.InformacionFirmante;
 */
public class AOUtilExt {


  /**
   * MPT devuelve una lista de InformacionFirmante, que contiene el nombre, la fecha y el tipo de
   * firma de cada una de las firmas.
   * 
   * @param tree elemento Root del árbol de firmas.
   * @return Una lista, ordenada por fecha de firma, de los firmantes.
   */
  public final static List<InformacionFirmante> getTreeAsInformacionFirmantes(
      final AOTreeModel tree) {
    if (tree == null || tree.getRoot() == null) {
      Logger.getLogger("es.gob.afirma").severe("Se ha proporcionado un arbol de firmas vacio"); //$NON-NLS-1$
      return null;
    }

    if (!(tree.getRoot() instanceof AOTreeNode)) {
      Logger.getLogger("es.gob.afirma") //$NON-NLS-1$
          .severe("La raiz del arbol de firmas no es de tipo TreeNode");
      return null;
    }

    List<InformacionFirmante> firmantes = new ArrayList<InformacionFirmante>();
    AOTreeNode root = (AOTreeNode) tree.getRoot();

    for (int i = 0; i < root.getChildCount(); i++) {
      archiveTreeNode(root.getChildAt(i), 0, firmantes);
    }


    return firmantes;
  }

  /**
   * MPT Transforma en una lista de InformacionFirmante una rama completa de un &aacute;rbol. Para
   * saber si un nodo es firma o contrafirma es necesario indicar la profundidad en la que está el
   * nodo del que pende la rama. En el caso de las ramas que penden del nodo ra&iacute;z del
   * &aacute;rbol la profundidad es cero (0).
   * 
   * @param node Nodo del que cuelga la rama.
   * @param depth Profundidad del nodo del que pende la rama.
   * @param firmantes Lista donde se van guardando los firmantes.
   */
  private final static void archiveTreeNode(final AOTreeNode node, final int depth,
      final List<InformacionFirmante> firmantes) {
    InformacionFirmante firmante = new InformacionFirmante();

    // Si la lista de firmantes está vacía, es que estamos procesando la primera de las firmas
    // que será siempre de tipo FIRMA.
    // if (firmantes.size() == 0) {
    // firmante.setTipo(InformacionFirmante.TipoFirma.F);

    // Si la lista de firmantes no estaba vacía (no es primera firma), y la profundidad es 0, es que
    // se trata de una COFIRMA de la primera firma
    // } else

    // Si la profundidad es 0, se trata de una COFIRMA de la primera firma.
    if (depth == 0) {
      firmante.setTipo(InformacionFirmante.TipoFirma.CF);

      // Si la profundidad no es 0, se tratará de una CONTRAFIRMA de la primera firma.
    } else {
      firmante.setTipo(InformacionFirmante.TipoFirma.XF);
    }


    if (node.getUserObject() instanceof AOSimpleSignInfo) {
      AOSimpleSignInfo info = (AOSimpleSignInfo) node.getUserObject();
      firmante.setNombre(AOUtil.getCN(info.getCerts()[0]));
      firmante.setFecha(getSignTime(info));
      firmante.setCertificado(info.getCerts()[0]);
    } else {
      firmante.setNombre(node.getUserObject().toString());
    }

    firmantes.add(firmante);

    for (int i = 0; i < node.getChildCount(); i++) {
      archiveTreeNode(node.getChildAt(i), depth + 1, firmantes);
    }

  }



  /**
   * Transforma en cadena una rama completa de un &aacute;rbol. Para una correcta indexaci&oacute;
   * es necesario indicar la profundidad en la que esta el nodo del que pende la rama. En el caso de
   * las ramas que penden del nodo ra&iacute;z del &aacute;rbol la profundidad es cero (0).
   * 
   * @param node Nodo del que cuelga la rama.
   * @param depth Profundidad del nodo del que pende la rama.
   * @param linePrefx Prefijo de cada l&iacute;nea de firma (por defecto, cadena vac&iacute;a).
   * @param identationString Cadena para la identaci&oacute;n de los nodos de firma (por defecto,
   *        tabulador).
   * @param buffer Buffer en donde se genera la cadena de texto.
   */
  private final static void archiveTreeNode(final AOTreeNode node, final int depth,
      final String linePrefx, final String identationString, final StringBuilder buffer) {
    buffer.append('\n').append(linePrefx);
    for (int i = 0; i < depth; i++)
      buffer.append(identationString);
    buffer.append((node).getUserObject());
    for (int i = 0; i < node.getChildCount(); i++) {
      archiveTreeNode(node.getChildAt(i), depth + 1, linePrefx, identationString, buffer);
    }
  }

  private static String getSignTime(AOSimpleSignInfo value) {
    Date date = value.isTimeStamped() ? value.getTimestampingTime()[0] : value.getSigningTime();
    return date == null ? "" : getFormatedTime(date);
  }

  private static String getFormatedTime(Date date) {
    return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date);
  }
}
