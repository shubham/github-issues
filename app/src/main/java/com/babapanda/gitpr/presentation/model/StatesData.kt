package com.babapanda.gitpr.presentation.model

import com.babapanda.gitpr.base.BaseUiModel

sealed class StatesData {
    abstract val pageNo: Int
    abstract val loadedAllItems: Boolean
    abstract val data: List<BaseUiModel>
}

data class DefaultState(
    override val pageNo: Int,
    override val data: List<BaseUiModel>,
    override val loadedAllItems: Boolean
) : StatesData()

data class LoadingState(
    override val pageNo: Int,
    override val data: List<BaseUiModel>,
    override val loadedAllItems: Boolean
) : StatesData()

data class PaginationState(
    override val pageNo: Int,
    override val data: List<BaseUiModel>,
    override val loadedAllItems: Boolean
) :
    StatesData()

data class ErrorState(
    val errorMessage: String,
    override val pageNo: Int,
    override val data: List<BaseUiModel>,
    override val loadedAllItems: Boolean
) : StatesData()