package shvyn22.finalspaceapplication.data.remote

import com.google.gson.annotations.SerializedName

data class EpisodeDTO(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String?,

    @SerializedName("air_date")
    val airDate: String?,

    @SerializedName("director")
    val director: String?,

    @SerializedName("writer")
    val writer: String?,

    @SerializedName("img_url")
    val imgURL: String?
)
