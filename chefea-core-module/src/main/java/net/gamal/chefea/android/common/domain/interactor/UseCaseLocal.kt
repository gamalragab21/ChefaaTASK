package net.gamal.chefea.android.common.domain.interactor

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import net.gamal.chefea.android.common.data.model.Resource
import net.gamal.chefea.android.common.data.model.exception.LeonException
import net.sbs.softpos.android.common.domain.interactor.UseCase

internal abstract class UseCaseLocal<Domain, in Body> : UseCase<Domain, Body>() {

    protected abstract fun executeLocalDS(body: Body? = null): Flow<Domain>

    override operator fun invoke(
        scope: CoroutineScope, body: Body?, multipleInvoke: Boolean,
        onResult: (Resource<Domain>) -> Unit
    ) {
        scope.launch(Dispatchers.Main) {
            if (multipleInvoke.not())
                onResult.invoke(Resource.loading())

            runFlow(executeLocalDS(body), body, onResult).collect {
                onResult.invoke(invokeSuccessState(it, body))

                if (multipleInvoke.not())
                    onResult.invoke(Resource.loading(false))
            }
        }
    }

    override operator fun invoke(body: Body?, multipleInvoke: Boolean): Flow<Resource<Domain>> =
        channelFlow {
            if (multipleInvoke.not())
                send(Resource.loading())

            runFlow(executeLocalDS(body), body) {
                send(it)
            }.collect {
                send(invokeSuccessState(it, body))

                if (multipleInvoke.not())
                    send(Resource.loading(false))
            }
        }

    override fun validateResponseModel(domain: Domain, body: Body?): Resource<Domain>? = null

    override suspend fun validateFailureResponse(
        exception: LeonException, body: Body?
    ): Resource<Domain>? = null

    override suspend fun <M> runFlow(
        requestExecution: Flow<M>, body: Body?, onResult: suspend (Resource<Domain>) -> Unit
    ): Flow<M> = requestExecution.catch { e ->
        val failureResource = if (e is LeonException) e
        else LeonException.Unknown(message = "Unknown error in UseCaseLocal: $e")

        onResult.invoke(invokeFailureState(failureResource, body))
        onResult.invoke(Resource.loading(false))
    }.apply {
        if (runFlowInsideIODisputer())
            flowOn(Dispatchers.IO)
    }
}