package net.gamal.chefea.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.gamal.chefea.core.common.data.consts.Constants
import net.gamal.chefea.core.common.data.repository.local.room.ChefeaDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object StorageModule {


    @Provides
    @Singleton
    fun provideChefeaDatabase(@ApplicationContext context: Context): ChefeaDatabase =
        Room.databaseBuilder(context, ChefeaDatabase::class.java, Constants.CHEFEA_DB_NAME).build()
}