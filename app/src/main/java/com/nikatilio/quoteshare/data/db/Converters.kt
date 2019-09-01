package com.nikatilio.quoteshare.data.db

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun stringFromStringList(value: String): List<String>? {
        return value.split(",")
    }

    @TypeConverter
    fun stringListToString(stringList: List<String>?): String? {
        return stringList?.joinToString(",")
    }
}