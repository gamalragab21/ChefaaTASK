package net.gamal.chefea.festures.resize.data.repository

import android.content.Context
import net.gamal.chefaatask.core.android.helpers.file.ImageFileUtils
import net.gamal.chefea.core.common.domain.model.request.RemoteRequest
import net.gamal.chefea.festures.commics.data.mapper.ComicsItemMapper
import net.gamal.chefea.festures.commics.domain.models.ComicsItem
import net.gamal.chefea.festures.resize.data.mapper.TinfyResponseMapper
import net.gamal.chefea.festures.resize.domain.model.TinfyResponse
import net.gamal.chefea.festures.resize.domain.repository.IResizeRepository
import net.gamal.chefea.festures.resize.domain.repository.local.IResizeLocalDs
import net.gamal.chefea.festures.resize.domain.repository.remote.IResizeRemoteDs
import java.io.File
import javax.inject.Inject

internal class ResizeRepository @Inject constructor(
    private val context: Context,
    private val remoteDs: IResizeRemoteDs,
    private val localDs: IResizeLocalDs,
    private val imageFileUtils: ImageFileUtils
) : IResizeRepository {
    override suspend fun shrinkImageFile(remoteRequest: RemoteRequest): TinfyResponse {
        val result = remoteDs.shrinkImageUrlFile(remoteRequest)
        return TinfyResponseMapper.dtoToDomain(result)
    }

    override suspend fun resizeImage(output: TinfyResponse, remoteMap: RemoteRequest): File {
        val response = remoteDs.resizeImageOutput(output.output.url, remoteMap)
        return imageFileUtils.saveImageToFile(response)
    }

    override suspend fun updateCurrentComic(comicsItem: ComicsItem, domain: File) {
        comicsItem.thumbnail.apply {
            setImageAsBitmap(domain.path,context)
        }
        val entity = ComicsItemMapper.domainToEntity(comicsItem)
        localDs.updateComic(entity)
    }
}