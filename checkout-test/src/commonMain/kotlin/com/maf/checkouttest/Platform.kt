package com.maf.checkouttest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
