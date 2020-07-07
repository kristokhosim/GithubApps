package com.chuanzz.githubuserapp.base

import android.os.Bundle
import android.transition.Fade
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.chuanzz.githubuserapp.R

abstract class BaseActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null

    abstract fun getLayout(): Int
    abstract fun setup()
    abstract fun createMainlyRequest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        setup()
        createMainlyRequest()
    }

    private fun initLayout() {
        setContentView(getLayout())
    }

    private fun initializeBasicComponent() {
        toolbar = findViewById(R.id.toolbar)
    }

    protected fun initializeBasic(title: String? = "") {
        initializeBasicComponent()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (title == "") {
            supportActionBar?.setDisplayShowTitleEnabled(false)
        } else {
            supportActionBar?.setDisplayShowTitleEnabled(true)
        }
        supportActionBar?.title = title
        toolbar?.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_back_white)
        toolbar?.setNavigationOnClickListener { onBackPressed() }

        val fade = Fade()
        val decor = window.decorView.findViewById<View>(R.id.action_bar_container)

        fade.excludeTarget(decor, true)
        fade.excludeTarget(android.R.id.statusBarBackground, true)
        fade.excludeTarget(android.R.id.navigationBarBackground, true)

        window.enterTransition = fade
        window.exitTransition = fade

        //   changeStatusBarColor(R.color.black_1F)
    }

    protected fun removeNavigation() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun changeStatusBarColor(color: Int) {
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = color
    }
}