//
//  Products.kt
//  OrderCloud-SDK
//
//  Created by Ben Le Cam on 1/7/16.
//  Copyright © 2016 Ben Le Cam. All rights reserved.
//

package com.four51.ordercloud

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result

public class Products {
    companion object {
        /*
            GET /products/{id}
        */
        public fun getProduct(product: Product, completionHandler: (Request, Response, Result<String, FuelError>) -> Unit) {
            Api.get("products/${product.id}", completionHandler = completionHandler)
        }

        /*
            GET /products
        */
        public fun getProducts(productList: ProductList,
                               search: String = String(),
                               searchOn: String = String(),
                               sortBy: String = String(),
                               page: String = String(),
                               pageSize: String = String(),
                               completionHandler: (Request, Response, Result<String, FuelError>) -> Unit) {
            val params = mapOf(
            "search" to search,
            "searchOn" to searchOn,
            "sortBy" to sortBy,
            "page" to page,
            "pageSize" to pageSize
            )

            Api.get("products", parameters = params, completionHandler = completionHandler)
        }

        /*
            POST /products
        */
        public fun createProduct(product: Product, completionHandler: (Request, Response, Result<String, FuelError>) -> Unit) {
            Api.post("products", parameters = product.serialize(), completionHandler = completionHandler)
        }

        /*
            DELETE /products/{id}
        */
        public fun deleteProduct(product: Product, completionHandler: (Request, Response, Result<String, FuelError>) -> Unit) {
            Api.delete("products/${product.id}", completionHandler = completionHandler)
        }

        /*
            PUT /products/{id}
        */
        public fun updateProduct(product: Product, completionHandler: (Request, Response, Result<String, FuelError>) -> Unit) {
            Api.put("products/${product.id}", parameters = product.serialize(), completionHandler = completionHandler)
        }

        /*
            PATCH /products/{id}
        */
        public fun patchProduct(product: Product, completionHandler: (Request, Response, Result<String, FuelError>) -> Unit) {
            Api.patch("products/${product.id}", parameters = product.serialize(), completionHandler = completionHandler)
        }
    }
}