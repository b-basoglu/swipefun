package com.bbasoglu.swipefun.matchmaker.common.data.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.bbasoglu.swipefun.matchmaker.common.data.util.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = Constants.TABLE_RICK_MORTY_RESULTS,
    indices = [Index(value = ["id"], unique = true)]
)
data class RickMortyEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "dataIndex") var dataIndex: Int? = null,
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "status") val status: String?,
    @ColumnInfo(name = "species") val species: String?,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "gender") val gender: String?,
    @ColumnInfo(name = "image") val image: String?,
    @Embedded(prefix = "origin") val origin: OriginEntity?,
    @Embedded(prefix = "location") val location: LocationEntity?,
    @ColumnInfo(name = "episode") val episode: List<String>?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "created") val created: String?,
): Parcelable
