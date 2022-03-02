/*
 *
 * Copyright © 2022 Charitha Amarasinghe, Colombo, Sri Lanka
 * All Rights Reserved.
 *
 */
package com.example.githubfollowers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubfollowers.utils.KeyStore.Companion.KEY_AVATAR_LINK
import com.example.githubfollowers.utils.KeyStore.Companion.KEY_USER_NAME
import com.example.githubfollowers.utils.LocalDB
import com.example.githubfollowers.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    val mFragmentManager = supportFragmentManager
    val mFragmentTransaction = mFragmentManager.beginTransaction()
    val mFragment = MyCustomFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initViewModel()

        swipeLayout.setOnRefreshListener {
            LocalDB.pageNumber = 0
            initViewModel()
            swipeLayout.isRefreshing = false
        }
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter
            recyclerViewAdapter.setOnItemClickListener(object :
                RecyclerViewAdapter.OnItemClickListener {
                override fun onItemClick(login: String, avatar: String?) {
                    recyclerView.visibility = View.GONE
                    val mBundle = Bundle()
                    mBundle.putString(KEY_USER_NAME, login)
                    mBundle.putString(KEY_AVATAR_LINK, avatar)
                    mFragment.arguments = mBundle
                    mFragmentTransaction.add(R.id.frameLayout, mFragment).commit()
                }
            })
        }
    }

    private fun initViewModel(){
        val viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest { recyclerViewAdapter.submitData(it) }
        }
    }
}