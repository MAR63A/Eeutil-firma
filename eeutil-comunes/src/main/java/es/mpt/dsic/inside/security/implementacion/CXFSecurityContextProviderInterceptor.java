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

import java.security.Principal;
import java.util.Collection;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.security.SecurityContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class CXFSecurityContextProviderInterceptor extends AbstractPhaseInterceptor<Message> {
  public CXFSecurityContextProviderInterceptor() {
    // TODO: this is almost certainly not the right phase;
    // in particular this will probably not work with WS-Security
    super(Phase.RECEIVE);
  }

  public void handleMessage(Message message) throws Fault {
    final Authentication authentication = message.getExchange().get(Authentication.class);
    if ((authentication != null) && authentication.isAuthenticated()) {
      message.put(SecurityContext.class, new SecurityContext() {
        public Principal getUserPrincipal() {
          return authentication;
        }

        @SuppressWarnings("unchecked")
        public boolean isUserInRole(String role) {
          Collection<GrantedAuthority> authorities =
              (Collection<GrantedAuthority>) authentication.getAuthorities();
          if (authorities != null) {
            for (GrantedAuthority authority : authorities) {
              if (role.equals(authority.getAuthority())) {
                return true;
              }
            }
          }
          return false;
        }
      });
    }
  }
}
