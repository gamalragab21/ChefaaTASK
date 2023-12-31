package net.gamal.chefea.festures.resize.domain.repository.local

import net.gamal.chefea.festures.commics.data.models.entity.ComicsItemEntity

internal interface IResizeLocalDs {
    suspend fun updateComic(comicsItem: ComicsItemEntity)
}