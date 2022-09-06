package com.babapanda.gitpr.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    var listOfItems: MutableList<T>? = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        TODO("Not yet implemented")
    }
}
