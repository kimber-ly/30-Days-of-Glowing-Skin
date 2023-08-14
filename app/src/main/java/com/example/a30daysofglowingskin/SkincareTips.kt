package com.example.a30daysofglowingskin

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class SkincareTips(
    val day: Int,
    @StringRes val tips: Int,
    @DrawableRes val image: Int,
    @StringRes val description: Int,
)
