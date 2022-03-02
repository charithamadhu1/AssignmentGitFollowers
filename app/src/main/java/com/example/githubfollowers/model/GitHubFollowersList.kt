/*
 *
 * Copyright © 2022 Charitha Amarasinghe, Colombo, Sri Lanka
 * All Rights Reserved.
 *
 */
package com.example.githubfollowers.apiconnect

data class GitHubFollowersList(val total_count: Int, val incomplete_results :Boolean, val items: List<ItemsData> )
data class ItemsData( val login: String, val id: Int, val avatar_url: String)