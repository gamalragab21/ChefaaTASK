package net.gamal.chefaatask.core.android.helpers.file

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject


class ImageFileUtils @Inject constructor(private val context: Context) {

    private val timeStamp by lazy {
        SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    }

    fun saveBitmapToFile(bitmap: Bitmap): File {
        val imageFileName = "JPEG_$timeStamp.jpg"
        val storageDir: File = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        val imageFile = File.createTempFile(
            imageFileName,
            ".jpg",
            storageDir
        )
        net.gamal.chefea.android.extentions.error("ImageFileUtils:saveBitmapToFile: ${imageFile.path} ")

        val outputStream = FileOutputStream(imageFile)

        // Compress the bitmap to JPEG format
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        return imageFile
    }

    fun saveImageToFile(responseBody: ResponseBody): File {
        // Get the input stream and write it to a file
        val storageDir: File = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        val imageFileName = "tinfyOutput_$timeStamp.jpg"

        val inputStream: InputStream = responseBody.byteStream()
        val outputFile = File.createTempFile(
            imageFileName,
            ".jpg",
            storageDir
        )
        net.gamal.chefea.android.extentions.error("ImageFileUtils:saveImageToFile: ${outputFile.path} ")
        val outputStream = FileOutputStream(outputFile)
        val buffer = ByteArray(4096)
        var bytesRead: Int
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            outputStream.write(buffer, 0, bytesRead)
        }
        outputStream.close()
        inputStream.close()
        return outputFile
    }
}
