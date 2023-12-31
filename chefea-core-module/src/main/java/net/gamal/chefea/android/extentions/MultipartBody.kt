package net.gamal.chefea.android.extentions

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


fun File.createRequestBody(type: String = "image/*"): RequestBody {
    return asRequestBody(type.toMediaTypeOrNull())
}

fun File.toMultipartBody(): MultipartBody.Part {
    val requestFile = this.createRequestBody("multipart/form-data")
    return MultipartBody.Part.createFormData("image", this.name, requestFile)
}