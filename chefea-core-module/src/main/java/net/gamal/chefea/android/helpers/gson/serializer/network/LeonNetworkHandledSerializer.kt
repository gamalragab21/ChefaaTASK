package net.gamal.chefea.android.helpers.gson.serializer.network

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import net.gamal.chefea.core.common.data.consts.Constants.MESSAGE
import net.gamal.chefea.core.common.data.consts.Constants.MESSAGE_RES
import net.gamal.chefea.core.common.data.consts.Constants.SUBTYPE
import net.gamal.chefea.core.common.data.model.exception.LeonException
import java.lang.reflect.Type

internal class LeonNetworkHandledSerializer : JsonSerializer<LeonException.Network.Unhandled> {

    override fun serialize(
        src: LeonException.Network.Unhandled?, typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        val jsonObject = JsonObject()
        src?.apply {
            jsonObject.addProperty(MESSAGE, message)
            jsonObject.addProperty(MESSAGE_RES, messageRes)
            jsonObject.addProperty(
                SUBTYPE, LeonException.Network.Unhandled::class.java.simpleName
            )
        }
        return jsonObject
    }
}