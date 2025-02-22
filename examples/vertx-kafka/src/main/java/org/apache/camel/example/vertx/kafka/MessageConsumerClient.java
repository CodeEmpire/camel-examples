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
package org.apache.camel.example.vertx.kafka;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.component.ComponentsBuilderFactory;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.camel.example.vertx.kafka.MessagePublisherClient.setUpKafkaComponent;

public final class MessageConsumerClient {

    private static final Logger LOG = LoggerFactory.getLogger(MessageConsumerClient.class);

    private MessageConsumerClient() {
    }

    public static void main(String[] args) throws Exception {

        LOG.info("About to run Camel Vertx Kafka integration...");

        try (CamelContext camelContext = new DefaultCamelContext()) {
            // Set the location of the configuration
            camelContext.getPropertiesComponent().setLocation("classpath:application.properties");
            // Set up the Kafka component
            setUpKafkaComponent(camelContext);
            // Add route to send messages to Kafka

            camelContext.addRoutes(createRouteBuilder());
            camelContext.start();
            // let it run for 5 minutes before shutting down
            Thread.sleep(5L * 60 * 1_000);
        }
    }

    static RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
                from("vertx-kafka:{{consumer.topic}}"
                        + "?maxPollRecords={{consumer.maxPollRecords}}"
                        + "&seekToPosition={{consumer.seekTo}}"
                        + "&groupId={{consumer.group}}")
                        .routeId("FromKafka")
                        .log("${body}");
            }
        };
    }
}
