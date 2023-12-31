package net.gamal.chefea.festures.resize.data.model

import net.gamal.chefea.android.extentions.createRequestBody
import net.gamal.chefea.core.common.data.consts.Constants
import net.gamal.chefea.core.common.domain.model.request.IRequestValidation
import net.gamal.chefea.core.common.domain.model.request.RemoteRequest
import net.gamal.chefea.core.common.domain.model.request.RequestContractType
import net.gamal.chefea.festures.commics.domain.models.ComicsItem
import java.io.File
import java.nio.charset.StandardCharsets
import java.util.Base64


data class ResizeRequest(
    private val apiKey: String,
    private val imageFile: File,
    private val height: Float,
    private val width: Float,
    val comicsItem: ComicsItem
) : IRequestValidation {

    override fun isInitialState(): Boolean = apiKey.isEmpty() || width == 0f || height == 0f

    override val remoteMap: RemoteRequest
        get() = RemoteRequest(
            requestHeaders = hashMapOf(
                Constants.AUTHORIZATION to getAuthorizationApiKey(apiKey),
            ),
            requestBody = hashMapOf(
                Constants.IMAGE_FILE to imageFile.createRequestBody(),
                Constants.IMAGE_FILE_URL to comicsItem.thumbnail.path,
                Constants.HEIGHT to height,
                Constants.WIDTH to width
            )
        )


    private fun getAuthorizationApiKey(apiKey: String): String {
        val authString = "api:$apiKey".toByteArray(StandardCharsets.UTF_8)
        return Constants.BASIC.plus(" ").plus(Base64.getEncoder().encodeToString(authString))
    }

    fun clear() {
        imageFile.delete()
    }

    override fun getRequestContracts(): HashMap<RequestContractType, HashMap<String, Boolean>> {
        return hashMapOf(
            RequestContractType.HEADERS to hashMapOf(
                Constants.AUTHORIZATION to true,
            ),
            RequestContractType.BODY to hashMapOf(
                Constants.IMAGE_FILE to true,
                Constants.WIDTH to true,
                Constants.IMAGE_FILE_URL to true,
                Constants.HEIGHT to true,
                Constants.IMAGE_OUTPUT to false
            )
        )
    }
}