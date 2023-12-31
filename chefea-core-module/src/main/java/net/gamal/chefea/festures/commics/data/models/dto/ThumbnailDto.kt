package net.gamal.chefea.festures.commics.data.models.dto


import com.google.gson.annotations.SerializedName

internal data class ThumbnailDto(
    @SerializedName("extension")
    private var extension: String,
    @SerializedName("path")
    private var path: String
) {
    fun getCompletePath() = "$path.$extension".replaceHttpWithHttps()

    private fun String.replaceHttpWithHttps(): String =
        if (startsWith("http://")) "https${substring(4)}"
        else this
}