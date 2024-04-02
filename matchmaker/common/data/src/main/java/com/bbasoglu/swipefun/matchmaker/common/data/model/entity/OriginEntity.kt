package com.bbasoglu.swipefun.matchmaker.common.data.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OriginEntity(
    val name: String?,
    val url: String?
): Parcelable
