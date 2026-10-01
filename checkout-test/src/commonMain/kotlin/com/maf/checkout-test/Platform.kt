package com.maf.checkout-test

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
