package com.ivan.m.fleetmanager.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.ivan.m.fleetmanager.common.Constants
import com.ivan.m.fleetmanager.data.remote.FleetApi
import com.ivan.m.fleetmanager.data.repository.FleetRepositoryImpl
import com.ivan.m.fleetmanager.domain.preferences.Preferences
import com.ivan.m.fleetmanager.domain.repository.FleetRepository
import com.ivan.m.fleetmanager.domain.use_case.preferences.DefaultPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * All the dependencies for this module will be alive for the duration of the app.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFleetApi(): FleetApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FleetApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFleetRepository(preferences: Preferences, api: FleetApi): FleetRepository {
        return FleetRepositoryImpl(preferences = preferences, api = api)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(
        app: Application
    ): SharedPreferences {
        return app.getSharedPreferences("fleet_shared_pref", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences): Preferences {
        return DefaultPreferences(sharedPreferences)
    }
}