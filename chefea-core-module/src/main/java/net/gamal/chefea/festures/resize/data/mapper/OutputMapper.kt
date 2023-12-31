package net.gamal.chefea.festures.resize.data.mapper

import net.gamal.chefea.core.common.data.mapper.Mapper
import net.gamal.chefea.festures.resize.data.model.dto.OutputDto
import net.gamal.chefea.festures.resize.domain.model.Output

internal object OutputMapper : Mapper<OutputDto, Output, Unit>() {
    override fun dtoToDomain(dto: OutputDto): Output {
        return Output(dto.height, dto.url, dto.width)
    }
}