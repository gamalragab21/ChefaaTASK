package net.gamal.chefea.android.common.domain.repository.remote

import net.gamal.chefea.festures.commics.data.models.dto.ComicsResponseDto
import retrofit2.http.GET
import retrofit2.http.QueryMap

internal interface ChefeaApiService {
    @GET("public/comics")
    suspend fun getComics(@QueryMap hashMap: HashMap<String, Any>): ComicsResponseDto
}