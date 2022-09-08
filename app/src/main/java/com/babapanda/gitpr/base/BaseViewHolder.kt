package com.babapanda.gitpr.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.library.baseAdapters.BR

class BaseViewHolder<T> constructor(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private var data: Any? = null

    fun bind(
        item: Any?,
        baseViewModel: BaseViewModel?,
        baseHandler: BaseHandler<*>?,
        type: String?,
        position: Int?
    ) {
        this.data = item
        if (item != null && item is BaseUiModel && isRecyclable != item.isRecyclable()) {
            setIsRecyclable(item.isRecyclable())
        }
        if (item is BaseUiModel) {
            item.adapterPosition = position ?: 0
        }

        binding.setVariable(BR.obj, item)
        binding.setVariable(BR.handlers, baseHandler)
        binding.setVariable(BR.viewModel, baseViewModel)
        binding.setVariable(BR.type, type)
        binding.setVariable(BR.position, position)
        binding.executePendingBindings()
    }

    fun onViewDetachedFromWindow() {
        if (data != null && data is BaseUiModel) {
            (data as BaseUiModel).onViewDetachedFromWindow()
        }
    }
}