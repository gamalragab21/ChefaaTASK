package net.gamal.chefea.festures.resize.domain.interactors

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
            val output = repository.shrinkImageFile(body.remoteMap)
            emit(repository.resizeImage(output, body.remoteMap))
        }
    }

    override fun performLocalOperation(domain: File): Boolean = domain.exists()

    override suspend fun executeLocalOperation(domain: File, body: ResizeRequest?) {
        repository.updateCurrentComic(body!!.comicsItem, domain)
        domain.delete()
        body.clear()
    }

}