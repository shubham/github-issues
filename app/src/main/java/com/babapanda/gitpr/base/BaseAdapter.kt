package com.babapanda.gitpr.base

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseAdapter<T : BaseUiModel> : RecyclerView.Adapter<BaseViewHolder<T>> {
    private lateinit var mRecyclerView: RecyclerView
    private var layoutResource = 0
    var handler: BaseHandler<T>? = null
    private var viewModel: BaseViewModel? = null
    private var lastPosition = -1
    private var type: String? = null

    private var listOfItems: MutableList<T> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    val items: List<T>
        get() = listOfItems

    val firstItem: T?
        get() = if (listOfItems.size > 0) listOfItems[0] else null

    constructor(layoutResource: Int, type: String? = null) {
        this.layoutResource = layoutResource
        this.type = type
    }

    constructor(
        layoutResource: Int,
        handler: BaseHandler<T>?,
        type: String? = null
    ) {
        this.layoutResource = layoutResource
        this.handler = handler
        this.type = type
    }

    constructor(
        layoutResource: Int,
        viewModel: BaseViewModel?,
        handler: BaseHandler<T>? = null,
        type: String? = null
    ) {
        this.layoutResource = layoutResource
        this.viewModel = viewModel
        this.handler = handler
        this.type = type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater, viewType, parent, false
        )
        return BaseViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val data = listOfItems[position]
        holder.bind(
            item = data,
            baseViewModel = viewModel,
            baseHandler = handler,
            type = type,
            position = position
        )
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutResource != 0) {
            layoutResource
        } else {
            listOfItems[position].layoutResId
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    private fun isComputingLayout() =
        ::mRecyclerView.isInitialized && mRecyclerView.isComputingLayout

    fun insertDataAtPosition(data: T?, index: Int) {
        if (data == null) {
            return
        }
        listOfItems.add(index, data)
        notifyItemInserted(index)
    }

    fun clearItems() {
        try {
            if (isComputingLayout().not()) {
                listOfItems.clear()
                notifyDataSetChanged()
            } else {
                Log.e(
                    "BaseAdapter",
                    "exception is ",
                    Exception("RecyclerView failed to clearItems in else case ")
                )
            }
        } catch (e: IllegalStateException) {
            Log.e(
                "BaseAdapter",
                "exception is ",
                Exception("RecyclerView failed to clearItems in catch block and recyclerview state is ${isComputingLayout()}")
            )
        }
    }

    @Synchronized
    fun removeItem(position: Int) {
        try {
            if (isComputingLayout().not()) {
                if (listOfItems.size > 0) {
                    listOfItems.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, itemCount - position)
                }
            } else {
                Log.e(
                    "BaseAdapter",
                    "exception is ", Exception("RecyclerView failed to removeItem in else case")
                )
            }
        } catch (e: IllegalStateException) {
            Log.e(
                "BaseAdapter",
                "exception is ",
                Exception("RecyclerView failed to removeItem in catch block and recyclerview state is ${isComputingLayout()}")
            )
        }
    }

    override fun onViewDetachedFromWindow(viewHolder: BaseViewHolder<T>) {
        super.onViewDetachedFromWindow(viewHolder)
        viewHolder.onViewDetachedFromWindow()
    }
}
