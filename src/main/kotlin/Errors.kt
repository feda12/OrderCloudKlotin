//
//  Errors.kt
//  OrderCloudSDK
//
//  Created by Ben Le Cam on 1/21/16.
//  Copyright © 2016 Ben Le Cam. All rights reserved.
//


package com.four51.ordercloud

import com.beust.klaxon.JsonObject
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs

public class ApiError: Exception
{
    private var _errorCode: String = String()
    private var _message: String = String()

    constructor(errorCode: String, message: String)
    {
        this._errorCode = errorCode
        this._message  = message
    }
}

public class HttpError: Throwable
{
    private var _httpCode: Int = 0
    private var _message: String = String()

    constructor(httpCode: Int, message: String)
    {
        this._httpCode = httpCode
        this._message  = message
    }
}