package net.gamal.chefea.festures.commics.domain.interactors

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.gamal.chefea.core.common.data.model.exception.LeonException
import net.gamal.chefea.core.common.domain.interactor.UseCaseLocal
import net.gamal.chefea.festures.commics.domain.models.ComicsItem
import net.gamal.chefea.festures.commics.domain.repository.IComicsRepository
import net.gamal.chefea.festures.resize.data.model.ResizeRequest
import javax.inject.Inject

class GetComicByIdUC @Inject constructor(private val repository: IComicsRepository) :
    UseCaseLocal<ComicsItem, Int>() {
    override fun executeLocalDS(body: Int?): Flow<ComicsItem> = flow {
        if (body == null)
            throw LeonException.Local.RequestValidation(ResizeRequest::class)
        emit(repository.getComicById(body))
    }
}