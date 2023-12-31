package net.gamal.chefea.festures.commics.domain.repository

import net.gamal.chefea.core.common.domain.model.request.RemoteRequest
import net.gamal.chefea.festures.commics.domain.models.Comics
import net.gamal.chefea.festures.commics.domain.models.ComicsItem

interface IComicsRepository {
    suspend fun getComicsRemote(remoteRequest: RemoteRequest): Comics

    suspend fun getComicsLocal(): List<ComicsItem>
    suspend fun getComicById(id: Int): ComicsItem
    suspend fun saveComicsList(comicsItems: List<ComicsItem>)
    suspend fun saveComicsItem(comicsItem: ComicsItem)
    suspend fun updateComicsItem(comicsItem: ComicsItem)
}