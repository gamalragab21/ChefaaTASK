package net.gamal.chefea.festures.commics.data.repository

import net.gamal.chefea.android.common.domain.model.request.RemoteRequest
import net.gamal.chefea.festures.commics.data.mapper.ComicsMapper
import net.gamal.chefea.festures.commics.domain.models.Comics
import net.gamal.chefea.festures.commics.domain.repository.IComicsRepository

import net.gamal.chefea.festures.commics.domain.repository.remote.IComicsImagesRemoteDs
import javax.inject.Inject

internal class ComicsRepository @Inject constructor(private val remoteDS: IComicsImagesRemoteDs) :
    IComicsRepository {

    override suspend fun getComicsRemote(remoteRequest: RemoteRequest): Comics {
        val result = remoteDS.getComicsRemote(remoteRequest)
        return ComicsMapper.dtoToDomain(result)
    }
}