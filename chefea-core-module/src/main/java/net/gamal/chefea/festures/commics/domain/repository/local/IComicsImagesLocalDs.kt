package net.gamal.chefea.festures.commics.domain.repository.local

import net.gamal.chefea.festures.commics.data.models.entity.ComicsItemEntity

internal interface IComicsImagesLocalDs {
    suspend fun getComicsLocal(): List<ComicsItemEntity>
    suspend fun getComicById(id: Int): ComicsItemEntity
    suspend fun saveComicsList(comicsItems: List<ComicsItemEntity>)
    suspend fun saveComicsItem(comicsItem: ComicsItemEntity)
    suspend fun updateComicsItem(comicsItem: ComicsItemEntity)
}