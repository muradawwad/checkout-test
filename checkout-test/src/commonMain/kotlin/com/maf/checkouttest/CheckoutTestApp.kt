package com.maf.checkouttest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maf.checkout.domain.model.CheckoutCurrency
import com.maf.checkout.domain.model.CheckoutDelivery
import com.maf.checkout.domain.model.CheckoutProduct
import com.maf.checkout.domain.model.CheckoutProductType
import com.maf.checkout.domain.model.CheckoutRequest
import com.maf.checkout.domain.model.MafCheckoutCustomer
import com.maf.checkout.domain.model.Money
import com.maf.checkout.domain.model.SummaryItem
import com.maf.checkout.presentation.CheckoutCallbacks
import com.maf.checkout.presentation.MafCheckout

private val DemoAmount = Money(minorUnits = 55_000L, currency = CheckoutCurrency.AED)

/**
 * The purchase being made. Per-launch data only — environment and auth live on the
 * configuration passed to [initializeCheckout].
 */
private fun demoRequest(): CheckoutRequest = CheckoutRequest(
    productType = CheckoutProductType.GiftCard,
    productCode = "moe_giftcards",
    amount = DemoAmount,
    products = listOf(
        CheckoutProduct(
            id = "MGC-DIGITAL-550",
            name = "Mall Gift Card",
            quantity = 1,
            amount = DemoAmount,
        ),
    ),
    summaryItems = listOf(
        SummaryItem(title = "Subtotal", value = "AED 550.00"),
        SummaryItem(title = "Shipping", value = "FREE"),
    ),
    delivery = CheckoutDelivery.Digital,
    customer = MafCheckoutCustomer("Andrei", "a@maf.ae", "+97112345678", "+971"),
    isLoggedIn = true,
)

/**
 * Storefront that launches the SDK checkout in-process, mirroring the MAFCheckout demo app.
 */
@Composable
fun CheckoutTestApp(modifier: Modifier = Modifier) {
    var launched by remember { mutableStateOf(false) }
    var token by remember { mutableStateOf(CheckoutSession.accessToken) }
    var outcome by remember { mutableStateOf<String?>(null) }

    // Fetches design tokens before Buy now so checkout opens without a cold start.
    MafCheckout.warmTheme()

    if (launched) {
        MafCheckout(
            request = remember { demoRequest() },
            callbacks = CheckoutCallbacks(
                onPaymentSuccess = {
                    outcome = "Paid"
                    launched = false
                },
                onPaymentFailure = { error ->
                    outcome = "Failed — ${error.code}\n${error.diagnostic}"
                    launched = false
                },
                onCheckoutClose = {
                    outcome = "Cancelled"
                    launched = false
                },
            ),
            modifier = Modifier.fillMaxSize(),
        )
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text("Mall Gift Card", style = MaterialTheme.typography.headlineSmall)
        Text("AED 550.00", style = MaterialTheme.typography.titleMedium)

        OutlinedTextField(
            value = token,
            onValueChange = {
                token = it
                CheckoutSession.accessToken = it.trim()
            },
            label = { Text("MPass access token") },
            modifier = Modifier.fillMaxWidth(),
        )

        Button(
            onClick = {
                outcome = null
                launched = true
            },
            enabled = token.isNotBlank(),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Buy now")
        }

        outcome?.let { Text(it, style = MaterialTheme.typography.bodyMedium) }
    }
}
