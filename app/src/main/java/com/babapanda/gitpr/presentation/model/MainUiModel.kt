package com.babapanda.gitpr.presentation.model

import com.babapanda.gitpr.R
import com.babapanda.gitpr.base.BaseUiModel

class MainUiModel(
    var itemList: List<PRUiModel>? = null,
    var resId: Int = R.layout.item_pr,
) : BaseUiModel(layoutResId = resId)