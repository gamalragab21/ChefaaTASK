package net.gamal.chefea.festures.commics.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.gamal.chefea.core.common.data.repository.local.room.ChefeaDatabase
import net.gamal.chefea.core.common.domain.repository.remote.ChefeaApiService
import net.gamal.chefea.festures.commics.data.repository.ComicsRepository
import net.gamal.chefea.festures.commics.data.repository.local.ComicsImagesLocalDs
import net.gamal.chefea.festures.commics.data.repository.remote.ComicsImagesRemoteDs
import net.gamal.chefea.festures.commics.domain.repository.IComicsRepository
import net.gamal.chefea.festures.commics.domain.repository.local.IComicsImagesLocalDs
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
    fun provideComicsLocalDS(chefeaDatabase: ChefeaDatabase): IComicsImagesLocalDs =
        ComicsImagesLocalDs(chefeaDatabase)

    @Provides
    @Singleton
    fun provideComicsRepository(
        @ApplicationContext context: Context,
        remote: IComicsImagesRemoteDs,
        localDS: IComicsImagesLocalDs
    ): IComicsRepository =
        ComicsRepository(context, remote, localDS)
}