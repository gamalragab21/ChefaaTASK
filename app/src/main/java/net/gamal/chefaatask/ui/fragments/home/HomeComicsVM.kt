package net.gamal.chefaatask.ui.fragments.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import net.gamal.chefaatask.core.android.helpers.viewModel.AndroidBaseViewModel
import net.gamal.chefea.android.extentions.info
import net.gamal.chefea.android.helpers.properties.ConfigurationKey
import net.gamal.chefea.android.helpers.properties.ConfigurationUtil
import net.gamal.chefea.core.common.data.model.Resource
import net.gamal.chefea.festures.commics.data.models.ComicsRequest
import net.gamal.chefea.festures.commics.domain.interactors.FetchLocalComicsUC
import net.gamal.chefea.festures.commics.domain.interactors.FetchUserComicsUC
import javax.inject.Inject

@HiltViewModel
class HomeComicsVM @Inject constructor(
    context: Application,
    private val fetchRemoteComicsUC: FetchUserComicsUC,
    private val fetchLocalComicsUC: FetchLocalComicsUC,
    private val configurationUtil: ConfigurationUtil
) : AndroidBaseViewModel<HomeComicsState>(context) {

    fun getUserComicsByIndex() {
        fetchRemoteComicsUC.invoke(viewModelScope, buildComicsRequest()) {
            info("getComicsByIndex: $it")
            when (it) {
                is Resource.Failure -> produce(
                    HomeComicsState.Failure(it.exception)
                )

                is Resource.Progress -> produce(HomeComicsState.Loading(it.loading))
                is Resource.Success -> produce(HomeComicsState.AppendNewComicsList(it.model))
            }
        }
    }

    private fun getLocalComicsByIndex() {
        fetchLocalComicsUC.invoke(viewModelScope) {
            info("getComicsByIndex: $it")
            when (it) {
                is Resource.Failure -> produce(
                    HomeComicsState.Failure(it.exception)
                )

                is Resource.Progress -> produce(HomeComicsState.Loading(it.loading))
                is Resource.Success -> produce(HomeComicsState.AppendNewComicsList(it.model))
            }
        }
    }

    private fun buildComicsRequest() = ComicsRequest(
        System.currentTimeMillis().toString(),
        configurationUtil.getProperty(ConfigurationKey.PUBLIC_API_KEY),
        configurationUtil.getProperty(ConfigurationKey.PRIVATE_API_KEY)
    )
}