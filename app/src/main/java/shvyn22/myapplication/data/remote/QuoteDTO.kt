package shvyn22.myapplication.data.remote

import com.google.gson.annotations.SerializedName

data class QuoteDTO(
    @SerializedName("id")
    val id: Int,

    @SerializedName("quote")
    val quote: String?,

    @SerializedName("by")
    val author: String?,

    @SerializedName("image")
    val imgURL: String?
)
