package com.babapanda.gitpr.presentation.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.babapanda.gitpr.R
import com.babapanda.gitpr.base.BaseAdapter
import com.babapanda.gitpr.base.BaseFragment
import com.babapanda.gitpr.base.BaseHandler
import com.babapanda.gitpr.base.BaseUiModel
import com.babapanda.gitpr.databinding.FragmentMainBinding
import com.babapanda.gitpr.presentation.model.DefaultState
import com.babapanda.gitpr.presentation.model.ErrorState
import com.babapanda.gitpr.presentation.model.LoadingState
import com.babapanda.gitpr.presentation.model.PaginationState
import com.babapanda.gitpr.presentation.model.StatesData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment(), BaseHandler<Any> {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mainViewModel by viewModels<MainViewModel>()

    private lateinit var baseAdapter: BaseAdapter<BaseUiModel>
    private var lastPageLoaded = false
    private var isLoading = false

    private lateinit var binding: FragmentMainBinding

    private val stateObserver = Observer<StatesData> { state ->
        state?.let {
            lastPageLoaded = state.loadedAllItems
            when (state) {
                is DefaultState -> {
                    isLoading = false
                    baseAdapter.updateData(state.data)
                    binding.errorGroup.visibility = View.GONE
                    binding.shimmerViewContainer.stopShimmer()
                    binding.shimmerViewContainer.visibility = View.GONE
                    binding.repoRecyclerView.visibility = View.VISIBLE
                }
                is ErrorState -> {
                    isLoading = false
                    binding.errorGroup.visibility = View.VISIBLE
                    binding.shimmerViewContainer.visibility = View.GONE
                    binding.shimmerViewContainer.stopShimmer()
                    binding.repoRecyclerView.visibility = View.GONE
                }
                is LoadingState -> {
                    isLoading = true
                    binding.errorGroup.visibility = View.GONE
                    if (binding.shimmerViewContainer.isShimmerStarted.not())
                        binding.shimmerViewContainer.startShimmer()
                }
                is PaginationState -> {
                    isLoading = true
                    binding.shimmerViewContainer.stopShimmer()
                    binding.shimmerViewContainer.visibility = View.GONE
                    binding.repoRecyclerView.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()
        savedInstanceState?.let {
            mainViewModel.getPreviousState()
        } ?: mainViewModel.updatePrList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        setUpUI()
        binding.apply {
            this.viewModel = mainViewModel
            this.handlers = this@MainFragment
        }
        return binding.root
    }

    private fun setUpUI() {
        baseAdapter = BaseAdapter(
            layoutResource = R.layout.item_pr,
            viewModel = mainViewModel,
            handler = this@MainFragment
        )
        val linearLayoutManager = LinearLayoutManager(context)
        binding.repoRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = baseAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
    }

    private fun observeViewModel() {
        mainViewModel.stateLiveData.observe(this, stateObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.stateLiveData.removeObserver(stateObserver)
    }

    override fun onclick(view: View, data: Any) {
        // not needed for now, but can be extended
        when (view.id) {
            R.id.btn_load_more -> {
                mainViewModel.updatePrList()
            }
        }
    }
}