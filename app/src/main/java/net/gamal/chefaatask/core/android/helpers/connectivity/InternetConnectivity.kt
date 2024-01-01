package net.gamal.chefaatask.core.android.helpers.connectivity


object InternetConnectivity {

    fun isConnected(): Boolean {
        val command = "ping -c 1 google.com"
        return Runtime.getRuntime().exec(command).waitFor() == 0
    }
}