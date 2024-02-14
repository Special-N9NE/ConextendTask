package com.example.qrapp.di

import android.content.Context
import androidx.room.Room
import com.example.qrapp.data.repo.AppRepo
import com.example.qrapp.data.repo.AppRepoImpl
import com.example.qrapp.data.source.AppDatabase
import com.example.qrapp.data.source.RoomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "product_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideDao(appDatabase: AppDatabase): RoomDao {
        return appDatabase.getDao()
    }


    @Provides
    fun provideRepo(@ApplicationContext context: Context): AppRepo {
        return AppRepoImpl(provideAppDatabase(context))
    }
}
