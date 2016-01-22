//
//  Http.kt
//  OrderCloudKotlin
//
//  Created by Ben Le Cam on 1/13/16.
//  Copyright © 2016 Ben Le Cam. All rights reserved.
//

package com.four51.ordercloud

import com.beust.klaxon.JsonObject
import com.github.kittinunf.fuel.*
import com.github.kittinunf.fuel.core.*
import com.github.kittinunf.result.Result

public class Http {
    public enum class Method {
        OPTIONS,
        GET,
        HEAD,
        POST,
        PUT,
        PATCH,
        DELETE,
        TRACE,
        CONNECT
    }

    public enum class ParameterEncoding {
        URL,
        JSON
    }

    companion object {

        public fun request(method: Http.Method,
                           url: String,
                           parameters: Map<String, Any?> = emptyMap(),
                           encoding: Http.ParameterEncoding = Http.ParameterEncoding.URL,
                           headers: Map<String, String> = emptyMap<String, String>(),
                           completionHandler: (Request, Response, Result<String, FuelError>) -> Unit) {

            Manager.instance.baseHeaders = headers

            when (method) {
//                Http.Method.OPTIONS -> Fuel.options(url, parameters.toList()).responseString(handler = completionHandler)
                Http.Method.GET -> Fuel.get(url, parameters.toList()).responseString(handler = completionHandler)
//                Http.Method.HEAD -> Fuel.head(url, parameters.toList()).responseString(handler = completionHandler)
                Http.Method.POST -> Fuel.post(url, parameters.toList()).responseString(handler = completionHandler)
                Http.Method.PUT -> Fuel.put(url, parameters.toList()).responseString(handler = completionHandler)
                Http.Method.DELETE -> Fuel.delete(url, parameters.toList()).responseString(handler = completionHandler)
//                Http.Method.TRACE -> Fuel.trace(url, parameters.toList()).responseString(handler = completionHandler)
                Http.Method.PATCH -> Fuel.put(url, parameters.toList()).responseString(handler = completionHandler)
            }
        }

        public fun authenticatedRequest(method: Http.Method,
                                        url: String,
                                        parameters: Map<String, Any?> = emptyMap(),
                                        encoding: Http.ParameterEncoding = Http.ParameterEncoding.URL,
                                        completionHandler: (Request, Response, Result<String, FuelError>) -> Unit) {
            val headers = mapOf(
                    "Authorization" to "Bearer ${User.currentUser.getAccessToken()}",
                    "Content-Type" to "application/json; charset=utf-8",
                    "User-Agent" to Util.userAgent()
            )

            Http.request(method = method, url = url, parameters = parameters, encoding = encoding, headers = headers, completionHandler = completionHandler)
        }
    }
}
