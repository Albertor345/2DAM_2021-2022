package com.example.seriespelisretrofit.data.local.hilt

import android.content.Context
import androidx.room.Room
import com.example.seriespelisretrofit.data.local.RoomDatabaseConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ) =
        Room.databaseBuilder(context, RoomDatabaseConfig::class.java, "database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providesDaoFavoritos(articlesDatabase: RoomDatabaseConfig) =
        articlesDatabase.daoFavoritos()

}