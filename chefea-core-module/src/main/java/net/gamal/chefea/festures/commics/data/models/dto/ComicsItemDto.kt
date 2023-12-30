package net.gamal.chefea.festures.commics.data.models.dto


import com.google.gson.annotations.SerializedName

internal data class ComicsItemDto(
    @SerializedName("id")
    var id: Int,
    @SerializedName("thumbnail")
    var thumbnail: ThumbnailDto,
    @SerializedName("title")
    var title: String
)