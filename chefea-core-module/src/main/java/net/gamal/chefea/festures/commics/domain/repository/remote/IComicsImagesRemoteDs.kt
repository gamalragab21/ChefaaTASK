package net.gamal.chefea.festures.commics.domain.repository.remote

import net.gamal.chefea.android.common.domain.model.request.RemoteRequest
import net.gamal.chefea.festures.commics.data.models.dto.ComicsResponseDto

internal interface IComicsImagesRemoteDs {
    suspend fun getComicsRemote(remoteRequest: RemoteRequest): ComicsResponseDto
}