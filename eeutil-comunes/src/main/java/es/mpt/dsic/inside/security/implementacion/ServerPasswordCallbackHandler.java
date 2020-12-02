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

package es.mpt.dsic.inside.security.implementacion;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.ws.security.WSPasswordCallback;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class ServerPasswordCallbackHandler implements CallbackHandler, InitializingBean {
  private static final Logger LOG = LogUtils.getL7dLogger(ServerPasswordCallbackHandler.class);

  @Qualifier("authenticationManager")
  private AuthenticationManager authenticationManager;

  private boolean nestExceptions;
  private boolean logExceptions;

  public void setAuthenticationManager(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  public void setNestExceptions(boolean nestExceptions) {
    this.nestExceptions = nestExceptions;
  }

  public void setLogExceptions(boolean logExceptions) {
    this.logExceptions = logExceptions;
  }

  public void afterPropertiesSet() throws Exception {
    if (authenticationManager == null) {
      throw new IllegalStateException("No authentication manager has been configured");
    }
  }

  public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
    for (Callback callback : callbacks) {
      if (callback instanceof WSPasswordCallback) {
        WSPasswordCallback pwCallback = (WSPasswordCallback) callback;
        // TODO: need to check getUsage
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            pwCallback.getIdentifier(), pwCallback.getPassword());
        try {
          authentication = authenticationManager.authenticate(authentication);
        } catch (AuthenticationException ex) {
          throw translateException(ex);
        }
        Message message = PhaseInterceptorChain.getCurrentMessage();
        if (message == null) {
          LOG.fine("No current message; can't add the Authentication object to the Exchange.");
        } else {
          message.getExchange().put(Authentication.class, authentication);
        }
      } else {
        throw new UnsupportedCallbackException(callback);
      }
    }
  }

  private SecurityException translateException(AuthenticationException ex) {
    if (logExceptions) {
      LogUtils.log(LOG, Level.WARNING, "Authentication failed", ex);
    }
    return nestExceptions ? new SecurityException(ex)
        : new SecurityException("Authentication failed");
  }
}
