//
//  Product_tests.kt
//  OrderCloudSDK
//
//  Created by Ben Le Cam on 1/11/16.
//  Copyright © 2016 Ben Le Cam. All rights reserved.
//

package com.four51.ordercloud.tests

import com.beust.klaxon.*
import org.jetbrains.spek.api.*
import java.io.InputStream
import com.four51.ordercloud.Product

class ProductSpec : Spek() {
    init {
        given("'Product' class") {
            on("JSON without extended properties") {
                val loader: ClassLoader = Thread.currentThread().getContextClassLoader()
                var productJson: JsonObject = Parser().parse(loader.getResourceAsStream("product.json")) as JsonObject
                var product: Product


                it("serializes a product without extended properties") {
                    product = Product()

                    product.id = "1"
                    product.name = "test-product"
                    product.description = "This is a test product"
                    product.quantityMultiplier = 1
                    product.shipWeight = null
                    product.active = true
                    product.type = "Static"
                    product.inventoryEnabled = false
                    product.inventoryNotificationPoint = null
                    product.variantLevelInventory = false
                    product.xp = null
                    product.allowOrderExceedInventory = false
                    product.displayInventory = false

                    shouldEqual(productJson, JsonObject(product.serialize()))
                }

                it("deserializes a product without extended properties") {
                    product = Product()

                    try {
                        product.deserialize(productJson)
                    } catch(e: Exception) {
                        print("Could not deserialize JsonObject")
                    }

                    shouldEqual(product.id, "1")
                    shouldEqual(product.name, "test-product")
                    shouldEqual(product.description, "This is a test product")
                    shouldEqual(product.quantityMultiplier, 1)
                    shouldEqual(product.shipWeight, null)
                    shouldEqual(product.active, true)
                    shouldEqual(product.type, "Static")
                    shouldEqual(product.inventoryEnabled, false)
                    shouldEqual(product.inventoryNotificationPoint, null)
                    shouldEqual(product.variantLevelInventory, false)
                    shouldEqual(product.xp, null)
                    shouldEqual(product.allowOrderExceedInventory, false)
                    shouldEqual(product.displayInventory, false)
                }
            }

            on("JSON with extended properties") {
                val loader: ClassLoader = Thread.currentThread().getContextClassLoader()
                var productXpJson: JsonObject = Parser().parse(loader.getResourceAsStream("product_xp.json")) as JsonObject
                var product: Product

                it("serializes a product with extended properties", {
                    product = Product()

                    product.id = "1"
                    product.name = "test-product"
                    product.description = "This is a test product"
                    product.quantityMultiplier = 1
                    product.shipWeight = null
                    product.active = true
                    product.type = "Static"
                    product.inventoryEnabled = false
                    product.inventoryNotificationPoint = null
                    product.variantLevelInventory = false
                    product.allowOrderExceedInventory = false
                    product.displayInventory = false
                    product.xp = JsonObject(mapOf(
                            "xp1" to "value1",
                            "xp2" to 2,
                            "xp3" to false
                    ))

                    shouldEqual(productXpJson, JsonObject(product.serialize()))
                })

                it("deserializes a product with extended properties") {
                    product = Product()

                    try {
                        product.deserialize(productXpJson)
                    } catch (e: Exception) {
                        print("Could not deserialize JsonObject")
                    }

                    shouldEqual(product.xp!!.string("xp1"), "value1")
                    shouldEqual(product.xp!!.int("xp2"), 2)
                    shouldEqual(product.xp!!.boolean("xp3"), false)
                }
            }
        }
    }
}

