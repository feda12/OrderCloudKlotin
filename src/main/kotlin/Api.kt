//
//  Api.kt
//  OrderCloudSDK
//
//  Created by Ben Le Cam on 1/11/16.
//  Copyright © 2016 Ben Le Cam. All rights reserved.
//

package com.four51.ordercloud

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result


/*
All calls in the Api struct are versioned
aka, the current version path is added in
front of every endpoint url
*/
public class Api {
    companion object {
        public var version = "v1"

        public fun get(endpoint: String, parameters: Map<String, Any?> = emptyMap(), completionHandler: (Request, Response, Result<String, FuelError>) -> Unit) {
            Http.authenticatedRequest(Http.Method.GET, url = Util.apiUrl(endpoint), parameters = parameters, encoding = Http.ParameterEncoding.URL, completionHandler = completionHandler)
        }

        public fun post(endpoint: String, parameters: Map<String, Any?> = emptyMap(), completionHandler: (Request, Response, Result<String, FuelError>) -> Unit) {
            Http.authenticatedRequest(Http.Method.POST, url = Util.apiUrl(endpoint), parameters = parameters, encoding = Http.ParameterEncoding.JSON, completionHandler = completionHandler)
        }

        public fun delete(endpoint: String, parameters: Map<String, Any?> = emptyMap(), completionHandler: (Request, Response, Result<String, FuelError>) -> Unit) {
            Http.authenticatedRequest(Http.Method.DELETE, url = Util.apiUrl(endpoint), parameters = parameters, encoding = Http.ParameterEncoding.URL, completionHandler = completionHandler)
        }

        public fun patch(endpoint: String, parameters: Map<String, Any?> = emptyMap(), completionHandler: (Request, Response, Result<String, FuelError>) -> Unit) {
            Http.authenticatedRequest(Http.Method.PATCH, url = Util.apiUrl(endpoint), parameters = parameters, encoding = Http.ParameterEncoding.JSON, completionHandler = completionHandler)
        }

        public fun put(endpoint: String, parameters: Map<String, Any?> = emptyMap(), completionHandler: (Request, Response, Result<String, FuelError>) -> Unit) {
            Http.authenticatedRequest(Http.Method.PUT, url = Util.apiUrl(endpoint), parameters = parameters, encoding = Http.ParameterEncoding.JSON, completionHandler = completionHandler)
        }
    }
}
