package net.gamal.chefea.android.hashing

import android.security.keystore.KeyProperties
import java.security.MessageDigest

internal object HasherHelper {
    private val messageDigest = MessageDigest.getInstance(KeyProperties.DIGEST_MD5)

    fun generateHash(input:String): String {
        // Create MD5 hash
        val digest = messageDigest.digest(input.toByteArray())

        // Convert the byte array to a hexadecimal string
        val hexString = StringBuffer()
        for (i in digest.indices) {
            val hex = Integer.toHexString(0xff and digest[i].toInt())
            if (hex.length == 1) hexString.append('0')
            hexString.append(hex)
        }
        return hexString.toString()
    }
}