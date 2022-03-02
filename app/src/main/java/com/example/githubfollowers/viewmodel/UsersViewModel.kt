/*
 *
 * Copyright © 2022 Charitha Amarasinghe, Colombo, Sri Lanka
 * All Rights Reserved.
 *
 */
package com.example.githubfollowers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubfollowers.UsersPagingSource
import com.example.githubfollowers.apiconnect.ItemsData
import com.example.githubfollowers.apiconnect.InstanceRetrofit
import com.example.githubfollowers.apiconnect.RetroService
import kotlinx.coroutines.flow.Flow

class UsersViewModel: ViewModel() {
    lateinit var retroService: RetroService

    init {
        retroService = InstanceRetrofit.getRetroInstance().create(RetroService::class.java)
    }
    // use coroutins
    fun getListData(): Flow<PagingData<ItemsData>> {
        return Pager(config = PagingConfig(pageSize = 10, maxSize = 150),
        pagingSourceFactory = { UsersPagingSource(retroService) }).flow.cachedIn(viewModelScope)
    }
}