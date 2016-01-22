//
//  OrderCloud_SDK_tests.kt
//  OrderCloud-SDK-tests
//
//  Created by Ben Le Cam on 1/8/16.
//  Copyright © 2016 Ben Le Cam. All rights reserved.
//

package com.four51.ordercloud.tests

import org.jetbrains.spek.api.Spek

import com.four51.ordercloud.Util
import com.four51.ordercloud.User
import com.four51.ordercloud.Product
import com.four51.ordercloud.ProductList

class OrderCloudSDKSpec: Spek() {
    init
    {
        given("a developer")
        {
            on("typing on a keyboard")
            {
                it("works") {
                    print(Util.userAgent())
                }
            }
        }
    }
}
