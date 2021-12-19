package shvyn22.flexingfinalspace.data.remote

import com.google.gson.annotations.SerializedName

data class CharacterDTO(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("species")
    val species: String?,

    @SerializedName("gender")
    val gender: String?,

    @SerializedName("hair")
    val hair: String?,

    @SerializedName("origin")
    val origin: String?,

    @SerializedName("abilities")
    val abilities: List<String>?,

    @SerializedName("alias")
    val alias: List<String>?,

    @SerializedName("img_url")
    val imgURL: String?
)
