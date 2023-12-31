package net.gamal.chefaatask.ui.fragments.details

import android.graphics.Bitmap
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import dagger.hilt.android.AndroidEntryPoint
import net.gamal.chefaatask.R
import net.gamal.chefaatask.core.android.extension.observe
import net.gamal.chefaatask.core.android.extension.onBackClicked
import net.gamal.chefaatask.core.android.extension.showSnackBar
import net.gamal.chefaatask.core.android.helpers.validation.InputFieldError
import net.gamal.chefaatask.core.android.helpers.validation.InputFiledType
import net.gamal.chefaatask.core.android.helpers.validation.getErrorMessage
import net.gamal.chefaatask.core.android.helpers.viewModel.CurrentAction
import net.gamal.chefaatask.core.base.BaseFragment
import net.gamal.chefaatask.databinding.FragmentDetailsBinding
import net.gamal.chefea.festures.commics.domain.models.ComicsItem

@AndroidEntryPoint
class DetailsComicsFragment : BaseFragment<FragmentDetailsBinding>() {

    private val viewModel: DetailsComicsVM by viewModels()
    private val args: DetailsComicsFragmentArgs by navArgs()

    override fun onFragmentReady() {
        setToolBarConfigs(R.string.comic_details, true)
        onBackClicked { findNavController().popBackStack() }
        loadData(args.comicItem)
        setActions()
    }

    private fun setActions() = binding.apply {
        saveBtn.setOnClickListener {
            detailLayoutWidth.error = null
            detailLayoutHeight.error = null
            viewModel.resizeImageWithValidation(
                args.comicItem.apply {
                    title = binding.detailsEtCaption.text.toString()
                        .ifEmpty { getString(R.string.no_caption) }
                },
                detailsEtWidth.text.toString(),
                detailsEtHeight.text.toString()
            )
        }
    }

    private fun loadData(comicItem: ComicsItem) = binding.apply {
        comicImage.setImageBitmap(comicItem.thumbnail.imageBitmap!!)
        comicsId.text = "#${comicItem.id}"
        detailsEtCaption.setText(comicItem.title.ifEmpty {
            getString(R.string.no_caption)
        })

        getDominantColor(comicItem.thumbnail.imageBitmap!!) { dominantColor ->
            root.setBackgroundColor(dominantColor)
        }
    }


    fun getDominantColor(bitmap: Bitmap, listener: (Int) -> Unit) {
        Palette.from(bitmap).generate { palette ->
            val dominantColor = palette?.dominantSwatch?.rgb ?: 0
            listener.invoke(dominantColor)
        }
    }

    override fun subscribeToObservables() {
        viewModel.apply {
            observe(viewState) {
                handleViewState(it)
            }
        }
    }

    private fun handleViewState(state: DetailsComicsState) {
        when (state) {
            is DetailsComicsState.Failure -> handleHttpsStatusCode(state.exception)
            is DetailsComicsState.Loading -> showProgress(state.loading)
            is DetailsComicsState.Validation -> validateForm(state.inputFieldError)
            is DetailsComicsState.LoadComicOnScreen -> {
                showSnackBar(R.string.comic_update_successfully)
                loadData(state.model)
            }
        }
    }

    private fun validateForm(filed: InputFieldError) {
        when (filed.key) {
            InputFiledType.WIDTH -> binding.detailLayoutWidth.error =
                filed.getErrorMessage(requireContext())

            InputFiledType.HEIGHT -> binding.detailLayoutHeight.error =
                filed.getErrorMessage(requireContext())

            else -> {}
        }
    }

    override fun onRetryCurrentAction(currentAction: CurrentAction?, message: String) {
        showSnackBar(message) { onRefreshView() }
    }
}