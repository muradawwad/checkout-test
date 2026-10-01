package com.maf.checkouttest

import com.maf.checkout.domain.model.MafCheckoutAuthProvider
import com.maf.checkout.domain.model.MafCheckoutConfiguration
import com.maf.checkout.domain.model.MafCheckoutEnvironment
import com.maf.checkout.domain.model.MafCheckoutPlatforms
import com.maf.checkout.presentation.MafCheckout

/**
 * Host-owned MPass token. The SDK asks for it at every checkout launch rather than caching it,
 * so updating this value is enough to sign a new session in.
 */
object CheckoutSession {
    var accessToken: String = ""
}

/**
 * Application-level setup. Must run once before any checkout UI composes.
 */
fun initializeCheckout() {
    MafCheckout.initialize(
        MafCheckoutConfiguration(
            environment = MafCheckoutEnvironment.Dev,
            businessUnit = MafCheckoutPlatforms.SHARE,
            clientUuid = "01a05220-bf20-73c8-9de6-a46b409ce068",
            locale = "en",
            country = "AE",
            authProvider = MafCheckoutAuthProvider {
                CheckoutSession.accessToken.takeIf { it.isNotBlank() }
            },
        ),
    )
}
