package net.gamal.chefea.festures.commics.data.mapper

import net.gamal.chefea.core.common.data.mapper.Mapper
import net.gamal.chefea.festures.commics.data.models.dto.ComicsResponseDto
import net.gamal.chefea.festures.commics.domain.models.Comics

internal object ComicsMapper : Mapper<ComicsResponseDto, Comics, Unit>() {
    override fun dtoToDomain(dto: ComicsResponseDto): Comics {
        return Comics(
            dto.attributionText,
            ComicsDataMapper.dtoToDomain(dto.comicsData),
            dto.status
        ).apply {
            codeDto = dto.code ?: -1
            status = dto.status
        }
    }
}