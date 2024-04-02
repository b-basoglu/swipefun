package com.bbasoglu.swipefun.matchmaker.feed.composeui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class LocationUiData(
    val name: String?,
    val url: String?
): Parcelable