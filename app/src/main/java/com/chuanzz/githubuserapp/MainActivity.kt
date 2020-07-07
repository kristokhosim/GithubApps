package com.chuanzz.githubuserapp

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.chuanzz.githubuserapp.adapter.GithubUsersAdapter
import com.chuanzz.githubuserapp.base.BaseActivity
import com.chuanzz.githubuserapp.model.User
import com.chuanzz.githubuserapp.objects.BundleName
import com.chuanzz.githubuserapp.utils.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : BaseActivity() {

    private val githubUsersAdapter: GithubUsersAdapter by lazy { GithubUsersAdapter() }

    override fun getLayout(): Int = R.layout.activity_main

    override fun setup() {
        initializeBasic(getString(R.string.github_user_lists))
        removeNavigation()
        initRecyclerView()
    }

    override fun createMainlyRequest() {
        val dataUsername = resources.getStringArray(R.array.data_username)
        val dataAvatar = resources.obtainTypedArray(R.array.data_avatar)
        val dataLocation = resources.getStringArray(R.array.data_location)
        dataUsername.forEachIndexed { i, data ->
            githubUsersAdapter.add(
                User(
                    userName = "@${data.split(" ")[0]}",
                    name = data,
                    location = dataLocation[i],
                    company = getRandomCompany(),
                    repository = createRepository(randomNumberGenerator(10L).toInt()),
                    avatar = dataAvatar.getResourceId(i, -1),
                    followers = randomNumberGenerator(),
                    following = randomNumberGenerator()
                )
            )
        }
        dataAvatar.recycle()
    }

    private fun initRecyclerView() {
        rvListUser.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context, R.drawable.horizontal_line_divider,
                    showFirstDivider = false,
                    showLastDivider = false
                )
            )
            adapter = githubUsersAdapter.apply {
                setOnItemClick {
                    val intent = Intent(context, DetailUserActivity::class.java)
                    intent.putExtra(BundleName.DATA, it)
                    startActivity(intent)
                }
            }
        }
    }

    private fun randomNumberGenerator(max: Long = 999999999L): Long = (1..max).random()
    private fun getRandomCompany(): String {
        val dataCompany = resources.getStringArray(R.array.data_company)
        return dataCompany.random()
    }

    private fun createRepository(count: Int): List<String> {
        val repositories = resources.getStringArray(R.array.data_repository)
        return repositories.toMutableList().apply {
            repeat(size - count) {
                println("SIZE : $size")
                removeAt(Random.nextInt(size))
            }
        }
    }
}
