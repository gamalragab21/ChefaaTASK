package net.gamal.chefea.festures.resize.data.mapper

import net.gamal.chefea.core.common.data.mapper.Mapper
import net.gamal.chefea.festures.resize.data.model.dto.TinfyResponseDto
import net.gamal.chefea.festures.resize.domain.model.TinfyResponse

internal object TinfyResponseMapper : Mapper<TinfyResponseDto, TinfyResponse, Unit>() {
    override fun dtoToDomain(dto: TinfyResponseDto): TinfyResponse {
        return TinfyResponse(OutputMapper.dtoToDomain(dto.output))
    }

}