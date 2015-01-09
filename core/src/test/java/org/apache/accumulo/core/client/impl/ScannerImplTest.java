/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.accumulo.core.client.impl;

import org.apache.accumulo.core.client.ClientConfiguration;
import org.apache.accumulo.core.client.Scanner;
import org.apache.accumulo.core.client.mock.MockInstance;
import org.apache.accumulo.core.client.security.tokens.PasswordToken;
import org.apache.accumulo.core.security.Authorizations;
import org.apache.accumulo.core.security.Credentials;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class ScannerImplTest {

  @Test
  public void testValidReadaheadValues() {
    MockInstance instance = new MockInstance();
    ClientContext context = new ClientContext(instance, new Credentials("root", new PasswordToken("")), new ClientConfiguration());
    Scanner s = new ScannerImpl(context, "foo", Authorizations.EMPTY);
    s.setReadaheadThreshold(0);
    s.setReadaheadThreshold(10);
    s.setReadaheadThreshold(Long.MAX_VALUE);

    Assert.assertEquals(Long.MAX_VALUE, s.getReadaheadThreshold());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInValidReadaheadValues() {
    MockInstance instance = new MockInstance();
    ClientContext context = new ClientContext(instance, new Credentials("root", new PasswordToken("")), new ClientConfiguration());
    Scanner s = new ScannerImpl(context, "foo", Authorizations.EMPTY);
    s.setReadaheadThreshold(-1);
  }

}