package com.bbasoglu.swipefun.matchmaker.profile.ui.adapter

import com.bbasoglu.swipefun.matchmaker.profile.ui.adapter.model.ProfileUiData

sealed class ProfileAdapterClickEvent {
    data class ProfileItemClicked(val profileUiData: ProfileUiData) : ProfileAdapterClickEvent()
}