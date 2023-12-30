package net.gamal.chefea.festures.commics.domain.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import net.gamal.chefea.android.common.domain.model.BaseDomain

@Parcelize
data class Comics(
    var attributionText: String,
    var comicsData: ComicsData,
    var status: String
) : BaseDomain(), Parcelable {

    fun isInInitialState() = codeDto != 200 || status != "Ok"
}