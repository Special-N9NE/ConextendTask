package com.example.qrapp.di

import android.app.Activity
import com.example.qrapp.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Provides
    fun provideActivity(activity: Activity): MainActivity {
        return activity as MainActivity
    }
}