package com.chuanzz.githubuserapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chuanzz.githubuserapp.R
import com.chuanzz.githubuserapp.base.BaseAdapter
import com.chuanzz.githubuserapp.extension.formatFollowing
import com.chuanzz.githubuserapp.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class GithubUsersAdapter : BaseAdapter<User>() {
    override fun createItemViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_user, parent, false)
        return ItemViewHolder(view)
    }

    override fun bindItemViewHolder(viewHolder: RecyclerView.ViewHolder?, position: Int) {
        val holder = viewHolder as ItemViewHolder
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(user: User) {
            Glide.with(view.context)
                .load(user.avatar)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(view.imageAvatar)

            view.text_user_name.text = user.userName?.removeRange(0, 1)
            view.text_total_repositories.text =
                (user.repository?.size ?: 0).toLong().formatFollowing(view.context)
            view.text_total_following.text = user.following.formatFollowing(view.context)
            view.text_total_followers.text = user.followers.formatFollowing(view.context)
            view.setOnClickListener {
                clickListener?.invoke(user)
            }
        }
    }
}