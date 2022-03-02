/*
 *
 * Copyright © 2022 Charitha Amarasinghe, Colombo, Sri Lanka
 * All Rights Reserved.
 *
 */
package com.example.githubfollowers.apiconnect

import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    //suspend why? coroutin
    @GET("users")
    suspend fun getDataFromAPI(@Query("q")query: String,
                       @Query("ref")queryRef: String,
                       @Query("s")queryS: String,
                       @Query("type")queryType: String,
                       @Query("per_page")perPage: Int,
                       @Query("page")page: Int,
                       @Query("client_id")clientId: String,
                       @Query("client_secret")secretKey: String
    ): GitHubFollowersList
}