package com.babapanda.gitpr.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.library.baseAdapters.BR


class BaseViewHolder<T> constructor(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    private var data: Any? = null

    fun bind(
        item: Any?,
        baseViewModel: BaseViewModel,
        baseHandler: BaseHandler<T>,
        type: String,
        position: Int
    ) {
        this.data = item
        if (item != null && item is BaseUiModel && isRecyclable != item.isRecyclable()) {
            setIsRecyclable(item.isRecyclable())
        }
        if(item is BaseUiModel){
            (item as BaseUiModel).adapterPosition = position
        }

        binding.executePendingBindings()
    }
}