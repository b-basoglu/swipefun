package com.bbasoglu.swipefun.matchmaker.feed.ui.adapter.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class LocationUiData(
    val name: String?,
    val url: String?
): Parcelable