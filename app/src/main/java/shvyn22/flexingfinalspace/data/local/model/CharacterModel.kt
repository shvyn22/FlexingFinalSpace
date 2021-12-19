package shvyn22.flexingfinalspace.data.local.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Character")
@Parcelize
data class CharacterModel(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "species")
    val species: String,

    @ColumnInfo(name = "gender")
    val gender: String,

    @ColumnInfo(name = "hair")
    val hair: String,

    @ColumnInfo(name = "origin")
    val origin: String,

    @ColumnInfo(name = "abilities")
    val abilities: List<String>,

    @ColumnInfo(name = "alias")
    val alias: List<String>,

    @ColumnInfo(name = "imgURL")
    val imgURL: String
) : Parcelable
