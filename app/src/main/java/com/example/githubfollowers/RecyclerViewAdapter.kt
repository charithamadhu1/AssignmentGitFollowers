/*
 *
 * Copyright © 2022 Charitha Amarasinghe, Colombo, Sri Lanka
 * All Rights Reserved.
 *
 */
package com.example.githubfollowers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubfollowers.apiconnect.ItemsData

class RecyclerViewAdapter :
    PagingDataAdapter<ItemsData, RecyclerViewAdapter.MyViewHolder>(DiffUtilCallBack()) {
    private lateinit var onItemClickListener: OnItemClickListener

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
        holder.itemView.setOnClickListener(View.OnClickListener {
            onItemClickListener.onItemClick(
                getItem(position)!!.login,
                getItem(position)!!.avatar_url
            )
        })
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)
        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val mImageView: ImageView = view.findViewById(R.id.imageView)
        val mLoginName: TextView = view.findViewById(R.id.textviewName)
        val mLoginId: TextView = view.findViewById(R.id.textviewDescription)

        fun bind(data: ItemsData) {
            mLoginName.text = data.login
            mLoginId.text = data.id.toString()

            Glide.with(mImageView)
                .load(data.avatar_url)
                .circleCrop()
                .into(mImageView)
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<ItemsData>() {
        override fun areItemsTheSame(oldItem: ItemsData, newItem: ItemsData): Boolean {
            return oldItem.login == newItem.login
        }

        override fun areContentsTheSame(oldItem: ItemsData, newItem: ItemsData): Boolean {
            return oldItem.login == newItem.login
                    && oldItem.id == oldItem.id
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(login: String, listData: String?)
    }
}