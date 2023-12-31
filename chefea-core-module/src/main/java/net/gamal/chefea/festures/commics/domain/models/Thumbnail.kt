package net.gamal.chefea.festures.commics.domain.models

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Parcelable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.parcelize.Parcelize
import java.io.ByteArrayOutputStream
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Parcelize
data class Thumbnail(
    var path: String, var imageBitmap: Bitmap? = null
) : Parcelable {

    suspend fun setImageAsBitmap(path: String, context: Context) =
        suspendCoroutine { continuation ->
            Glide.with(context).asBitmap()
                .load(path)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap, transition: Transition<in Bitmap>?
                    ) {
                        imageBitmap = resource
                        continuation.resume(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
        }

    fun encodeToByteArray(): ByteArray {
        val stream = ByteArrayOutputStream()
        imageBitmap!!.compress(getCompressFormat(), 100, stream)
        return stream.toByteArray()
    }

    private fun getCompressFormat() = when (path.substringAfter(".")) {
        "jpg" -> Bitmap.CompressFormat.JPEG
        else -> Bitmap.CompressFormat.PNG
    }
}