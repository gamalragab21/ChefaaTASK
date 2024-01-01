package net.gamal.chefea.festures.resize.domain.interactors

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.gamal.chefea.android.extentions.debug
import net.gamal.chefea.android.helpers.file.deleteIfExists
import net.gamal.chefea.core.common.data.model.exception.LeonException
import net.gamal.chefea.core.common.domain.interactor.UseCaseRemoteThenCache
import net.gamal.chefea.festures.resize.data.model.ResizeRequest
import net.gamal.chefea.festures.resize.domain.repository.IResizeRepository
import java.io.File
import javax.inject.Inject

class UpdateComicsAndResizeImageUC @Inject constructor(private val repository: IResizeRepository) :
    UseCaseRemoteThenCache<File, ResizeRequest>() {

    override fun executeRemoteDS(body: ResizeRequest?): Flow<File> = flow {
        if (body == null || body.isInitialState())
            throw LeonException.Local.RequestValidation(ResizeRequest::class)

        body.validateRequestContract().also {
            debug("executeRemoteDS: body=$body")
            val output = repository.shrinkImageFile(body.comicsItem,body.remoteMap)
            emit(repository.resizeImage(output, body.remoteMap))
        }
    }

    override fun performLocalOperation(domain: File): Boolean = domain.exists()

    override suspend fun executeLocalOperation(domain: File, body: ResizeRequest?) {
        repository.updateCurrentComic(body!!.comicsItem, domain)
        domain.deleteIfExists()
        body.clear()
    }

}