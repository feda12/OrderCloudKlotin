//
//  ProductList_tests.kt
//  OrderCloudSDK
//
//  Created by Ben Le Cam on 1/12/16.
//  Copyright © 2016 Ben Le Cam. All rights reserved.
//

package com.four51.ordercloud.tests

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import org.jetbrains.spek.api.*

import com.four51.ordercloud.Product
import com.four51.ordercloud.ProductList

class ProductListSpec: Spek() {
    
    init {
        given("'ProductList' class") {
            on("JSON Handling") {
                val loader: ClassLoader = Thread.currentThread().getContextClassLoader()
                var productsJson: JsonObject = Parser().parse(loader.getResourceAsStream("products.json")) as JsonObject

                it("serializes a product list") {
                    val products: ProductList = ProductList()

                    products.page = 1
                    products.pageSize = 20
                    products.totalCount = 2
                    products.totalPages = 1
                    products.itemRange = JsonArray<Int>(1,2)
                    
                    products.items += Product()
                    products.items += Product()
                    

                    products.items.first().id = "1"
                    products.items.first().name = "test-product"
                    products.items.first().description = "This is a test product"
                    products.items.first().quantityMultiplier = 1
                    products.items.first().shipWeight = null
                    products.items.first().active = true
                    products.items.first().type = "Static"
                    products.items.first().inventoryEnabled = false
                    products.items.first().inventoryNotificationPoint = null
                    products.items.first().variantLevelInventory = false
                    products.items.first().xp = null
                    products.items.first().allowOrderExceedInventory = false
                    products.items.first().displayInventory = false
                    
                    products.items.last().id = "2"
                    products.items.last().name = "test-product2"
                    products.items.last().description = "This is a test product 2"
                    products.items.last().quantityMultiplier = 2
                    products.items.last().shipWeight = null
                    products.items.last().active = true
                    products.items.last().type = "Static"
                    products.items.last().inventoryEnabled = false
                    products.items.last().inventoryNotificationPoint = null
                    products.items.last().variantLevelInventory = false
                    products.items.last().xp = null
                    products.items.last().allowOrderExceedInventory = false
                    products.items.last().displayInventory = false

                    shouldEqual(JsonObject(products.serialize()), productsJson)
                }
                
                it("deserializes a product") {
                    val products: ProductList =  ProductList()
                    
                    try {
                        products.deserialize(productsJson)
                    } catch (e: Exception) {
                        print("Could not deserialize JSON")
                    }
                    
                    shouldEqual(1, products.page)
                    shouldEqual(20, products.pageSize)
                    shouldEqual(2, products.totalCount)
                    shouldEqual(1, products.totalPages)
                    shouldEqual(JsonArray<Int>(1, 2), products.itemRange)
                }
            }
        }
    }
}
