package com.example.crudpersonasv3.hilt

import android.content.Context
import androidx.room.Room
import com.example.crudpersonasv3.data.RoomDatabaseConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        /*@Named("assetDB") ruta: String*/
    ) =
        Room.databaseBuilder(context, RoomDatabaseConfig::class.java, "database")
            .fallbackToDestructiveMigrationFrom(4)
            /*.createFromAsset(ruta)*/
            .build()

    @Provides
    fun providesDaoCharacters(articlesDatabase: RoomDatabaseConfig) =
        articlesDatabase.daoCharacters()

}