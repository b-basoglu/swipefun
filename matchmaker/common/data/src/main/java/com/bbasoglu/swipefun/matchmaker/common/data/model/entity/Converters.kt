package com.bbasoglu.swipefun.matchmaker.common.data.model.entity

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun stringListToJsonString(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun stringItemJsonStringToList(value: String?): List<String>? {
        if (value.isNullOrEmpty()) {
            return null
        }
        return try {
            Gson().fromJson(value, Array<String>::class.java).toList()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    @TypeConverter
    fun resultLocationEntityToJsonString(value: LocationEntity?): String = Gson().toJson(value)

    @TypeConverter
    fun resultLocationEntityJsonStringToList(value: String):LocationEntity =
        Gson().fromJson(value, LocationEntity::class.java)

    @TypeConverter
    fun resultOriginEntityToJsonString(value: OriginEntity?): String = Gson().toJson(value)

    @TypeConverter
    fun resultOriginEntityJsonStringToList(value: String):OriginEntity =
        Gson().fromJson(value, OriginEntity::class.java)
}
