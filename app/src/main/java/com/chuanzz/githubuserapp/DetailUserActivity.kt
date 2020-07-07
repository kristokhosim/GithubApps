package com.chuanzz.githubuserapp

import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chuanzz.githubuserapp.adapter.RepositoryAdapter
import com.chuanzz.githubuserapp.base.BaseActivity
import com.chuanzz.githubuserapp.extension.formatFollowing
import com.chuanzz.githubuserapp.extension.snackBar
import com.chuanzz.githubuserapp.model.User
import com.chuanzz.githubuserapp.objects.BundleName
import com.chuanzz.githubuserapp.utils.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_detail_user.*

class DetailUserActivity : BaseActivity() {

    override fun getLayout(): Int = R.layout.activity_detail_user

    private val userData by lazy {
        intent.getParcelableExtra(BundleName.DATA) as User
    }

    private val repositoryAdapter: RepositoryAdapter by lazy { RepositoryAdapter() }

    override fun setup() {
        initializeBasic(getString(R.string.title_detail_user, userData.name))
        setData()
        initRecyclerView()
    }

    override fun createMainlyRequest() {

    }

    private fun initRecyclerView() {
        rvRepository.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context, R.drawable.horizontal_line_divider,
                    showFirstDivider = false,
                    showLastDivider = false
                )
            )
            adapter = repositoryAdapter.apply {
                setOnItemClick {
                    snackBar(it)
                }
            }
        }
        repositoryAdapter.addAll(userData.repository ?: listOf())
    }

    private fun setData() {
        Glide.with(this)
            .load(userData.avatar)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(image_avatar)

        text_repository_count.text =
            (userData.repository?.size ?: 0).toLong().formatFollowing(this)
        text_followers_count.text = userData.followers.formatFollowing(this)
        text_following_count.text = userData.following.formatFollowing(this)
        text_name.text = userData.name
        text_username.text = userData.userName
        text_company.text = userData.company
        text_location.text = userData.location
    }
}
