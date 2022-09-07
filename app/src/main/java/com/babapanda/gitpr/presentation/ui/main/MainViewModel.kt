package com.babapanda.gitpr.presentation.ui.main

import com.babapanda.gitpr.base.BaseViewModel
import com.babapanda.gitpr.domain.usecase.GetClosePRUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getClosePRUseCase: GetClosePRUseCase
) : BaseViewModel() {

}