package net.gamal.chefea.festures.commics.data.mapper

import net.gamal.chefea.android.common.data.mapper.Mapper
import net.gamal.chefea.festures.commics.data.models.dto.ComicsDataDto
import net.gamal.chefea.festures.commics.domain.models.ComicsData

internal object ComicsDataMapper : Mapper<ComicsDataDto, ComicsData, Unit>() {
    override fun dtoToDomain(dto: ComicsDataDto): ComicsData =
        ComicsData(dto.count, dto.limit, dto.offset, dto.results.map {
            ComicsItemMapper.dtoToDomain(it)
        }, dto.total)
}