package net.gamal.chefea.festures.commics.data.repository.local

import net.gamal.chefea.core.common.data.repository.local.room.ChefeaDatabase
import net.gamal.chefea.festures.commics.data.models.entity.ComicsItemEntity
import net.gamal.chefea.festures.commics.domain.repository.local.IComicsImagesLocalDs
import javax.inject.Inject

internal class ComicsImagesLocalDs @Inject constructor(private val chefeaDatabase: ChefeaDatabase) :
    IComicsImagesLocalDs {

    override suspend fun getComicsLocal(): List<ComicsItemEntity> {
        return chefeaDatabase.comicsDao().getAllComics()
    }

    override suspend fun getComicById(id: Int): ComicsItemEntity {
        return chefeaDatabase.comicsDao().getComicById(id)
    }

    override suspend fun saveComicsList(comicsItems: List<ComicsItemEntity>) {
        chefeaDatabase.comicsDao().insertAllComics(comicsItems)
    }

    override suspend fun saveComicsItem(comicsItem: ComicsItemEntity) {
        chefeaDatabase.comicsDao().insertComic(comicsItem)
    }

    override suspend fun updateComicsItem(comicsItem: ComicsItemEntity) {
        chefeaDatabase.comicsDao().updateComic(comicsItem)
    }
}