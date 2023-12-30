package net.gamal.chefea.festures.commics.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.gamal.chefea.android.common.domain.repository.remote.ChefeaApiService
import net.gamal.chefea.festures.commics.data.repository.ComicsRepository
import net.gamal.chefea.festures.commics.data.repository.remote.ComicsImagesRemoteDs
import net.gamal.chefea.festures.commics.domain.repository.IComicsRepository
import net.gamal.chefea.festures.commics.domain.repository.remote.IComicsImagesRemoteDs
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ComicsModule {

    @Provides
    @Singleton
    fun provideComicsRemoteDS(apiService: ChefeaApiService): IComicsImagesRemoteDs =
        ComicsImagesRemoteDs(apiService)

    @Provides
    @Singleton
    fun provideComicsRepository(remote: IComicsImagesRemoteDs): IComicsRepository =
        ComicsRepository(remote)
}