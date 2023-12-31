package net.gamal.chefea.festures.resize.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Output(
    var height: Float,
    var url: String,
    var width: Float
) : Parcelable