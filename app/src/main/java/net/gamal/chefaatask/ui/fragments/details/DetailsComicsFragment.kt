package net.gamal.chefaatask.ui.fragments.details

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
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
import net.gamal.chefea.festures.commics.domain.models.Thumbnail
import java.util.Random

@AndroidEntryPoint
class DetailsComicsFragment : BaseFragment<FragmentDetailsBinding>() {

    private val viewModel: DetailsComicsVM by viewModels()
    private val args: DetailsComicsFragmentArgs by navArgs()

    private var imageUriSelected: Uri? = null

    private val changeImage =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                imageUriSelected = data?.data
                binding.comicImage.setImageURI(imageUriSelected!!)
            }
        }

    override fun onFragmentReady() {
        setToolBarConfigs(
            if (args.comicItem !=null) R.string.comic_details else R.string.add_comic, true
        )
        onBackClicked { findNavController().popBackStack() }
        args.comicItem?.let { loadData(it) }
        setActions()
    }

    private fun setActions() = binding.apply {
        saveBtn.setOnClickListener {
            detailLayoutWidth.error = null
            detailLayoutHeight.error = null
            val title = binding.detailsEtCaption.text.toString()
                .ifEmpty { getString(R.string.no_caption) }
            val comicItem = args.comicItem.apply { this?.title = title } ?: ComicsItem(
                Random().nextInt(1000),
                Thumbnail("", null), title = title
            )
            viewModel.resizeImageWithValidation(
                comicItem,
                detailsEtWidth.text.toString(),
                detailsEtHeight.text.toString(), imageUriSelected
            )
        }
        comicImage.setOnClickListener {
            openGallery()
        }
    }

    private fun openGallery() {
        val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        changeImage.launch(pickImg)
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


    private fun getDominantColor(bitmap: Bitmap, listener: (Int) -> Unit) {
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

            DetailsComicsState.TaskAreEnqueuedSuccess -> showSnackBar("Your Task Enqueued successful, we will invoke it after connection back")
        }
    }

    private fun validateForm(filed: InputFieldError) {
        when (filed.key) {
            InputFiledType.WIDTH -> binding.detailLayoutWidth.error =
                filed.getErrorMessage(requireContext())

            InputFiledType.HEIGHT -> binding.detailLayoutHeight.error =
                filed.getErrorMessage(requireContext())

            InputFiledType.IMAGE -> showSnackBar(filed.getErrorMessage(requireContext()))

            else -> {}
        }
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }

    override fun onRetryCurrentAction(currentAction: CurrentAction?, message: String) {
        showSnackBar(message) { onRefreshView() }
    }
}