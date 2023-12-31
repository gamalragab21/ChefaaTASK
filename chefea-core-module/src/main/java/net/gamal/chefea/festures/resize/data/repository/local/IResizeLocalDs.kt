package net.gamal.chefea.festures.resize.data.repository.local

import net.gamal.chefea.core.common.data.repository.local.room.ChefeaDatabase
import net.gamal.chefea.festures.commics.data.models.entity.ComicsItemEntity
import net.gamal.chefea.festures.resize.domain.repository.local.IResizeLocalDs
import javax.inject.Inject

internal class ResizeLocalDs @Inject constructor(private val chefeaDatabase: ChefeaDatabase) :
    IResizeLocalDs {
    override suspend fun updateComic(comicsItem: ComicsItemEntity) {
        chefeaDatabase.comicsDao().updateComic(comicsItem)
    }
}