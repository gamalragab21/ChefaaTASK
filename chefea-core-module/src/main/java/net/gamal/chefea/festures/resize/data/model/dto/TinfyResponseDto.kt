package net.gamal.chefea.festures.resize.data.model.dto


import com.google.gson.annotations.SerializedName
import net.gamal.chefea.core.common.data.model.dto.BaseDto

internal data class TinfyResponseDto(
    @SerializedName("output")
    var output: OutputDto
) : BaseDto()