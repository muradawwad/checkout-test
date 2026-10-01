package com.maf.miniapp

import android.app.Application
import com.maf.checkouttest.initializeCheckout

class AppRoot : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeCheckout()
    }
}