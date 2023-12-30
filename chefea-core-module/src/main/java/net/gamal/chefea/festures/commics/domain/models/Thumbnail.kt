package net.gamal.chefea.festures.commics.domain.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbnail(
    var extension: String,
    var path: String
) : Parcelable