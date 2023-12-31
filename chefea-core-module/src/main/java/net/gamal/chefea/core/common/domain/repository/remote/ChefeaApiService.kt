package net.gamal.chefea.core.common.domain.repository.remote

import net.gamal.chefea.core.common.data.consts.Constants
import net.gamal.chefea.festures.commics.data.models.dto.ComicsResponseDto
import net.gamal.chefea.festures.resize.data.model.dto.TinfyResponseDto
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap

internal interface ChefeaApiService {
    @GET("public/comics")
    suspend fun getComics(@QueryMap hashMap: HashMap<String, Any>): ComicsResponseDto

    @POST("https://api.tinify.com/shrink")
    suspend fun shrinkLocalFile(
        @Body file: RequestBody,
        @Header(Constants.AUTHORIZATION) authorization: String
    ): TinfyResponseDto

    @POST("https://api.tinify.com/shrink")
    suspend fun shrinkUrlFile(
        @Body body: HashMap<String, Any>,
        @Header(Constants.AUTHORIZATION) authorization: String
    ): TinfyResponseDto

    @POST("{pathUrl}")
    @JvmSuppressWildcards
    suspend fun resieImage(
        @Header(Constants.AUTHORIZATION) authorization: String,
        @Path(value = "pathUrl", encoded = true) pathUrl: String,
        @Body body: HashMap<String, Any>
    ): ResponseBody

}