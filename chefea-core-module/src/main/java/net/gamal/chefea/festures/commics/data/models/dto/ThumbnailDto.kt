package net.gamal.chefea.festures.commics.data.models.dto


import com.google.gson.annotations.SerializedName

internal data class ThumbnailDto(
    @SerializedName("extension")
    var extension: String,
    @SerializedName("path")
    var path: String
)