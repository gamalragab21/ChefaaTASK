package net.gamal.chefea.festures.commics.domain.repository

import net.gamal.chefea.android.common.domain.model.request.RemoteRequest
import net.gamal.chefea.festures.commics.domain.models.Comics

interface IComicsRepository {
    suspend fun getComicsRemote(remoteRequest: RemoteRequest): Comics
}