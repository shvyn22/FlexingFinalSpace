package shvyn22.flexingfinalspace.data.local.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable