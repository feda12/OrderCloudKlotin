//
//  Product.kt
//  OrderCloudSDK
//
//  Created by Ben Le Cam on 1/8/16.
//  Copyright © 2016 Ben Le Cam. All rights reserved.
//

package com.four51.ordercloud

import com.beust.klaxon.*

public class Product : Serializable {
    // Public properties (!! means they must be filled, ? means they can be null)
    public var id: String = String()
    public var name: String? = String()
    public var description: String? = String()
    public var quantityMultiplier: Int = 0
    public var shipWeight: Double? = null
    public var active: Boolean? = false
    public var type: String? = String()
    public var inventoryEnabled: Boolean? = false
    public var inventoryNotificationPoint: Int? = null
    public var variantLevelInventory: Boolean? = false
    public var xp: JsonObject? = null
    public var allowOrderExceedInventory: Boolean? = false
    public var displayInventory: Boolean? = false


    constructor() {
    }

    constructor(productId: String) {
        id = productId
    }

    constructor(json: JsonObject) {
        this.deserialize(json)
    }

    override public fun deserialize(jsonData: JsonObject) {
        super.deserialize(jsonData)

        this.id = jsonData.string("ID")!!
        this.name = jsonData.string("Name")
        this.description = jsonData.string("Description")
        this.quantityMultiplier = jsonData.int("QuantityMultiplier")!!
        this.shipWeight = jsonData.double("ShipWeight")
        this.active = jsonData.boolean("Active")
        this.type = jsonData.string("Type")
        this.inventoryEnabled = jsonData.boolean("InventoryEnabled")
        this.inventoryNotificationPoint = jsonData.int("InventoryNotificationPoint")
        this.variantLevelInventory = jsonData.boolean("VariantLevelInventory")
        this.xp = jsonData.obj("xp")
        this.allowOrderExceedInventory = jsonData.boolean("AllowOrderExceedInventory")
        this.displayInventory = jsonData.boolean("DisplayInventory")
    }

    override public fun serialize(): Map<String, Any?> {
        return mapOf(
                "ID" to this.id,
                "Name" to this.name,
                "Description" to this.description,
                "QuantityMultiplier" to this.quantityMultiplier,
                "ShipWeight" to this.shipWeight,
                "Active" to this.active,
                "Type" to this.type,
                "InventoryEnabled" to this.inventoryEnabled,
                "InventoryNotificationPoint" to this.inventoryNotificationPoint,
                "VariantLevelInventory" to this.variantLevelInventory,
                "xp" to this.xp,
                "AllowOrderExceedInventory" to this.allowOrderExceedInventory,
                "DisplayInventory" to this.displayInventory
        )
    }
}

/*
    Comparison operator for (two products) {
*/
//    public fun == (p1: OrderCloud.Product, p2: OrderCloud.Product): Boolean {
//        return p1.name == p2.name
//                && p1.id == p2.id
//                && p1.description == p2.description
//                && p1.quantityMultiplier == p2.quantityMultiplier
//                && p1.shipWeight == p2.shipWeight
//                && p1.active == p2.active
//                && p1.type == p2.type
//                && p1.inventoryEnabled == p2.inventoryEnabled
//                && p1.inventoryNotificationPoint == p2.inventoryNotificationPoint
//                && p1.variantLevelInventory == p2.variantLevelInventory
//                && p1.allowOrderExceedInventory == p2.allowOrderExceedInventory
//                && p1.displayInventory == p2.displayInventory
//    }
