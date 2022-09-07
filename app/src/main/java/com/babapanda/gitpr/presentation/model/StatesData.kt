package com.babapanda.gitpr.presentation.model

sealed class StatesData {
    abstract val pageNo: Int
    abstract val loadedAllItems: Boolean
    abstract val data: List<PRUiModel>
}

data class DefaultState(
    override val pageNo: Int,
    override val data: List<PRUiModel>,
    override val loadedAllItems: Boolean
) : StatesData()

data class LoadingState(
    override val pageNo: Int,
    override val data: List<PRUiModel>,
    override val loadedAllItems: Boolean
) : StatesData()

data class PaginationState(
    override val pageNo: Int,
    override val data: List<PRUiModel>,
    override val loadedAllItems: Boolean
) :
    StatesData()

data class ErrorState(
    val errorMessage: String,
    override val pageNo: Int,
    override val data: List<PRUiModel>,
    override val loadedAllItems: Boolean
) : StatesData()