package net.gamal.chefaatask.ui.fragments.home

import net.gamal.chefaatask.core.android.helpers.viewModel.ViewState
import net.gamal.chefea.core.common.data.model.exception.LeonException
import net.gamal.chefea.festures.commics.domain.models.ComicsItem

sealed class HomeComicsState : ViewState {
    data class Loading(val loading: Boolean) : HomeComicsState()
    data class Failure(val exception: LeonException) : HomeComicsState()
    data class AppendNewComicsList(val comicsList: List<ComicsItem>) :
        HomeComicsState()
}