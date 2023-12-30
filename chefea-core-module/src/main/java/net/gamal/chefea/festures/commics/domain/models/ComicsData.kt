package net.gamal.chefea.festures.commics.domain.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicsData(
    var count: Int,
    var limit: Int,
    var offset: Int,
    var results: List<ComicsItem>,
    var total: Int
) : Parcelable