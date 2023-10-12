package com.example.playpal.core.utils

import androidx.room.TypeConverter
import com.example.playpal.core.data.source.remote.response.ShortScreenshotsItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromList(list: List<ShortScreenshotsItem?>?): String? {
        if (list == null) return null
        val gson = Gson()
        val type = object : TypeToken<List<ShortScreenshotsItem?>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toList(value: String?): List<ShortScreenshotsItem?>? {
        if (value == null) return null
        val gson = Gson()
        val type = object : TypeToken<List<ShortScreenshotsItem?>>() {}.type
        return gson.fromJson(value, type)
    }
}
