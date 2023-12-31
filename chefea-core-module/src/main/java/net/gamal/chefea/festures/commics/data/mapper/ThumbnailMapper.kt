package net.gamal.chefea.festures.commics.data.mapper

import net.gamal.chefea.core.common.data.mapper.Mapper
import net.gamal.chefea.festures.commics.data.models.dto.ThumbnailDto
import net.gamal.chefea.festures.commics.data.models.entity.ThumbnailEntity
import net.gamal.chefea.festures.commics.domain.models.Thumbnail

internal object ThumbnailMapper : Mapper<ThumbnailDto, Thumbnail, ThumbnailEntity>() {
    override fun dtoToDomain(dto: ThumbnailDto): Thumbnail {
        return Thumbnail(dto.getCompletePath())
    }

    override fun domainToEntity(domain: Thumbnail): ThumbnailEntity {
        return ThumbnailEntity(domain.encodeToByteArray(), domain.path)
    }

    override fun entityToDomain(entity: ThumbnailEntity): Thumbnail {
        return Thumbnail(imageBitmap = entity.getBitmap(), path = entity.path)
    }
}