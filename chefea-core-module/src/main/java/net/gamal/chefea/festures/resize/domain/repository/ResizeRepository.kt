package net.gamal.chefea.festures.resize.domain.repository

import net.gamal.chefea.core.common.domain.model.request.RemoteRequest
import net.gamal.chefea.festures.commics.domain.models.ComicsItem
import net.gamal.chefea.festures.resize.domain.model.TinfyResponse
import java.io.File

interface IResizeRepository {
    suspend fun shrinkImageFile(comicsItem: ComicsItem, remoteRequest: RemoteRequest): TinfyResponse
    suspend fun resizeImage(output: TinfyResponse, remoteMap: RemoteRequest): File
    suspend fun updateCurrentComic(comicsItem: ComicsItem, domain: File)
}