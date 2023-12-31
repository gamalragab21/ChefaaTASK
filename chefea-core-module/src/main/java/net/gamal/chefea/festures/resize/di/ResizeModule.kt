package net.gamal.chefea.festures.resize.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.gamal.chefaatask.core.android.helpers.file.ImageFileUtils
import net.gamal.chefea.core.common.data.repository.local.room.ChefeaDatabase
import net.gamal.chefea.core.common.domain.repository.remote.ChefeaApiService
import net.gamal.chefea.festures.resize.data.repository.ResizeRepository
import net.gamal.chefea.festures.resize.data.repository.local.ResizeLocalDs
import net.gamal.chefea.festures.resize.data.repository.remote.ResizeRemoteDs
import net.gamal.chefea.festures.resize.domain.repository.IResizeRepository
import net.gamal.chefea.festures.resize.domain.repository.local.IResizeLocalDs
import net.gamal.chefea.festures.resize.domain.repository.remote.IResizeRemoteDs
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ResizeModule {

    @Provides
    @Singleton
    fun provideResizeRemoteDS(apiService: ChefeaApiService): IResizeRemoteDs =
        ResizeRemoteDs(apiService)

    @Provides
    @Singleton
    fun provideResizeLocalDS(chefeaDatabase: ChefeaDatabase): IResizeLocalDs =
        ResizeLocalDs(chefeaDatabase)

    @Provides
    @Singleton
    fun provideResizeRepository(
        context: Context, imageFileUtils: ImageFileUtils,
        localDs: IResizeLocalDs, remote: IResizeRemoteDs
    ): IResizeRepository =
        ResizeRepository(context, remote, localDs, imageFileUtils)
}