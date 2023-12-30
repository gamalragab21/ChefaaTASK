package net.gamal.chefea.android.helpers.gson.converter

import okhttp3.ResponseBody

internal interface INetworkFailureConverter {
    fun produceErrorBodyFailure(code: Int, errorBody: ResponseBody?): Throwable
    fun produceFailure(throwable: Throwable): Throwable
}