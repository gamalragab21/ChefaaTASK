package net.gamal.chefea.festures.commics.domain.interactors

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.gamal.chefea.core.common.data.model.exception.LeonException
import net.gamal.chefea.core.common.domain.interactor.UseCaseLocalThenRemoteThenCache
import net.gamal.chefea.festures.commics.data.models.ComicsRequest
import net.gamal.chefea.festures.commics.domain.models.ComicsItem
import net.gamal.chefea.festures.commics.domain.repository.IComicsRepository
import javax.inject.Inject

class FetchUserComicsUC @Inject constructor(private val repository: IComicsRepository) :
    UseCaseLocalThenRemoteThenCache<List<ComicsItem>, ComicsRequest>() {

    override fun executeLocalDS(body: ComicsRequest?): Flow<List<ComicsItem>> = flow {
        emit(repository.getComicsLocal())
    }

    override fun performRemoteOperation(domain: List<ComicsItem>?): Boolean =
        domain?.isEmpty() == true

    override fun executeRemoteDS(body: ComicsRequest?): Flow<List<ComicsItem>> = flow {
        if (body == null || body.isInitialState())
            throw LeonException.Local.RequestValidation(ComicsRequest::class)
        body.validateRequestContract().also {
            emit(repository.getComicsRemote(body.remoteMap).comicsData.results)
        }
    }

    override fun performLocalOperation(domain: List<ComicsItem>): Boolean = domain.isNotEmpty()

    override suspend fun executeLocalOperation(domain: List<ComicsItem>, body: ComicsRequest?) {
        repository.saveComicsList(domain)
    }
}