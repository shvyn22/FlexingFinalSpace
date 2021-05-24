package shvyn22.myapplication.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Episode")
data class EpisodeModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "airDate")
    val airDate: String,

    @ColumnInfo(name = "director")
    val director: String,

    @ColumnInfo(name = "writer")
    val writer: String,

    @ColumnInfo(name = "imgURL")
    val imgURL: String
)
