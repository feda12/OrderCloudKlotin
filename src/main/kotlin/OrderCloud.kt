//
//  OrderCloud.kt
//  OrderCloud-SDK
//
//  Created by Ben Le Cam on 1/7/16.
//  Copyright © 2016 Ben Le Cam. All rights reserved.
//

package com.four51.ordercloud

import com.beust.klaxon.JsonObject
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs

/*
We use struct as a namespace here.
It can be extended in other classes using extend ordercloud
*/
public class OrderCloud {

    companion object {
        // Set private to enforce the use of the setup funtion
        private var _clientId: String = String()
            private get
            private set

        /*
            Wrapper around the client id setup
        */
        public fun setupClientId(client_id: String) {
            OrderCloud._clientId = client_id
            // Add more stuff if needed in the future
        }

        /*
            Return the client id
        */
        public fun getClientId(): String {
            return OrderCloud._clientId
        }
    }
}