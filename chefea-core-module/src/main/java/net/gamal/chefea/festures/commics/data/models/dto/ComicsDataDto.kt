package net.gamal.chefea.festures.commics.data.models.dto


import com.google.gson.annotations.SerializedName

internal data class ComicsDataDto(
    @SerializedName("count")
    var count: Int,
    @SerializedName("limit")
    var limit: Int,
    @SerializedName("offset")
    var offset: Int,
    @SerializedName("results")
    var results: List<ComicsItemDto>,
    @SerializedName("total")
    var total: Int
)