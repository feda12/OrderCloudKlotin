//
//  Serializable_tests.kt
//  OrderCloudSDK
//
//  Created by Ben Le Cam on 1/14/16.
//  Copyright © 2016 Ben Le Cam. All rights reserved.
//


package com.four51.ordercloud.tests

import com.beust.klaxon.*
import com.four51.ordercloud.*
import org.jetbrains.spek.api.*

class SerializableSpec: Spek()
{
    init {
        given("Errors in JSON response") {
            on("Errors present")
            {
                it("throws an API error")
                {
                    val obj: Serializable = User()

                    val errorsJson = JsonObject(mapOf(
                            "Errors" to mapOf(
                                    "ErrorCode" to "[23032/32849243*3923232aad3220mod883948]",
                                    "Message" to "No idea."
                            )
                    ))

                    val error = ApiError(errorCode = "[23032/32849243*3923232aad3220mod883948]", message = "No idea.")
//                    shouldThrow(error, obj.deserialize(errorsJson))
                }
            }
        }
    }
}
