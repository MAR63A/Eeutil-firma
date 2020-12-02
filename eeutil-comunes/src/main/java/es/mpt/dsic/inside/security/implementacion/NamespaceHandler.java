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

import org.apache.cxf.configuration.spring.SimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import es.mpt.dsic.inside.security.interceptor.BasicAuthInterceptor;
import es.mpt.dsic.inside.security.interceptor.GenericUsernamePasswordInterceptor;
import es.mpt.dsic.inside.security.interceptor.SoapAuthenticationInterceptor;

public class NamespaceHandler extends NamespaceHandlerSupport {
  public void init() {
    registerBeanDefinitionParser("basic-auth-interceptor",
        new AuthenticationManagerAwareBeanDefinitionParser(BasicAuthInterceptor.class));
    registerBeanDefinitionParser("server-password-callback-handler",
        new AuthenticationManagerAwareBeanDefinitionParser(ServerPasswordCallbackHandler.class));
    registerBeanDefinitionParser("spring-security-context-feature",
        new SimpleBeanDefinitionParser(SpringSecurityContextFeature.class));
    registerBeanDefinitionParser("generic-username-password-interceptor",
        new SimpleBeanDefinitionParser(GenericUsernamePasswordInterceptor.class));
    registerBeanDefinitionParser("soap-authentication-interceptor",
        new AuthenticationManagerAwareBeanDefinitionParser(SoapAuthenticationInterceptor.class));
    registerBeanDefinitionParser("cxf-security-context-provider-interceptor",
        new SimpleBeanDefinitionParser(CXFSecurityContextProviderInterceptor.class));
    registerBeanDefinitionParser("spring-security-context-consumer-interceptor",
        new SimpleBeanDefinitionParser(SpringSecurityContextConsumerInterceptor.class));
  }
}
