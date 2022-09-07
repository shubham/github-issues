package com.babapanda.gitpr.domain.model

data class PullRequest(
    val title: String?,
    val createdDate: String?,
    val closedDate: String?,
    val user: User
)