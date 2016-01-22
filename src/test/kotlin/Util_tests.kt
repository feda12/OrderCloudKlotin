//
//  Util_tests.kt
//  OrderCloudKotlin
//
//  Created by Ben Le Cam on 1/11/16.
//  Copyright © 2016 Ben Le Cam. All rights reserved.
//

package com.four51.ordercloud.tests

import com.beust.klaxon.*
import com.four51.ordercloud.*
import org.jetbrains.spek.api.*
import java.util.*

class UtilSpec : Spek() {
    init {
        given("Util class") {
            on("URL sanitization") {
                it("sanitizes an already sanitized url") {
                    shouldEqual("v1/me", Util.sanitize("v1/me"))
                }
                it("sanitizes a complex endpoint") {
                    val endpoint = "///v1//me/hello/////"
                    shouldEqual("/v1/me/hello/", Util.sanitize(endpoint))
                }

                it("sanitizes a complete url") {
                    val url = "http://mycomplexurl.com///a//b/c/d//e/"
                    shouldEqual("http://mycomplexurl.com/a/b/c/d/e/", Util.sanitize(url))
                }
            }

            on("API Versionning") {

                val expectedUrl = "/v1/a/b/c"

                it("returns a valid string for an empty endpoint")
                {
                    Api.version = "v1"
                    shouldEqual("/v1/", Util.versionEndpoint(String()))
                }

                it("versions a perfectly formatted input") {
                    Api.version = "v1"
                    val endpoint = "a/b/c"
                    shouldEqual(expectedUrl, Util.versionEndpoint(endpoint))
                }
                it("versions when version contains a backlash at the end") {
                    Api.version = "v1/"
                    val endpoint = "a/b/c"
                    shouldEqual(expectedUrl, Util.versionEndpoint(endpoint))
                }
                it("versions when endpoint contains a backslash as first character") {
                    Api.version = "v1"
                    val endpoint = "/a/b/c"
                    shouldEqual(expectedUrl, Util.versionEndpoint(endpoint))
                }
                it("versions when endpoint contains a backslash as first character") {
                    Api.version = "v1"
                    val endpoint = "/a/b/c"
                    shouldEqual(expectedUrl, Util.versionEndpoint(endpoint))
                }
            }

            on("API Path") {
                it("returns a clean url to the api given a version and endpoint") {
                    val expected = "/v1/my/endpoint"
                    Api.version = "v1"
                    val endpoint = "my/endpoint"

                    val result = Util.apiPath(endpoint)

                    shouldEqual(expected, result)
                }
            }
            on("API URL") {
                it("returns an absolute url to the given API endpoint with the proper version and url") {
                    Api.version = "v1"
                    Urls.api = "api.ordercloud.io"
                    val endpoint = "me"
                    shouldEqual("https://api.ordercloud.io/v1/me", Util.apiUrl(endpoint))
                }
            }

            on("XP serializer") {
                it("returns null if (xp is empty") {

                }
            }
        }
    }
}