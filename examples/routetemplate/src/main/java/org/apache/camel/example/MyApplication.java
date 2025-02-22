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

import org.apache.camel.main.Main;

/**
 * Main class that boot the Camel application
 */
public final class MyApplication {

    private MyApplication() {
    }

    public static void main(String[] args) throws Exception {
        // use Camels Main class
        Main main = new Main();
        // and add route templates via routes builder
        main.configure().addRoutesBuilder(MyRouteTemplates.class);
        // add configuration class which set up the routes from the route templates

        // to configure route templates we can use java code as below from a template builder class,
        // gives more power as its java code.
        // or we can configure as well from application.properties,
        // less power as its key value pair properties
        // and you can also use both java and properties together

        // in this example we use properties by default and have disabled java
        // main.bind("myTemplateBuilder", new MyTemplateBuilder());

        // now keep the application running until the JVM is terminated (ctrl + c or sigterm)
        main.run(args);
    }

}
