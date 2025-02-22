/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.example;

import org.apache.camel.CamelConfiguration;
import org.apache.camel.CamelContext;

/**
 * Class to configure the Camel application.
 */
public class MyConfiguration implements CamelConfiguration {

    /**
     * Creates the Artemis JMS ConnectionFactory and bind it to the Camel registry
     * so we can do auto-wiring on the Camel JMS component.
     * See more details in the application.properties file.
     */
//    @BindToRegistry
//    public ConnectionFactory myArtemisClient(@PropertyInject("artemisBroker") String brokerUrl) {
//        ActiveMQConnectionFactory cf = new ActiveMQJMSConnectionFactory(brokerUrl);
//        cf.setUser("admin");
//        cf.setPassword("admin");
//        return cf;
//    }

    @Override
    public void configure(CamelContext camelContext) {
        // this method is optional and can be removed if no additional configuration is needed.
    }

}
