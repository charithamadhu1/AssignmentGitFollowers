/*
 *
 * Copyright © 2022 Charitha Amarasinghe, Colombo, Sri Lanka
 * All Rights Reserved.
 *
 */
package com.example.githubfollowers

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubfollowers.apiconnect.ItemsData
import com.example.githubfollowers.utils.KeyStore.Companion.KEY_AVATAR_LINK
import com.example.githubfollowers.utils.KeyStore.Companion.KEY_USER_NAME

class MyCustomFragment : Fragment() {
    // Declaring TextView from the custom fragment layout
    private lateinit var mTextViewUserName: TextView
    private lateinit var mImageOfTheUser: ImageView
    private lateinit var buttonBack: Button
    private var listData: List<ItemsData>? = null

    fun setUpdatedData(listData: List<ItemsData>) {
        this.listData = listData
    }

    // Override function when the view is being created
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflates the custom fragment layout
        val view: View = inflater.inflate(R.layout.my_custom_fragment, container, false)

        // Finds the TextView in the custom fragment
        mTextViewUserName = view.findViewById<View>(R.id.nameTextView) as TextView
        mImageOfTheUser = view.findViewById<View>(R.id.imageViewAvatar) as ImageView
        buttonBack = view.findViewById<View>(R.id.button_back) as Button

        // Gets the data from the passed bundle
        val bundle = arguments
        val userName = bundle!!.getString(KEY_USER_NAME)
        val avatar_url = bundle.getString(KEY_AVATAR_LINK)

        // Sets the derived data (type String) in the TextView
        mTextViewUserName.text = userName
        Glide.with(mImageOfTheUser)
            .load(avatar_url)
            .apply(RequestOptions.centerCropTransform())
            .into(mImageOfTheUser)


        buttonBack.setOnClickListener(View.OnClickListener {
            val intent = Intent(getActivity(), MainActivity::class.java)
            getActivity()?.startActivity(intent)
        })

        return view
    }

}