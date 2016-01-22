//
//  User_tests.kt
//  OrderCloudSDK
//
//  Created by Ben Le Cam on 1/11/16.
//  Copyright © 2016 Ben Le Cam. All rights reserved.
//


package com.four51.ordercloud.tests

import com.beust.klaxon.*
import com.four51.ordercloud.*
import org.jetbrains.spek.api.*
import org.joda.time.DateTime

class UserSpec : Spek() {
    init {
        given("Current User") {
            on("authentication")
            {
                it("returns a valid user object") {
                    shouldNotBeNull(User.currentUser)
                }

                it("is not authenticated") {
                    shouldEqual(false, User.currentUser.isAuthenticated())
                }

                it("authenticates an user properly") {
                    shouldEqual(false, User.currentUser.isAuthenticated())
                    OrderCloud.setupClientId("26B93175-8B38-4EB3-969F-91ACB109DA2D")
                    User.currentUser.authenticate("blecam", password = "fails345", completionHandler = { request, response, result ->
                        Util.defaultAuthHandler()
                        shouldEqual(true, User.currentUser.isAuthenticated())
                    })
                    User.currentUser.logout()
                }
            }
        }

        given("user data")
        {
            on("without extended properties") {
                val loader: ClassLoader = Thread.currentThread().getContextClassLoader()
                var userJson: JsonObject = Parser().parse(loader.getResourceAsStream("user.json")) as JsonObject
                User.currentUser.logout()


                it("deserializes a User JSON") {
                    try {
                        User.currentUser.deserialize(userJson)
                    } catch (e: Exception) {
                        println(e)
                        println("Could not deserialize userXpJson")
                    }

                    shouldEqual(User.currentUser.id, "userid")
                    shouldEqual(User.currentUser.username, "username")
                    shouldEqual(User.currentUser.firstName, "firstname")
                    shouldEqual(User.currentUser.lastName, "lastname")
                    shouldEqual(User.currentUser.email, "me@four51.com")
                    shouldEqual(User.currentUser.phone, "1234567890")
                    shouldEqual(User.currentUser.termsAccepted, DateTime.parse("2016-01-11T00:00:00.000-06:00"))
                    shouldEqual(User.currentUser.active, true)
                    shouldEqual(User.currentUser.xp, null)
                }
                it("serializes a User JSON") {
                    User.currentUser.id = "userid"
                    User.currentUser.username = "username"
                    User.currentUser.firstName = "firstname"
                    User.currentUser.lastName = "lastname"
                    User.currentUser.phone = "1234567890"
                    User.currentUser.email = "me@four51.com"
                    User.currentUser.termsAccepted = DateTime.parse("2016-01-11T00:00:00.000-06:00")
                    User.currentUser.active = true
                    User.currentUser.xp = null


                    shouldEqual(userJson, JsonObject(User.currentUser.serialize()))
                }
            }
            on("with extended properties") {
                val loader: ClassLoader = Thread.currentThread().getContextClassLoader()
                var userXpJson: JsonObject = Parser().parse(loader.getResourceAsStream("user_xp.json")) as JsonObject

                it("deserializes a User JSON with extended properties") {
                    try {
                        User.currentUser.deserialize(userXpJson)
                    } catch (e: Exception) {
                        print("Could not deserialize userXpJson")
                    }
                    shouldEqual(User.currentUser.xp!!.string("xp1"), "value1")
                    shouldEqual(User.currentUser.xp!!.int("xp2"), 2)
                    shouldEqual(User.currentUser.xp!!.boolean("xp3"), false)
                }

                it("serializes a User JSON with extended properties") {
                    User.currentUser.id = "userid"
                    User.currentUser.username = "username"
                    User.currentUser.firstName = "firstname"
                    User.currentUser.lastName = "lastname"
                    User.currentUser.phone = "1234567890"
                    User.currentUser.email = "me@four51.com"
                    User.currentUser.termsAccepted = DateTime.parse("2016-01-11T00:00:00.000-06:00")
                    User.currentUser.active = true
                    User.currentUser.xp = JsonObject(mapOf(
                            "xp1" to "value1",
                            "xp2" to 2,
                            "xp3" to false
                    ))

                    shouldEqual(userXpJson, JsonObject(User.currentUser.serialize()))
                }
            }
        }
        given("User API calls") {
            on("/me") {
                it("fetches the data serializes it") {
                    User.currentUser.logout()
                    shouldEqual(false, User.currentUser.isAuthenticated())
                    OrderCloud.setupClientId("26B93175-8B38-4EB3-969F-91ACB109DA2D")
                    User.currentUser.authenticate("blecam", password = "fails345", completionHandler = { request, response, result ->
                        Util.defaultAuthHandler()
                        shouldEqual(true, User.currentUser.isAuthenticated())
                    })

                    Api.get("me", completionHandler = { request, response, result ->
                        Serializable.completionHandler(User.currentUser)
                        shouldNotEqual(String(), User.currentUser.firstName)
                    })
                }
            }
        }
    }
}
