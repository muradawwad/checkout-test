package com.maf.miniapp

import androidx.compose.ui.window.ComposeUIViewController
import com.maf.checkouttest.initializeCheckout
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    initializeCheckout()
    return ComposeUIViewController {
        App()
    }
}