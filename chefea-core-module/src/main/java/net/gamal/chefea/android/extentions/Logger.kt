package net.gamal.chefea.android.extentions

import android.util.Log

private const val TAG="ChefeaAPP"
fun debug(message:String){
    Log.d(TAG, "debug: $message")
}
fun error(message:String){
    Log.e(TAG, "debug: $message")
}
fun info(message:String){
    Log.i(TAG, "debug: $message")
}
fun warning(message:String){
    Log.w(TAG, "debug: $message")
}