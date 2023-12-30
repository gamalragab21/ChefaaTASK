package net.gamal.chefea.festures.commics.domain.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicsItem(
    var id: Int,
    var thumbnail: Thumbnail,
    var title: String
) : Parcelable