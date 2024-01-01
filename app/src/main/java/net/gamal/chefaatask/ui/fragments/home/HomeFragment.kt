package net.gamal.chefaatask.ui.fragments.home

import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import net.gamal.chefaatask.R
import net.gamal.chefaatask.core.android.extension.navigateSafe
import net.gamal.chefaatask.core.android.extension.observe
import net.gamal.chefaatask.core.android.extension.showSnackBar
import net.gamal.chefaatask.core.android.helpers.MobileUtils
import net.gamal.chefaatask.core.android.helpers.viewModel.CurrentAction
import net.gamal.chefaatask.core.base.BaseFragment
import net.gamal.chefaatask.databinding.FragmentHomeBinding
import net.gamal.chefea.festures.commics.domain.models.ComicsItem
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeComicsVM by viewModels()

    @Inject
    lateinit var comicsAdapter: ComicsAdapter

    override fun onFragmentReady() {
        setToolBarConfigs(R.string.comics_trending)
        onSwipeRefreshStatus(true)
        setupComicsRecyclerView()
        onRefreshView()
        setActions()
    }

    private fun setActions() {
        comicsAdapter.setOnItemClickListener { comicsItem, view ->
            if (view == null) navigateSafe(
                HomeFragmentDirections.actionHomeFragmentToDetailsComicsFragment(
                    comicsItem
                )
            )
            else showPopupMenu(view, comicsItem)
        }

        binding.addingComic.setOnClickListener {
            navigateSafe(
                HomeFragmentDirections.actionHomeFragmentToDetailsComicsFragment(null)
            )
        }
    }

    private fun showPopupMenu(view: View, comicsItem: ComicsItem) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.inflate(R.menu.image_menu)
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.action_share -> MobileUtils.shareImage(
                    requireContext(),
                    comicsItem.thumbnail.imageBitmap!!
                )

                R.id.action_save -> MobileUtils.saveImage(
                    comicsItem.thumbnail.imageBitmap!!
                ).let {
                    if (it) showSnackBar("Saved Success..")
                }
            }
            true
        }
        popupMenu.show()
    }

    override fun subscribeToObservables() {
        viewModel.apply {
            observe(viewState) {
                when (it) {
                    is HomeComicsState.AppendNewComicsList -> comicsAdapter.comicsItems =
                        it.comicsList

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

    private fun setupComicsRecyclerView() = binding.topDealsRecyclerView.apply {
        itemAnimator = null
        isNestedScrollingEnabled = true
        setHasFixedSize(true)
        layoutManager = StaggeredGridLayoutManager(3, RecyclerView.VERTICAL)
        adapter = comicsAdapter
    }

    override fun onRefreshView() {
        viewModel.getUserComicsByIndex()
    }

    override fun onDestroyView() {
        comicsAdapter.comicsItems = emptyList()
        super.onDestroyView()
    }

    override fun onRetryCurrentAction(currentAction: CurrentAction?, message: String) {
        showSnackBar(message) { onRefreshView() }
    }
}