package com.babapanda.gitpr.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

open class BaseViewModel() : ViewModel() {

    var dialogLoadingObservable: BehaviorSubject<String> = BehaviorSubject.create<String>()
    var apiCallInProgress: Boolean = false
    var compositeDisposable: CompositeDisposable? = null

    init {
        compositeDisposable = CompositeDisposable()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.clear()
    }

    open fun callInProgress() {
        apiCallInProgress = true
    }

    open fun callCompleted() {
        apiCallInProgress = false
    }

    fun showProgressDialog(message: String? = null) {
        callInProgress()
        if (message == null)
            dialogLoadingObservable.onNext("Please wait")
        else
            dialogLoadingObservable.onNext(message)
    }

    fun hideProgressDialog() {
        callCompleted()
        dialogLoadingObservable.onNext("")
    }
}