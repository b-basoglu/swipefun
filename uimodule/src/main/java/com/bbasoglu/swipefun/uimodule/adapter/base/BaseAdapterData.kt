package com.bbasoglu.swipefun.uimodule.adapter.base

import android.os.Parcelable

interface BaseAdapterData: Parcelable {
    val id: String?
    override fun equals(other: Any?): Boolean
}