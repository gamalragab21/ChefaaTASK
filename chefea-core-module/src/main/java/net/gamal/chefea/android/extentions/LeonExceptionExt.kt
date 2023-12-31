package net.gamal.chefea.android.extentions

import com.google.gson.GsonBuilder
import net.gamal.chefea.core.common.data.model.exception.LeonException
import net.gamal.chefea.android.helpers.gson.adapter.LeonExceptionTypeAdapter
import net.gamal.chefea.android.helpers.gson.parsing.JSONParsingStrategy
import net.gamal.chefea.android.helpers.gson.serializer.client.LeonClientResponseValidationSerializer
import net.gamal.chefea.android.helpers.gson.serializer.client.LeonClientUnauthorizedSerializer
import net.gamal.chefea.android.helpers.gson.serializer.client.LeonClientUnhandledSerializer
import net.gamal.chefea.android.helpers.gson.serializer.local.LeonLocalIOOperationSerializer
import net.gamal.chefea.android.helpers.gson.serializer.local.LeonLocalRequestValidationSerializer
import net.gamal.chefea.android.helpers.gson.serializer.network.LeonNetworkHandledSerializer
import net.gamal.chefea.android.helpers.gson.serializer.network.LeonNetworkRetrialSerializer
import net.gamal.chefea.android.helpers.gson.serializer.server.LeonServerInternalSerializer

internal fun LeonException.toJsonLeonException(): String {
    val gson = GsonBuilder()
        // NetworkException
        .registerTypeAdapter(
            LeonException.Network.Retrial::class.java, LeonNetworkRetrialSerializer()
        ).registerTypeAdapter(
            LeonException.Network.Unhandled::class.java, LeonNetworkHandledSerializer()
        )

        // Client
        .registerTypeAdapter(
            LeonException.Client.Unauthorized::class.java, LeonClientUnauthorizedSerializer()
        ).registerTypeAdapter(
            LeonException.Client.ResponseValidation::class.java,
            LeonClientResponseValidationSerializer()
        ).registerTypeAdapter(
            LeonException.Client.Unhandled::class.java, LeonClientUnhandledSerializer()
        )

        // Server
        .registerTypeAdapter(
            LeonException.Server.InternalServerError::class.java, LeonServerInternalSerializer()
        )

        // Local
        .registerTypeAdapter(
            LeonException.Local.RequestValidation::class.java,
            LeonLocalRequestValidationSerializer()
        ).registerTypeAdapter(
            LeonException.Local.IOOperation::class.java, LeonLocalIOOperationSerializer()
        )
        .create()

    return gson.toJson(this)
}

fun String.toLeonException(): LeonException {
    val gson = GsonBuilder()
        .registerTypeAdapter(
            LeonException::class.java, LeonExceptionTypeAdapter(JSONParsingStrategy())
        )
        .create()

    return gson.fromJson(this, LeonException::class.java)
}
