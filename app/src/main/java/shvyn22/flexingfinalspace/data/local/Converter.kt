package shvyn22.flexingfinalspace.data.local

import androidx.room.TypeConverter

class Converter {

    @TypeConverter
    fun fromListToString(list: List<String>): String {
        return list.joinToString(separator = ", ")
    }

    @TypeConverter
    fun fromStringToList(string: String): List<String> {
        return if (string.isEmpty()) return emptyList()
        else string.split(", ")
    }
}


