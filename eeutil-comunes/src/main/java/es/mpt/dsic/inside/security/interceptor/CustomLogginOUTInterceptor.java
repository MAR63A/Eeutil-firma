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

package es.mpt.dsic.inside.security.interceptor;

import java.io.OutputStream;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.io.CacheAndWriteOutputStream;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.io.CachedOutputStreamCallback;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;


public class CustomLogginOUTInterceptor extends LoggingOutInterceptor {

  public CustomLogginOUTInterceptor() {
    super(Phase.PRE_STREAM);
  }

  @Override
  public void handleMessage(Message message) throws Fault {
    OutputStream out = message.getContent(OutputStream.class);
    final CacheAndWriteOutputStream newOut = new CacheAndWriteOutputStream(out);
    message.setContent(OutputStream.class, newOut);
    newOut.registerCallback(new LoggingCallback());
  }

  public class LoggingCallback implements CachedOutputStreamCallback {
    public void onFlush(CachedOutputStream cos) {}

    public void onClose(CachedOutputStream cos) {
      try {
        StringBuilder builder = new StringBuilder();
        cos.writeCacheTo(builder, limit);
        // String soapXml = builder.toString();

        // guardar en base datos

      } catch (Exception e) {
      }
    }
  }
}
