/*
 *
 * Copyright © 2022 Charitha Amarasinghe, Colombo, Sri Lanka
 * All Rights Reserved.
 *
 */
package com.example.githubfollowers.apiconnect

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstanceRetrofit {
    companion object {
        val baseURL = "https://api.github.com/search/"

        fun getRetroInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}