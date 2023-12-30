package net.gamal.chefea.festures.commics.data.mapper

import net.gamal.chefea.android.common.data.mapper.Mapper
import net.gamal.chefea.festures.commics.data.models.dto.ComicsItemDto
import net.gamal.chefea.festures.commics.data.models.entity.ComicsItemEntity
import net.gamal.chefea.festures.commics.domain.models.ComicsItem

internal object ComicsItemMapper : Mapper<ComicsItemDto, ComicsItem, ComicsItemEntity>() {
    override fun dtoToDomain(dto: ComicsItemDto): ComicsItem {
        return ComicsItem(
            dto.id, ThumbnailMapper.dtoToDomain(dto.thumbnail), dto.title
        )
    }

    override fun domainToEntity(domain: ComicsItem): ComicsItemEntity {
        return ComicsItemEntity(
            domain.id,
            ThumbnailMapper.domainToEntity(domain.thumbnail),
            domain.title
        )
    }
}