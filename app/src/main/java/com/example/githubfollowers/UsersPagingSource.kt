/*
 *
 * Copyright © 2022 Charitha Amarasinghe, Colombo, Sri Lanka
 * All Rights Reserved.
 *
 */
package com.example.githubfollowers

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubfollowers.apiconnect.ItemsData
import com.example.githubfollowers.apiconnect.RetroService
import com.example.githubfollowers.utils.LocalDB
import java.lang.Exception

class UsersPagingSource(val apiService: RetroService) : PagingSource<Int, ItemsData>() {

    override fun getRefreshKey(state: PagingState<Int, ItemsData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemsData> {
        return try {
            val nextPage: Int? = params.key ?: FIRST_PAGE_INDEX
            // These keys (CLIENT ID AND SECRET KEY) should decrypt in the code - because this will make a security issue
            val response = nextPage?.let {
                apiService.getDataFromAPI(
                    "followers:>=0",
                    "searchresults",
                    "followers",
                    "Users",
                    10,
                    it,
                    "Iv1.02e25df009e61f9f",
                    "0f3a47545c8a1c8e09861b38ee3d6a94e46a24b8"
                )
            }

            val nextPageNumber: Int? = nextPage?.plus(1)
            LocalDB.pageNumber = nextPageNumber
            LoadResult.Page(data = response!!.items, prevKey = null, nextKey = nextPageNumber)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 0
    }
}