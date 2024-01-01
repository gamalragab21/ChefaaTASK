package net.gamal.chefaatask.core.workers.resizeImage.domain

import android.annotation.SuppressLint
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import net.gamal.chefaatask.core.android.notification.NotificationUtils
import net.gamal.chefaatask.core.base.AppConstants
import net.gamal.chefaatask.core.workers.resizeImage.data.WorkerResult
import net.gamal.chefea.android.extentions.debug
import net.gamal.chefea.android.extentions.getModelFromJSON
import net.gamal.chefea.android.extentions.info
import net.gamal.chefea.android.extentions.toJsonLeonException
import net.gamal.chefea.core.common.data.model.Resource
import net.gamal.chefea.core.common.data.model.exception.LeonException
import net.gamal.chefea.festures.resize.data.model.ResizeRequest
import net.gamal.chefea.festures.resize.domain.interactors.UpdateComicsAndResizeImageUC
import javax.inject.Inject

@SuppressLint("SpecifyJobSchedulerIdRange")
@AndroidEntryPoint
class ResizeImageService : JobService() {
    @Inject
    lateinit var resizeImageUC: UpdateComicsAndResizeImageUC

    @Inject
    lateinit var notificationUtils: NotificationUtils

    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Default + job)


    private var params: JobParameters? = null
    private lateinit var resizeRequest: ResizeRequest

    override fun onStartJob(p0: JobParameters?): Boolean {
        this.params = p0
        params?.extras?.let {
            resizeRequest = it.getString(AppConstants.RESIZE_REQUEST, "")
                .getModelFromJSON(ResizeRequest::class.java)
        }

        showNotification()
        resizeImage(resizeRequest)
        return true
    }

    private fun resizeImage(resizeRequest: ResizeRequest) {
        resizeImageUC.invoke(coroutineScope, resizeRequest) {
            debug("resizeImage: $it")
            when (it) {
                is Resource.Failure -> {
                    info("Failed to resize image")
                    jobFinished(params, false)
                }

                is Resource.Progress -> setFinalResult(if (it.loading) WorkerResult.SHOW_LOADING else WorkerResult.DISMISS_LOADING)

                is Resource.Success -> {
                    setFinalResult(WorkerResult.SUCCESS)
                    info("Image resizing successfully")
                    jobFinished(params, false)
                }
            }
        }
    }

    private fun showNotification() {
        val notification = notificationUtils.showNotification(
            "Update Comics", "uploading in progress"
        )
        startForeground(
            resizeRequest.comicsItem.id, notification
        )
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        debug("SessionExpiryTimerService:onStopJob")
        job.cancel()
        return false
    }

    private fun setFinalResult(workerResult: WorkerResult, exception: LeonException? = null) {
        val intent = Intent(AppConstants.RESIZE_IMAGE_WORKER_ACTION).apply {
            exception?.let {
                putExtra(AppConstants.RESIZE_IMAGE_EXCEPTION, exception.toJsonLeonException())
            }
            putExtra(AppConstants.RESIZE_RESULT, workerResult.value)
            putExtra(AppConstants.COMIC_ID, resizeRequest.comicsItem.id)
        }
        sendBroadcast(intent)
    }
}