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
package org.apache.camel.example.websocket;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.ws.WebSocket;
import org.asynchttpclient.ws.WebSocketListener;
import org.asynchttpclient.ws.WebSocketUpgradeHandler;
import org.junit.jupiter.api.Test;

import static org.apache.camel.example.websocket.CamelTwitterWebSocketMain.createTwitterWebSocketRoute;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A unit test checking that Camel can poll a constant feed of Twitter and publish results using WebSocket.
 */
class CamelTwitterWebSocketTest extends CamelTestSupport {

    @Test
    void should_support_polling_twitter_publishing_websocket() throws Exception {
        try (AsyncHttpClient c = new DefaultAsyncHttpClient()) {
            final CountDownLatch received = new CountDownLatch(1);
            WebSocket websocket = null;
            try {
                websocket = c.prepareGet("ws://localhost:9090/camel-tweet")
                        .execute(new WebSocketUpgradeHandler.Builder().addWebSocketListener(
                                new WebSocketListener() {

                                    @Override
                                    public void onOpen(WebSocket websocket) {
                                    }

                                    @Override
                                    public void onClose(WebSocket websocket, int code, String reason) {

                                    }

                                    @Override
                                    public void onTextFrame(String payload, boolean finalFragment, int rsv) {
                                        received.countDown();
                                    }

                                    @Override
                                    public void onError(Throwable t) {
                                    }
                                }).build()).get();
                assertTrue(received.await(30, TimeUnit.SECONDS));
            } finally {
                if (websocket != null) {
                    websocket.sendCloseFrame();
                }
            }
        }
    }

    @Override
    protected RoutesBuilder createRouteBuilder() {
        return createTwitterWebSocketRoute("test", 100);
    }
}
