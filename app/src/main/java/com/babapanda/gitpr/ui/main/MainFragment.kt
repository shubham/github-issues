package com.babapanda.gitpr.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.babapanda.gitpr.R
import com.babapanda.gitpr.base.BaseFragment
import com.babapanda.gitpr.base.BaseHandler
import com.babapanda.gitpr.base.BaseUiModel
import javax.inject.Inject

class MainFragment : BaseFragment(), BaseHandler<BaseUiModel> {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onclick(view: View, data: BaseUiModel) {

    }

}