package com.chuanzz.githubuserapp.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<T> by lazy { mutableListOf<T>() }

    protected var clickListener: ((T) -> Unit)? = null

    abstract fun createItemViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder

    protected abstract fun bindItemViewHolder(viewHolder: RecyclerView.ViewHolder?, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return createItemViewHolder(parent)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindItemViewHolder(holder, position)
    }

    fun add(item: T) {
        items.add(item)
        notifyItemInserted(items.size)
    }

    fun addAll(items: List<T>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T = items[position]

    fun setOnItemClick(clickListener : (T) -> Unit){
        this.clickListener = clickListener
    }
}