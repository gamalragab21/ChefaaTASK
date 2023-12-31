package net.gamal.chefaatask.ui.fragments.details

import android.app.Application
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import net.gamal.chefaatask.core.android.helpers.file.ImageFileUtils
import net.gamal.chefaatask.core.android.helpers.validation.ChefeaValidator.validateWidthAndHeightInput
import net.gamal.chefaatask.core.android.helpers.viewModel.AndroidBaseViewModel
import net.gamal.chefea.android.extentions.debug
import net.gamal.chefea.android.helpers.properties.ConfigurationKey
import net.gamal.chefea.android.helpers.properties.ConfigurationUtil
import net.gamal.chefea.core.common.data.model.Resource
import net.gamal.chefea.festures.commics.domain.interactors.GetComicByIdUC
import net.gamal.chefea.festures.commics.domain.models.ComicsItem
import net.gamal.chefea.festures.resize.data.model.ResizeRequest
import net.gamal.chefea.festures.resize.domain.interactors.UpdateComicsAndResizeImageUC
import javax.inject.Inject

@HiltViewModel
class DetailsComicsVM @Inject constructor(
    context: Application,
    private val resizeImageUC: UpdateComicsAndResizeImageUC,
    private val getComicByIdUC: GetComicByIdUC,
    private val configurationUtil: ConfigurationUtil,
    private val imageFileUtils: ImageFileUtils
) : AndroidBaseViewModel<DetailsComicsState>(context) {

    fun resizeImageWithValidation(comicsItem: ComicsItem, width: String, height: String) {
        val widthAndHeightValid = validateWidthAndHeightInput(width, height) {
            produce(DetailsComicsState.Validation(it))
        }
        if (widthAndHeightValid) resizeImage(
            comicsItem.id,
            buildResizeRequest(comicsItem, width.toFloat(), height.toFloat())
        )
    }

    private fun resizeImage(comicId: Int, resizeRequest: ResizeRequest) {

        produce(DetailsComicsState.Loading(true))
        resizeImageUC.invoke(
            viewModelScope, resizeRequest, multipleInvoke = true
        ) {
            debug("resizeImage: $it")

            when (it) {
                is Resource.Failure -> produce(
                    DetailsComicsState.Failure(it.exception)
                ).also {
                    produce(DetailsComicsState.Loading(false))
                }

                is Resource.Progress -> {}
                is Resource.Success -> getUpdateComic(comicId)
            }
        }
    }

    private fun getUpdateComic(comicId: Int) {
        getComicByIdUC.invoke(viewModelScope, comicId, multipleInvoke = true) {
            when (it) {
                is Resource.Failure -> produce(
                    DetailsComicsState.Failure(it.exception)
                ).also {
                    produce(DetailsComicsState.Loading(false))
                }

                is Resource.Progress -> {}
                is Resource.Success -> produce(DetailsComicsState.LoadComicOnScreen(it.model)).also {
                    produce(DetailsComicsState.Loading(false))

                }
            }
        }
    }

    private fun buildResizeRequest(
        comicsItem: ComicsItem,
        width: Float, height: Float
    ): ResizeRequest {
        return ResizeRequest(
            configurationUtil.getProperty(ConfigurationKey.TINY_API_KEY),
            imageFileUtils.saveBitmapToFile(comicsItem.thumbnail.imageBitmap!!),
            height, width, comicsItem
        )
    }

}