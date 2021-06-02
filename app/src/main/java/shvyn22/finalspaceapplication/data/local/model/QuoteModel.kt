package shvyn22.finalspaceapplication.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Quote")
data class QuoteModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "quote")
    val quote: String,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "imgURL")
    val imgURL: String
)
