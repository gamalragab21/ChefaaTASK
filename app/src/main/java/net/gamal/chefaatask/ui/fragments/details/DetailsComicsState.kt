package net.gamal.chefaatask.ui.fragments.details

import net.gamal.chefaatask.core.android.helpers.validation.InputFieldError
import net.gamal.chefaatask.core.android.helpers.viewModel.ViewState
import net.gamal.chefea.core.common.data.model.exception.LeonException
import net.gamal.chefea.festures.commics.domain.models.ComicsItem

sealed class DetailsComicsState : ViewState {
    data class Loading(val loading: Boolean) : DetailsComicsState()
    data class Failure(val exception: LeonException) : DetailsComicsState()

    data class Validation(val inputFieldError: InputFieldError) : DetailsComicsState()
    data class LoadComicOnScreen(val model: ComicsItem) : DetailsComicsState()
    data object TaskAreEnqueuedSuccess : DetailsComicsState()
}