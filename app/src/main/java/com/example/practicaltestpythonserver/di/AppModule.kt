package com.example.practicaltestpythonserver.di

import android.content.Context
import com.example.appunotest.Utils.NetworkStateData

import com.example.practicaltestpythonserver.MyApplication
import com.example.practicaltestpythonserver.network.ApiService
import com.example.practicaltestpythonserver.network.NetworkSDK
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideNetworkSDK(apiService: ApiService): NetworkSDK {
        return NetworkSDK(apiService)
    }
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MyApplication {
        return app as MyApplication
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }



    @Provides
    @Singleton
    fun providesNetworkState(@ApplicationContext app: Context): NetworkStateData {
        return NetworkStateData(app)
    }






}