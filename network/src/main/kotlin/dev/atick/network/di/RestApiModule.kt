package dev.atick.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.atick.network.api.HypoAiRestApi
import dev.atick.network.di.retrofit.RetrofitModule
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(
    includes = [
        RetrofitModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object RestApiModule {
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): HypoAiRestApi {
        return retrofit.create(HypoAiRestApi::class.java)
    }
}