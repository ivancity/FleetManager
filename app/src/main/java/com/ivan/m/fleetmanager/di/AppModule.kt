package com.ivan.m.fleetmanager.di

import com.ivan.m.fleetmanager.common.Constants
import com.ivan.m.fleetmanager.data.remote.FleetApi
import com.ivan.m.fleetmanager.data.repository.FleetRepositoryImpl
import com.ivan.m.fleetmanager.domain.repository.FleetRepository
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
    fun provideFleetRepository(api: FleetApi): FleetRepository {
        return FleetRepositoryImpl(api = api)
    }
}