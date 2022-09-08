package com.babapanda.gitpr.presentation.model

import com.babapanda.gitpr.R
import com.babapanda.gitpr.base.BaseUiModel

class PRUiModel(
    val createDate: String,
    val closedDate: String,
    val userName: String,
    val avatarUrl: String,
    val repoName: String
) : BaseUiModel(layoutResId = R.layout.item_pr)