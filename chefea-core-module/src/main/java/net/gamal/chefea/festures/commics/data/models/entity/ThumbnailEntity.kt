package net.gamal.chefea.festures.commics.data.models.entity

import android.graphics.Bitmap
import android.graphics.BitmapFactory

data class ThumbnailEntity(
    var imageBytes: ByteArray,var path:String
) {
    fun getBitmap(): Bitmap {
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ThumbnailEntity) return false

        return imageBytes.contentEquals(other.imageBytes)
    }

    override fun hashCode(): Int {
        return imageBytes.contentHashCode()
    }
}
