//
//  Util.kt
//  OrderCloudSDK
//
//  Created by Ben Le Cam on 1/11/16.
//  Copyright © 2016 Ben Le Cam. All rights reserved.
//

package com.four51.ordercloud

import com.beust.klaxon.*
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import sun.text.normalizer.UTF16
import java.io.ByteArrayInputStream
import java.io.InputStream

public class Util {
    companion object {
        /*
        Sanitize an url
        Example: v1//endpoint becomes v1/endpoint
        */
        public fun sanitize(url: String): String {
            var sanitizedUrl: String = String()
            var urlPrefix: String = String()

            if (url.take(5) == "https") {
                urlPrefix = "https://"
            } else if (url.take(4) == "http") {
                urlPrefix = "http://"
            }

            val urlWithoutPrefix = url.drop(urlPrefix.length())

            for (c in urlWithoutPrefix) {

                if (c != '/' || sanitizedUrl.count() == 0 || (sanitizedUrl.last() != '/' && c == '/')) {
                    sanitizedUrl += c
                }
            }
            return urlPrefix + sanitizedUrl
        }

        /*
        Return an url starting with the given API version endpoint
        */
        public fun versionEndpoint(endpoint: String): String {
            return sanitize("/${Api.version}/${endpoint}")
        }

        /*
        Return a full endpoint path to the given endpoint
        */
        public fun apiPath(endpoint: String): String {
            return sanitize(versionEndpoint(endpoint))
        }

        /*
        Return a full url to the authentication endpoint
        */
        public fun authUrl(): String {
            val scheme = "https://"
            val path = "/oauth/token"
            return sanitize(scheme + Urls.auth + path)
        }

        /*
        Return a full url to the api endpoint
        */
        public fun apiUrl(endpoint: String): String {
            val scheme = "https://"
            return sanitize(scheme + Urls.api + Util.apiPath(endpoint))
        }

        /*
        Creates the user agent corresponding to this platform
        */
        public fun userAgent(): String {
            val sdkVersion = "0.1.0"
            val sdk = "OrderCloudKotlin"
            val platform = "Java/Kotlin"

            return "${sdk}/${sdkVersion} (${platform})"
        }

        public fun strToJsonObject(str: String) : JsonObject
        {
            val stream: InputStream = ByteArrayInputStream(str.toByteArray())
            return Parser().parse(stream) as JsonObject!!
        }

        /*
            Returns a default handler for the authentication
        */
        public fun defaultAuthHandler(): (Request, Response, Result<String, FuelError>) -> Unit {
            return { request, response, result ->
                val (data, error) = result
                print("This is the default auth handler")
                if (error != null) {

                    val authJson: JsonObject = JsonObject()
                    print(authJson)
                    val authToken: String = authJson.string("access_token")!!
                    if (authToken.count() > 0) {
                        User.currentUser.setupWithToken(authToken)
                    } else {
                        val errors: JsonArray<JsonObject> = authJson.array<JsonObject>("Errors")!!
                        if (errors.count() > 0) {
                            for (error in errors) {
                                print(error)
                            }
                        } else {
                            print("Unknown authentication error")
                        }
                    }
                } else {
                    print(error)
                }
            }
        }
    }
}
