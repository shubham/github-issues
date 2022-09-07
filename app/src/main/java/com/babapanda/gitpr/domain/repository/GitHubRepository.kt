package com.babapanda.gitpr.domain.repository

import com.babapanda.gitpr.domain.model.PullRequest
import com.babapanda.gitpr.util.Resource
import io.reactivex.Single

interface GitHubRepository {
    fun getUserPullRequest(page: Int): Single<Resource<List<PullRequest>>>
}