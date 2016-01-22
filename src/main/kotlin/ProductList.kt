//
//  ProductList.kt
//  OrderCloudSDK
//
//  Created by Ben Le Cam on 1/12/16.
//  Copyright © 2016 Ben Le Cam. All rights reserved.
//

package com.four51.ordercloud

import com.beust.klaxon.*

public class ProductList : Serializable {
    // Public properties (!! means they must be filled, ? means they can be null)
    public var items: Array<Product> = emptyArray<Product>()
    public var page: Int? = 0
    public var pageSize: Int? = 0
    public var totalCount: Int? = 0
    public var totalPages: Int? = 0
    public var itemRange: JsonArray<Int>? = JsonArray<Int>()

    constructor() {
    }

    override public fun deserialize(jsonData: JsonObject) {
        super.deserialize(jsonData)
        val meta: JsonObject = jsonData.obj("Meta")!!
        this.page = meta.int("Page")
        this.pageSize = meta.int("PageSize")
        this.totalCount = meta.int("TotalCount")
        this.totalPages = meta.int("TotalPages")
        this.itemRange = meta.array("ItemRange")

        val items: JsonArray<JsonObject> = jsonData.array<JsonObject>("Items")!!

        for (item in items) {
            this.items.plus(Product(json = item))
        }
    }

    override public fun serialize(): Map<String, Any> {

        var itemsData: JsonArray<JsonObject> = JsonArray<JsonObject>()
        for (product in this.items) {
            itemsData.add(JsonObject(product.serialize()))
        }

        return mapOf<String, Any>(
                "Meta" to JsonObject(mapOf(
                        "Page" to this.page,
                        "PageSize" to this.pageSize,
                        "TotalCount" to this.totalCount,
                        "TotalPages" to this.totalPages,
                        "ItemRange" to this.itemRange
                )),
                "Items" to itemsData
        )
    }
}
