package net.gamal.chefea.festures.resize.data.model.dto


import com.google.gson.annotations.SerializedName

data class OutputDto(
    @SerializedName("height")
    var height: Float,
    @SerializedName("url")
    var url: String,
    @SerializedName("width")
    var width: Float
)