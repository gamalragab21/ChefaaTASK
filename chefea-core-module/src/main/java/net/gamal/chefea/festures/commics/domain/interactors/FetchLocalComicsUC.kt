package net.gamal.chefea.festures.commics.domain.interactors

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.gamal.chefea.core.common.domain.interactor.UseCaseLocal
import net.gamal.chefea.festures.commics.domain.models.ComicsItem
import net.gamal.chefea.festures.commics.domain.repository.IComicsRepository
import javax.inject.Inject

class FetchLocalComicsUC @Inject constructor(private val repository: IComicsRepository) :
    UseCaseLocal<List<ComicsItem>, Unit>() {
    override fun executeLocalDS(body: Unit?): Flow<List<ComicsItem>> = flow {
        emit(repository.getComicsLocal())
    }
}