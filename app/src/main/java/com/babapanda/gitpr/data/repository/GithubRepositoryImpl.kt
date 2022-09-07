package com.babapanda.gitpr.data.repository

import com.babapanda.gitpr.data.api.ApiService
import com.babapanda.gitpr.domain.model.PullRequest
import com.babapanda.gitpr.domain.repository.GitHubRepository
import com.babapanda.gitpr.util.Resource
import com.babapanda.gitpr.util.convertResponseToResult
import io.reactivex.Single
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : GitHubRepository {
    override fun getUserPullRequest(page: Int): Single<Resource<List<PullRequest>>> {
        return apiService.getPullRequest(page).convertResponseToResult {
            it?.map { pr ->
                pr.toDomainModel()
            }
        }
    }
}