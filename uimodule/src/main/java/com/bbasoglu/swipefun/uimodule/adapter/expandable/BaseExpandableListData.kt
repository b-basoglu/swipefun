package com.bbasoglu.swipefun.uimodule.adapter.expandable

interface BaseExpandableListData {
    val id: String?
    var isExpanded: Boolean
    override fun equals(other: Any?): Boolean
}