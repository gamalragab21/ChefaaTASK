package net.gamal.chefea.festures.commics.domain.interactors

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.gamal.chefea.android.common.data.model.exception.LeonException
import net.gamal.chefea.android.common.domain.interactor.UseCaseRemoteThenCache
import net.gamal.chefea.festures.commics.data.models.ComicsRequest
import net.gamal.chefea.festures.commics.domain.models.Comics
import net.gamal.chefea.festures.commics.domain.repository.IComicsRepository
import javax.inject.Inject

class FetchComicsUC @Inject constructor(private val repository: IComicsRepository) :
    UseCaseRemoteThenCache<Comics, ComicsRequest>() {

    override fun executeRemoteDS(body: ComicsRequest?): Flow<Comics> = flow {
        if (body == null || body.isInitialState())
            throw LeonException.Local.RequestValidation(ComicsRequest::class)
        body.validateRequestContract().also {
            emit(repository.getComicsRemote(body.remoteMap))
        }
    }

    override fun performLocalOperation(domain: Comics): Boolean = domain.isInInitialState().not()

    override suspend fun executeLocalOperation(domain: Comics, body: ComicsRequest?) {
        // TODO: invoke local ...
    }
}