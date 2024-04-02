package com.bbasoglu.swipefun.matchmaker.profile.ui.adapter.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OriginUiModel(
    val name: String?,
    val url: String?
): Parcelable
