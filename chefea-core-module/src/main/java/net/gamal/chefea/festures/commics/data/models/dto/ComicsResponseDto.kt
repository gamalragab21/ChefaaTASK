package net.gamal.chefea.festures.commics.data.models.dto


import com.google.gson.annotations.SerializedName
import net.gamal.chefea.android.common.data.model.dto.BaseDto

internal data class ComicsResponseDto(
    @SerializedName("attributionText")
    var attributionText: String,
    @SerializedName("data")
    var comicsData: ComicsDataDto,
    @SerializedName("status")
    var status: String
):BaseDto()