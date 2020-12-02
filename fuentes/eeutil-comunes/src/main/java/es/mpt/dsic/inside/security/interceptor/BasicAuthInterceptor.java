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

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package es.mpt.dsic.inside.security.interceptor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.Conduit;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

// Inspired by:
// - http://chrisdail.com/2008/03/31/apache-cxf-with-http-basic-authentication/
// - http://markmail.org/message/ej7mm5ynvcnukcbk
public class BasicAuthInterceptor extends AbstractPhaseInterceptor<Message> {
  private AuthenticationManager authenticationManager;

  public BasicAuthInterceptor() {
    super(Phase.RECEIVE);
  }

  // TODO: use an afterPropertiesSet to check that an authentication manager is configured
  public void setAuthenticationManager(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  public void handleMessage(Message inMessage) {
    AuthorizationPolicy policy = inMessage.get(AuthorizationPolicy.class);
    boolean sendUnauthorized = true;
    if (policy != null) {
      Authentication authentication =
          new UsernamePasswordAuthenticationToken(policy.getUserName(), policy.getPassword());
      try {
        authentication = authenticationManager.authenticate(authentication);
        inMessage.getExchange().put(Authentication.class, authentication);
        sendUnauthorized = false;
      } catch (AuthenticationException ex) {
        // TODO: log exception?
      }
    }
    if (sendUnauthorized) {
      Message outMessage = getOutMessage(inMessage);
      outMessage.put(Message.RESPONSE_CODE, HttpURLConnection.HTTP_UNAUTHORIZED);
      setHeader(outMessage, "WWW-Authenticate", "Basic realm=foo"); // TODO: configure realm
      inMessage.getInterceptorChain().abort();
      try {
        getConduit(inMessage).prepare(outMessage);
        close(outMessage);
      } catch (IOException ioe) {
        // REVISIT log etc...
        ioe.printStackTrace();
      }
    }
  }

  private Message getOutMessage(Message inMessage) {
    Exchange exchange = inMessage.getExchange();
    Message outMessage = exchange.getOutMessage();
    if (outMessage == null) {
      Endpoint endpoint = exchange.get(Endpoint.class);
      outMessage = endpoint.getBinding().createMessage();
      exchange.setOutMessage(outMessage);
    }
    outMessage.putAll(inMessage);
    return outMessage;
  }

  @SuppressWarnings("unchecked")
  private void setHeader(Message message, String name, String value) {
    Map<String, List<String>> responseHeaders =
        (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);
    if (responseHeaders != null) {
      responseHeaders.put(name, Arrays.asList(new String[] {value}));
    }
  }

  private Conduit getConduit(Message inMessage) throws IOException {
    Exchange exchange = inMessage.getExchange();
    EndpointReferenceType target = exchange.get(EndpointReferenceType.class);
    Conduit conduit = exchange.getDestination().getBackChannel(inMessage, null, target);
    exchange.setConduit(conduit);
    return conduit;
  }

  private void close(Message outMessage) throws IOException {
    OutputStream os = outMessage.getContent(OutputStream.class);
    os.flush();
    os.close();
  }
}
