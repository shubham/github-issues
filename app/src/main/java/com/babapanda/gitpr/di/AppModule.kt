package com.babapanda.gitpr.di

import com.babapanda.gitpr.data.repository.GithubRepositoryImpl
import com.babapanda.gitpr.domain.repository.GitHubRepository
import com.babapanda.gitpr.domain.usecase.GetClosePRUseCase
import com.babapanda.gitpr.domain.usecase.GetClosePRUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideGithubRepository(githubRepositoryImpl: GithubRepositoryImpl): GitHubRepository =
        githubRepositoryImpl

    @Provides
    fun provideClosePrUseCase(getClosePRUseCaseImpl: GetClosePRUseCaseImpl): GetClosePRUseCase =
        getClosePRUseCaseImpl

}