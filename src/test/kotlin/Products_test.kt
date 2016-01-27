//
//  Products_test.kt
//  OrderCloudSDK
//
//  Created by Ben Le Cam on 1/8/16.
//  Copyright © 2016 Ben Le Cam. All rights reserved.
//
package com.four51.ordercloud.tests

import com.beust.klaxon.*
import com.four51.ordercloud.*
import org.jetbrains.spek.api.*
import java.util.concurrent.CountDownLatch

class ProductsSpec : Spek() {
    init {
        given("Products API Resource") {
            on("Authenticated user") {
                it("creates, gets, updates, patches and deletes a product") {
                    val loader: ClassLoader = Thread.currentThread().getContextClassLoader()
                    OrderCloud.setupClientId("26B93175-8B38-4EB3-969F-91ACB109DA2D")
                    val lock = CountDownLatch(1)

                    User.currentUser.authenticate("blecam", password = "fails345", completionHandler = Util.defaultAuthHandler(lock))
                    lock.await()
                    shouldEqual(true, User.currentUser.isAuthenticated())

                    val productA: Product = Product()

                    // Test: create a product
                    val productJson = Parser().parse(loader.getResourceAsStream("product_create.json")) as JsonObject
                    try {
                        productA.deserialize(productJson)
                    } catch (e: Exception) {
                        print("Could not load JSON")
                    }
                    Products.createProduct(productA, completionHandler = { request, response, result ->
                        Serializable.completionHandler(productA)
                    })

                    // Test: get a single product with id of the one created earlier
                    // This test also ensure we were able to create the product
                    val productB: Product = Product(productId = productA.id)
                    Products.getProduct(productB, completionHandler = { request, response, result ->
                        Serializable.completionHandler(productA)
                        shouldEqual(productA.name, productB.name)
                    })

                    // Test: updating a product
                    productA.name = "Product Update"
                    Products.updateProduct(productA, completionHandler = Serializable.completionHandler(productA))


                    Products.getProduct(productB, completionHandler = { request, response, result ->
                        Serializable.completionHandler(productA)
                        shouldEqual(productA.name, productB.name)
                    })
                    // Test: patching a product
                    productA.name = "Product Patch"
                    Products.patchProduct(productA, completionHandler = Serializable.completionHandler(productA))

                    Products.getProduct(productB, completionHandler = { request, response, result ->
                        Serializable.completionHandler(productA)
                        shouldEqual(productA.name, productB.name)
                    })

                    // Test: deleting a product
                    Products.deleteProduct(productA, completionHandler = Serializable.completionHandler(productA))

                }
            }
        }
    }
}
