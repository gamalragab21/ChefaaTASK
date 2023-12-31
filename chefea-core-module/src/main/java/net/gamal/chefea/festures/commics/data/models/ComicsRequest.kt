package net.gamal.chefea.festures.commics.data.models

import net.gamal.chefea.android.hashing.HasherHelper
import net.gamal.chefea.core.common.data.consts.Constants
import net.gamal.chefea.core.common.domain.model.request.IRequestValidation
import net.gamal.chefea.core.common.domain.model.request.RemoteRequest
import net.gamal.chefea.core.common.domain.model.request.RequestContractType

data class ComicsRequest(
    val ts: String = System.currentTimeMillis().toString(),
    val publicApiKey: String, val privateApiKey: String
) : IRequestValidation {
    override fun isInitialState(): Boolean =
        ts.isEmpty() || publicApiKey.isEmpty() || privateApiKey.isEmpty()

    override val remoteMap: RemoteRequest
        get() = RemoteRequest(
            requestQueries = hashMapOf(
                Constants.TS to ts,
                Constants.API_KEY to publicApiKey,
                Constants.LIMIT to 20,
                Constants.HASH to HasherHelper.generateHash("$ts$privateApiKey$publicApiKey")
            )
        )

    override fun getRequestContracts(): HashMap<RequestContractType, HashMap<String, Boolean>> {
        return hashMapOf(
            RequestContractType.QUERIES to hashMapOf(
                Constants.TS to true,
                Constants.API_KEY to true,
                Constants.HASH to true,
                Constants.LIMIT to true,
            )
        )
    }

}