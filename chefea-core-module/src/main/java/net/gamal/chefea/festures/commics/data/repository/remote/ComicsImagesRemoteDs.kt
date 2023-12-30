package net.gamal.chefea.festures.commics.data.repository.remote

import net.gamal.chefea.android.common.domain.model.request.RemoteRequest
import net.gamal.chefea.android.common.domain.repository.remote.ChefeaApiService
import net.gamal.chefea.festures.commics.data.models.dto.ComicsResponseDto
import net.gamal.chefea.festures.commics.domain.repository.remote.IComicsImagesRemoteDs
import javax.inject.Inject

internal class ComicsImagesRemoteDs @Inject constructor(private val provider: ChefeaApiService) :
    IComicsImagesRemoteDs {

    override suspend fun getComicsRemote(remoteRequest: RemoteRequest): ComicsResponseDto {
        return provider.getComics(remoteRequest.requestQueries)
    }
}