package net.gamal.chefea.festures.resize.domain.repository.remote

import net.gamal.chefea.core.common.domain.model.request.RemoteRequest
import net.gamal.chefea.festures.resize.data.model.dto.TinfyResponseDto
import okhttp3.ResponseBody

internal interface IResizeRemoteDs {
    suspend fun shrinkImageLocalFile(remoteRequest: RemoteRequest): TinfyResponseDto
    suspend fun shrinkImageUrlFile(remoteRequest: RemoteRequest): TinfyResponseDto
    suspend fun resizeImageOutput(pathUrl:String,remoteRequest: RemoteRequest): ResponseBody
}