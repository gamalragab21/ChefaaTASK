package net.gamal.chefaatask.ui.fragments.details

import android.app.Application
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.PersistableBundle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import net.gamal.chefaatask.core.android.helpers.connectivity.InternetConnectivity
import net.gamal.chefaatask.core.android.helpers.validation.ChefeaValidator.validateWidthAndHeightInput
import net.gamal.chefaatask.core.android.helpers.validation.InputFieldError
import net.gamal.chefaatask.core.android.helpers.validation.InputFiledType
import net.gamal.chefaatask.core.android.helpers.viewModel.AndroidBaseViewModel
import net.gamal.chefaatask.core.base.AppConstants
import net.gamal.chefaatask.core.workers.resizeImage.data.WorkerResult
import net.gamal.chefaatask.core.workers.resizeImage.domain.ResizeImageService
import net.gamal.chefea.android.extentions.registerReceiverNotExported
import net.gamal.chefea.android.extentions.toJson
import net.gamal.chefea.android.extentions.toLeonException
import net.gamal.chefea.android.helpers.file.ImageFileUtils
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
    private val context: Application,
    private val resizeImageUC: UpdateComicsAndResizeImageUC,
    private val getComicByIdUC: GetComicByIdUC,
    private val configurationUtil: ConfigurationUtil,
    private val imageFileUtils: ImageFileUtils
) : AndroidBaseViewModel<DetailsComicsState>(context) {

    private val resizeImageWorkerBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == AppConstants.RESIZE_IMAGE_WORKER_ACTION) {
                val workerResult =
                    WorkerResult.find(
                        intent.getIntExtra(AppConstants.RESIZE_RESULT, 0)
                    )
                when (workerResult) {
                    WorkerResult.SUCCESS -> getUpdateComic(
                        intent.getIntExtra(
                            AppConstants.COMIC_ID,
                            -1
                        )
                    )

                    WorkerResult.FAILED -> {
                        val exception = intent.getStringExtra(AppConstants.RESIZE_IMAGE_EXCEPTION)!!
                            .toLeonException()
                        produce(
                            DetailsComicsState.Failure(exception)
                        )
                    }

                    WorkerResult.SHOW_LOADING -> produce(DetailsComicsState.Loading(true))

                    WorkerResult.DISMISS_LOADING -> produce(DetailsComicsState.Loading(false))
                }
            }
        }
    }

    fun resizeImageWithValidation(
        comicsItem: ComicsItem,
        width: String, height: String,
        imageUriSelected: Uri?
    ) {
        val widthAndHeightValid = validateWidthAndHeightInput(width, height) {
            produce(DetailsComicsState.Validation(it))
        }
        val imageFileResultValid =
            !comicsItem.thumbnail.inInitialState() || imageUriSelected != null
        if (!imageFileResultValid) produce(
            DetailsComicsState.Validation(
                InputFieldError(
                    InputFiledType.IMAGE, "Please selected image firstly"
                )
            )
        )

        if (widthAndHeightValid && imageFileResultValid)
            startResizeImageWorker(
                comicsItem.id,
                buildResizeRequest(comicsItem, imageUriSelected, width.toFloat(), height.toFloat())
            )
    }

    private fun startResizeImageWorker(id: Int, resizeRequest: ResizeRequest) {

        if (!InternetConnectivity.isConnected())
            produce(DetailsComicsState.TaskAreEnqueuedSuccess)
        val jobScheduler =
            context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.cancel(id)
        val jobInfo = JobInfo.Builder(
            id, ComponentName(context, ResizeImageService::class.java)
        )
        val job =
            jobInfo.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setExtras(PersistableBundle().apply {
                    putString(AppConstants.RESIZE_REQUEST, resizeRequest.toJson())
                }
                )
                .build()
        jobScheduler.schedule(job)
    }


    private fun getUpdateComic(comicId: Int) {
        getComicByIdUC.invoke(viewModelScope, comicId) {
            when (it) {
                is Resource.Failure -> produce(
                    DetailsComicsState.Failure(it.exception)
                )

                is Resource.Progress -> produce(DetailsComicsState.Loading(it.loading))
                is Resource.Success -> produce(DetailsComicsState.LoadComicOnScreen(it.model))
            }
        }
    }


    private fun buildResizeRequest(
        comicsItem: ComicsItem,
        imageUriSelected: Uri?, width: Float, height: Float
    ): ResizeRequest {
        /**
         * we are check if we have a local image from gallery also we are will upload image file to server and remove the old path of image url in server
         * if we not have a local image so we will completed with a path url on server
         */
        val selectedFile = if (imageUriSelected != null) {
            comicsItem.thumbnail.path = ""
            imageFileUtils.createFileFromUri(context, imageUriSelected)
        } else imageFileUtils.saveBitmapToFile(comicsItem.thumbnail.imageBitmap!!)

        return ResizeRequest(
            configurationUtil.getProperty(ConfigurationKey.TINY_API_KEY),
            selectedFile.absolutePath, height, width, comicsItem
        )
    }

    private fun registerBrodCast() {
        context.registerReceiverNotExported(
            resizeImageWorkerBroadcastReceiver,
            IntentFilter(AppConstants.RESIZE_IMAGE_WORKER_ACTION)
        )
    }

    fun clear() {
        context.unregisterReceiver(resizeImageWorkerBroadcastReceiver)
    }

    init {
        registerBrodCast()
    }

}