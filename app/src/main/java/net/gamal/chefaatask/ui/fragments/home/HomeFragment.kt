package net.gamal.chefaatask.ui.fragments.home

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import net.gamal.chefaatask.core.android.extension.observe
import net.gamal.chefaatask.core.android.helpers.viewModel.CurrentAction
import net.gamal.chefaatask.core.base.BaseFragment
import net.gamal.chefaatask.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeComicsVM by viewModels()

    override fun onFragmentReady() {
        onSwipeRefreshStatus(true)
        onRefreshView()
    }

    override fun subscribeToObservables() {
        viewModel.apply {
            observe(viewState) {
                when (it) {
                    is HomeComicsState.AppendNewComicsList -> net.gamal.chefea.android.extentions.info(
                        "AppendNewComicsList: ${it.comicsList}"
                    )

                    is HomeComicsState.Failure -> {
                        net.gamal.chefea.android.extentions.error(
                            "AppendNewComicsList: ${it.exception}"
                        )
                        handleHttpsStatusCode(it.exception)
                    }

                    is HomeComicsState.Loading -> {
                        showProgress(it.loading)
                        if (!it.loading) stopSwipeRefreshLoading()
                    }
                }
            }
        }
    }

    override fun onRefreshView() {
        viewModel.getTransactionsByIndex()
    }

    override fun onRetryCurrentAction(currentAction: CurrentAction?, message: String) {
    }
}