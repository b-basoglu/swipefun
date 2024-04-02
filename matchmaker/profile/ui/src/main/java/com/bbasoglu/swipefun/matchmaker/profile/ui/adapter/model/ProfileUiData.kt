package com.bbasoglu.swipefun.matchmaker.profile.ui.adapter.model
import android.os.Parcelable
import com.bbasoglu.swipefun.matchmaker.profile.domain.model.LocationDomainModel
import com.bbasoglu.swipefun.matchmaker.profile.domain.model.OriginDomainModel
import com.bbasoglu.swipefun.uimodule.adapter.base.BaseAdapterData
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileUiData(
    override val id: String?,
    var dataIndex: Int? = null,
    val name: String?,
    val status: String?,
    val species : String?,
    val type : String?,
    val gender : String?,
    val image: String?,
    val origin: OriginUiModel?,
    val location: LocationUiModel?,
    val episode: List<String>?,
    val url: String?,
    val created: String?,
) : BaseAdapterData, Parcelable