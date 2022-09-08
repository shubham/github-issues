package com.babapanda.gitpr.di

import com.babapanda.gitpr.data.repository.GithubRepositoryImpl
import com.babapanda.gitpr.domain.repository.GitHubRepository
import com.babapanda.gitpr.domain.usecase.GetClosePRUseCase
import com.babapanda.gitpr.domain.usecase.GetClosePRUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideGithubRepository(githubRepositoryImpl: GithubRepositoryImpl): GitHubRepository =
        githubRepositoryImpl

    @Provides
    fun provideClosePrUseCase(getClosePRUseCaseImpl: GetClosePRUseCaseImpl): GetClosePRUseCase =
        getClosePRUseCaseImpl

}