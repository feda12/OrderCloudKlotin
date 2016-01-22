//
//  User.kt
//  OrderCloudSDK
//
//  Created by Ben Le Cam on 1/8/16.
//  Copyright © 2016 Ben Le Cam. All rights reserved.
//


package com.four51.ordercloud

import com.beust.klaxon.*
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import org.joda.time.DateTime
import java.util.*

public class User : Serializable {
    // Singleton pattern
    companion object {
        public val currentUser: User = User()
    }

    // Private properties
    private var accessToken: String = String()

    // Public properties
    public var firstName: String? = String()
    public var lastName: String? = String()
    public var id: String = String()
    public var username: String = String()
    public var email: String? = String()
    public var phone: String? = String()
    public var termsAccepted: DateTime? = DateTime()
    public var active: Boolean? = false
    public var xp: JsonObject? = JsonObject()


    constructor() {

    }

    /*
        Access token getter
    */
    public fun getAccessToken(): String {
        return this.accessToken
    }

    /*
        Method to setup user given a token
    */
    public fun setupWithToken(token: String) {
        this.accessToken = token;
        Api.get("me", completionHandler = Serializable.completionHandler(this))
    }

    /*
        Authenticate a user given an username, password and scope as well as a potential handler
    */
    public fun authenticate(username: String, password: String, scope: String = "FullAccess", completionHandler: (Request, Response, Result<String, FuelError>) -> Unit = Util.defaultAuthHandler()) {
        val authParams = mapOf(
                "grant_type" to "password",
                "client_id" to OrderCloud.getClientId(),
                "username" to username,
                "password" to password,
                "scope" to scope
        )

        Http.request(method = Http.Method.POST, url = Util.authUrl(), parameters = authParams, completionHandler = completionHandler)
    }

    /*
        Returns whether the user is authenticated or not
    */
    public fun isAuthenticated(): Boolean {
        return this.accessToken.count() > 0
    }

    /*
        Log user out by removing all of its data
    */
    public fun logout() {
        this.accessToken = String()
        this.firstName = String()
        this.lastName = String()
        this.id = String()
        this.username = String()
        this.email = String()
        this.phone = String()
        this.termsAccepted = null
        this.active = false
        this.xp = JsonObject()
    }

    override public fun deserialize(jsonData: JsonObject) {
        super.deserialize(jsonData)

        this.firstName = jsonData.string("FirstName")
        this.lastName = jsonData.string("LastName")
        this.id = jsonData.string("ID")!!
        this.username = jsonData.string("Username")!!
        this.email = jsonData.string("Email")
        this.phone = jsonData.string("Phone")
        this.termsAccepted = DateTime.parse(jsonData.string("TermsAccepted"))
        this.active = jsonData.boolean("Active")
        this.xp = jsonData.obj("xp")
    }

    override public fun serialize(): Map<String, Any?> {
        return mapOf(
                "ID" to this.id,
                "Username" to this.username,
                "FirstName" to this.firstName,
                "LastName" to this.lastName,
                "Email" to this.email,
                "Phone" to this.phone,
                "TermsAccepted" to this.termsAccepted.toString(),
                "Active" to this.active,
                "xp" to this.xp
        )
    }
}
