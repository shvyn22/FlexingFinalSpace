package shvyn22.flexingfinalspace.data.remote.dto

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
