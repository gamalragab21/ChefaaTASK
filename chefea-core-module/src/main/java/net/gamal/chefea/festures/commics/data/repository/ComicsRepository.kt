package net.gamal.chefea.festures.commics.data.repository

import android.content.Context
import net.gamal.chefea.core.common.domain.model.request.RemoteRequest
import net.gamal.chefea.festures.commics.data.mapper.ComicsItemMapper
import net.gamal.chefea.festures.commics.data.mapper.ComicsMapper
import net.gamal.chefea.festures.commics.domain.models.Comics
import net.gamal.chefea.festures.commics.domain.models.ComicsItem
import net.gamal.chefea.festures.commics.domain.repository.IComicsRepository
import net.gamal.chefea.festures.commics.domain.repository.local.IComicsImagesLocalDs

import net.gamal.chefea.festures.commics.domain.repository.remote.IComicsImagesRemoteDs
import javax.inject.Inject

internal class ComicsRepository @Inject constructor(
    private val context: Context,
    private val remoteDS: IComicsImagesRemoteDs,
    private val localDs: IComicsImagesLocalDs
) : IComicsRepository {

    override suspend fun getComicsRemote(remoteRequest: RemoteRequest): Comics {
        val result = remoteDS.getComicsRemote(remoteRequest)
        val comics = ComicsMapper.dtoToDomain(result)
        comics.comicsData.results.forEach {
            it.thumbnail.setImageAsBitmap(it.thumbnail.path,context)
        }
        return comics
    }

    override suspend fun getComicsLocal(): List<ComicsItem> {
        val result = localDs.getComicsLocal()
        return ComicsItemMapper.entityToDomain(result).reversed()
    }

    override suspend fun getComicById(id: Int): ComicsItem {
        val result = localDs.getComicById(id)
        return ComicsItemMapper.entityToDomain(result)
    }

    override suspend fun saveComicsList(comicsItems: List<ComicsItem>) {
        val result = ComicsItemMapper.domainToEntity(comicsItems)
        localDs.saveComicsList(result)
    }

    override suspend fun saveComicsItem(comicsItem: ComicsItem) {
        val result = ComicsItemMapper.domainToEntity(comicsItem)
        localDs.saveComicsItem(result)
    }

    override suspend fun updateComicsItem(comicsItem: ComicsItem) {
        val result = ComicsItemMapper.domainToEntity(comicsItem)
        localDs.updateComicsItem(result)
    }
}