package net.gamal.chefaatask.core.android.helpers.validation

import android.content.Context

data class InputFieldError(var key: InputFiledType, var message: Any)

fun InputFieldError.getErrorMessage(context: Context): String = when (message) {
    is Int -> context.getString(message as Int)
    else -> message as String
}