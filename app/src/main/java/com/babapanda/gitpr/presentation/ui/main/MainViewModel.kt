package com.babapanda.gitpr.presentation.ui.main

import androidx.lifecycle.MutableLiveData
import com.babapanda.gitpr.base.BaseViewModel
import com.babapanda.gitpr.domain.model.PullRequest
import com.babapanda.gitpr.domain.usecase.GetClosePRUseCase
import com.babapanda.gitpr.presentation.model.LoadingState
import com.babapanda.gitpr.presentation.model.StatesData
import com.babapanda.gitpr.presentation.model.DefaultState
import com.babapanda.gitpr.presentation.model.PaginationState
import com.babapanda.gitpr.presentation.model.ErrorState
import com.babapanda.gitpr.presentation.model.LoadMoreUiModel
import com.babapanda.gitpr.presentation.model.PRUiModel
import com.babapanda.gitpr.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getClosePRUseCase: GetClosePRUseCase
) : BaseViewModel() {
    companion object {
        private const val MAX_ALLOWED = 30
    }

    val stateLiveData = MutableLiveData<StatesData>()

    init {
        stateLiveData.value = DefaultState(1, emptyList(), false)
    }

    fun updatePrList() {
        val pageNo = getCurrentLoadedPageNo()
        stateLiveData.value = if (pageNo == 1)
            LoadingState(pageNo, currentLoadedItems(), false)
        else
            PaginationState(pageNo, currentLoadedItems(), false)
        getPRList(pageNo)
    }

    fun clearList() {
        stateLiveData.value = LoadingState(1, emptyList(), false)
        updatePrList()
    }

    fun getPreviousState() {
        val pageNo = getCurrentLoadedPageNo()
        stateLiveData.value = DefaultState(pageNo, currentLoadedItems(), false)
    }

    private fun getPRList(page: Int) {
        compositeDisposable?.add(
            getClosePRUseCase.execute(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    when (result) {
                        is Resource.Success -> {
                            handlePRList(result.data)
                        }

                        is Resource.Error -> {
                            onError(result.throwable)
                        }
                    }
                }, this::onError)
        )
    }

    private fun handlePRList(data: List<PullRequest>?) {
        val currentItems = currentLoadedItems().toMutableList()
        val currentPageNumber = getCurrentLoadedPageNo().plus(1)
        val allItemsAreLoaded = currentItems.size < MAX_ALLOWED
        data?.let {
           // if (currentItems.size > 1) currentItems.removeLast()
            currentItems.addAll(it.map { pr ->
                PRUiModel(
                    closedDate = pr.closedDate ?: "",
                    createDate = pr.createdDate ?: "",
                    repoName = pr.title ?: "",
                    avatarUrl = pr.user.avatarUrl ?: "",
                    userName = pr.user.name
                )
            })
        }
        // adding load more
      //  currentItems.add(LoadMoreUiModel())
        stateLiveData.value = DefaultState(currentPageNumber, currentItems, allItemsAreLoaded)
    }

    private fun onError(error: Throwable?) {
        val pageNum = stateLiveData.value?.pageNo ?: 1
        stateLiveData.value = ErrorState(
            errorMessage = error?.message
                ?: "Something went Wrong",
            pageNo = pageNum,
            loadedAllItems = areCurrentItemsLoaded(),
            data = currentLoadedItems()
        )
    }

    private fun getCurrentLoadedPageNo() = stateLiveData.value?.pageNo ?: 1

    private fun areCurrentItemsLoaded(): Boolean = stateLiveData.value?.loadedAllItems ?: false

    private fun currentLoadedItems() = stateLiveData.value?.data ?: emptyList()

}