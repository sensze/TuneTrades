package com.ifkusyoba.tunetrades.ui.views.checkout

import com.ifkusyoba.tunetrades.data.model.OrderMusic

data class CheckoutState(
    val orderMusic: List<OrderMusic>,
    val totalPrice: Int
)