package net.gamal.chefea.festures.resize.data.repository.remote

import net.gamal.chefea.android.extentions.debug
import net.gamal.chefea.core.common.data.consts.Constants
import net.gamal.chefea.core.common.domain.model.request.RemoteRequest
import net.gamal.chefea.core.common.domain.repository.remote.ChefeaApiService
import net.gamal.chefea.festures.resize.data.model.dto.TinfyResponseDto
import net.gamal.chefea.festures.resize.domain.repository.remote.IResizeRemoteDs
import okhttp3.RequestBody
import okhttp3.ResponseBody
import javax.inject.Inject

internal class ResizeRemoteDs @Inject constructor(private val provider: ChefeaApiService) :
    IResizeRemoteDs {
    override suspend fun shrinkImageLocalFile(remoteRequest: RemoteRequest): TinfyResponseDto {
        val imageFile = remoteRequest.requestBody[Constants.IMAGE_FILE] as RequestBody
        val authorization = remoteRequest.requestHeaders[Constants.AUTHORIZATION] as String
        debug("shrinkImageLocalFile: imageFile=$imageFile ")
        return provider.shrinkLocalFile(file = imageFile, authorization = authorization)
    }

    override suspend fun shrinkImageUrlFile(remoteRequest: RemoteRequest): TinfyResponseDto {
        val authorization = remoteRequest.requestHeaders[Constants.AUTHORIZATION] as String
        val imageFileUrl = remoteRequest.requestBody[Constants.IMAGE_FILE_URL] as String

        return provider.shrinkUrlFile(
            body = hashMapOf(
                Constants.SOURCE to hashMapOf(
                    Constants.URL to imageFileUrl,
                )
            ), authorization = authorization
        )
    }

    override suspend fun resizeImageOutput(
        pathUrl: String, remoteRequest: RemoteRequest
    ): ResponseBody {
        val width = remoteRequest.requestBody[Constants.WIDTH] as Float
        val height = remoteRequest.requestBody[Constants.HEIGHT] as Float
        val authorization = remoteRequest.requestHeaders[Constants.AUTHORIZATION] as String

        return provider.resieImage(
            authorization = authorization,
            pathUrl, hashMapOf(
                Constants.RESIZE to hashMapOf(
                    Constants.WIDTH to width,
                    Constants.HEIGHT to height,
                    Constants.METHOD to "fit"
                )
            )
        )
    }
}