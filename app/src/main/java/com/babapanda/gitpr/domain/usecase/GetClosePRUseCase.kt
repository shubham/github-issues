package com.babapanda.gitpr.domain.usecase

import com.babapanda.gitpr.domain.model.PullRequest
import com.babapanda.gitpr.domain.repository.GitHubRepository
import com.babapanda.gitpr.util.Resource
import io.reactivex.Single
import javax.inject.Inject

interface GetClosePRUseCase {
    fun execute(page: Int): Single<Resource<List<PullRequest>>>
}

class GetClosePRUseCaseImpl @Inject constructor(private val repository: GitHubRepository) :
    GetClosePRUseCase {
    override fun execute(page: Int): Single<Resource<List<PullRequest>>> {
        return repository.getUserPullRequest(page)
    }
}