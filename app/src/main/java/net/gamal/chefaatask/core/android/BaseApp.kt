package net.gamal.chefaatask.core.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}


/*
import com.google.gson.annotations.SerializedName

data class TinifyResponse(
    @SerializedName("output") val output: Output
)

data class Output(
    @SerializedName("url") val url: String
)

 */