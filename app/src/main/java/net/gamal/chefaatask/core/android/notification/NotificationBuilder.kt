package net.sbs.softpos.finpay.core.android.notification

import android.app.PendingIntent
import android.content.Context
import javax.inject.Inject

class NotificationBuilder {
    class Builder @Inject constructor(val context: Context) {
        internal var imageRes: Int? = null
        internal var colorRes: Int? = null
        internal var channelId: String? = null
        internal var channelName: String? = null
        internal var channelDescription: String? = null
        internal var pendingIntent: PendingIntent? = null

        fun setChannelId(channelId: String): Builder {
            this.channelId = channelId
            return this
        }

        fun setChannelName(channelName: String): Builder {
            this.channelName = channelName
            return this
        }

        fun setChannelDescription(channelDescription: String): Builder {
            this.channelDescription = channelDescription
            return this
        }

        fun setPendingIntent(pendingIntent: PendingIntent): Builder {
            this.pendingIntent = pendingIntent
            return this
        }

        fun setImageRes(imageRes: Int): Builder {
            this.imageRes = imageRes
            return this
        }

        fun setColorRes(colorRes: Int): Builder {
            this.colorRes = colorRes
            return this
        }

        fun build(): NotificationUtils {
            if (channelId == null) throw Exception(
                TAG, Throwable("channelId notification is require to build notification")
            )
            if (channelName == null) throw Exception(
                TAG, Throwable("channelName notification is require to build notification")
            )
            if (channelDescription == null) throw Exception(
                TAG, Throwable("channelDescription notification is require to build notification")
            )
            if (pendingIntent == null) throw Exception(
                TAG, Throwable("pendingIntent notification is require to build notification")
            )
            if (colorRes == null) throw Exception(
                TAG, Throwable("Color notification resource is require to build notification")
            )
            if (imageRes == null) throw Exception(
                TAG, Throwable("Image notification resource is require to build notification")
            )
            return NotificationUtils(this)
        }
    }

    companion object {
        const val TAG: String = "NotificationBuilder"
    }
}