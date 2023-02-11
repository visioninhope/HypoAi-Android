package dev.atick.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.atick.network.data.HypoAiDataSource
import dev.atick.network.data.HypoAiDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindHypoAiDataSource(
        hypoAiDataSourceImpl: HypoAiDataSourceImpl
    ): HypoAiDataSource

}