package com.babapanda.gitpr.data.api

import com.babapanda.gitpr.data.model.PullRequestDTO
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("repos/shubham/github-issues/pulls?state=closed&per_page=10")
    fun getPullRequest(@Query("page") page: Int): Single<Response<List<PullRequestDTO>>>
}