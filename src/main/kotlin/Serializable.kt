//
//  Serializable.kt
//  OrderCloudSDK
//
//  Created by Ben Le Cam on 1/13/16.
//  Copyright © 2016 Ben Le Cam. All rights reserved.
//


package com.four51.ordercloud

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.array
import com.beust.klaxon.string
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result

import com.four51.ordercloud.ApiError

/*
    Define an object that is serializable
*/
abstract public class Serializable {
    // Childs should re-define these funtions and call super if (desired) {
    open fun deserialize(json: JsonObject) {
        try {
            val errors: JsonArray<JsonObject> = json.array<JsonObject>("Errors")!!
            println(errors)
            for (error in errors) {
                println(error)
                throw ApiError(errorCode = error.string("ErrorCode")!!, message = error.string("Message")!!)
            }
        } catch (e: Exception) {

        }
    }

    abstract public fun serialize(): Map<String, Any?>

    companion object {
        /*
            Default completionHandler for a serializable object, mostly used for testing purposes
        */
        public fun completionHandler(obj: Serializable): (Request, Response, Result<String, FuelError>) -> Unit {
            return { request, response, result ->
                val (data, error) = result
                if (error != null) {
                    obj.deserialize(Util.strToJsonObject(data!!))
                } else {
                    println(error)
                }
            }
        }
    }
}
