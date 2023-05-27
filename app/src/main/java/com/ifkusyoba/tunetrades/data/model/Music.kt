package com.ifkusyoba.tunetrades.data.model

import androidx.annotation.StringRes

data class Music(
    val id: Long,
    val image: Int,
    val title: String,
    val price: Int,
    @StringRes val description: Int,
)