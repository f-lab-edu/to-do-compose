package flab.eryuksa.todocompose.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import flab.eryuksa.todocompose.data.source.local.LocalDataSource
import flab.eryuksa.todocompose.data.source.local.LocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalSourceModule {

    @Binds
    @Singleton
    abstract fun bindLocalSource(localDataSource: LocalDataSourceImpl): LocalDataSource
}