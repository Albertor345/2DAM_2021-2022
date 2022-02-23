package com.example.logincompose.data.local.hilt

import android.content.Context
import androidx.room.Room
import com.example.logincompose.data.local.RoomDatabaseConfig
import com.example.logincompose.utils.Constants
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
        Room.databaseBuilder(context, RoomDatabaseConfig::class.java, Constants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providesDaoFavoritos(articlesDatabase: RoomDatabaseConfig) =
        articlesDatabase.daoFavoritos()

}