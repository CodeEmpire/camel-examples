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

import org.apache.camel.BindToRegistry;
import org.apache.camel.PropertyInject;
import org.apache.camel.builder.LambdaRouteBuilder;
import org.apache.camel.CamelConfiguration;

/**
 * Class to configure the Camel application.
 */
public class MyConfiguration implements CamelConfiguration {

    @BindToRegistry
    public MyBean myBean(@PropertyInject("hi") String hi, @PropertyInject("bye") String bye) {
        // this will create an instance of this bean with the name of the method (eg myBean)
        return new MyBean(hi, bye);
    }

    /**
     * Here we define a route as a method that returns a LambdaRouteBuilder instance.
     * This allows us to use lambda style.
     */
    @BindToRegistry
    public LambdaRouteBuilder myRoute() {
        return rb -> rb.from("quartz:foo?cron={{myCron}}")
                .bean("myBean", "hello")
                .log("${body}")
                .bean("myBean", "bye")
                .log("${body}");
    }

}
