package net.gamal.chefea.festures.resize.domain.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import net.gamal.chefea.core.common.domain.model.BaseDomain

@Parcelize
data class TinfyResponse(
    var output: Output
) : BaseDomain(), Parcelable