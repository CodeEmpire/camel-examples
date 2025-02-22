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
package org.apache.camel.example

import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.apache.camel.RoutesBuilder
import org.apache.camel.test.junit5.CamelTestSupport
import org.apache.http.HttpStatus
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

/**
 * A unit test checking that Camel supports Kotlin.
 */
class KotlinTest: CamelTestSupport() {

    @Test
    fun `should support kotlin`() {
        Given {
            baseUri("http://localhost:8080")
        } When {
            get("/")
        } Then {
            statusCode(HttpStatus.SC_OK)
            body(equalTo("Hello from Kotlin"))
        }
    }

    override fun createRouteBuilder(): RoutesBuilder {
        return MyRouteBuilder()
    }
}
