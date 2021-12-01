package com.example.crudpersonasv3.hilt

import android.content.Context
import androidx.room.Room
import com.example.examen_moviles_1eva_albertogonzalez.data.RoomDatabaseConfig
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
        /*@Named("assetDB") ruta: String*/
    ) =
        Room.databaseBuilder(context, RoomDatabaseConfig::class.java, "database")
            .fallbackToDestructiveMigrationFrom(3)
            /*.createFromAsset(ruta)*/
            .build()



    @Provides
    fun providesDaoPuntos(articlesDatabase: RoomDatabaseConfig) =
        articlesDatabase.daoPuntos()

    @Provides
    fun providesDaoMensajes(articlesDatabase: RoomDatabaseConfig) =
        articlesDatabase.daoMensajes()

}