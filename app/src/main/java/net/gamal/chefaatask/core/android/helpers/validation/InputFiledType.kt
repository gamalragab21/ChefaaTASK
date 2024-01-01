package net.gamal.chefaatask.core.android.helpers.validation


enum class InputFiledType(val title: String) {
    EMAIL("email"),
    PASSWORD("password"),
    PHONE_EMAIL("username"),
    REPEAT_PASSWORD("password_confirmation"),
    OUTLET("outlet"),
    COUNTRY_CODE("countryCode"),
    CURRENCY_TYPE("currencyType"),
    TRANSACTION_TYPE("transactionType"),
    PHONE("phone"),
    WIDTH("width"),
    IMAGE("image"),
    HEIGHT("height"),
    OTP("otp"),
    LOCATION("location"),
    AMOUNT("amount"),
    CODE("code"),
    OTHER("other");

    companion object {
        fun find(key: String): InputFiledType {
            return entries.find { it.title == key } ?: OTHER
        }
    }
}