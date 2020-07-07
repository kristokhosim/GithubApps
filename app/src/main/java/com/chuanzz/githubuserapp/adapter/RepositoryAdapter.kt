package com.chuanzz.githubuserapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chuanzz.githubuserapp.R
import com.chuanzz.githubuserapp.base.BaseAdapter
import kotlinx.android.synthetic.main.activity_detail_user.view.*

class RepositoryAdapter : BaseAdapter<String>() {
    override fun createItemViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent?.context).inflate(R.layout.item_repository, parent, false)
        return ItemViewHolder(view)
    }

    override fun bindItemViewHolder(viewHolder: RecyclerView.ViewHolder?, position: Int) {
        val holder = viewHolder as ItemViewHolder
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(repository: String) {
            view.text_repository.text = repository
            view.setOnClickListener {
                clickListener?.invoke(repository)
            }
        }
    }
}