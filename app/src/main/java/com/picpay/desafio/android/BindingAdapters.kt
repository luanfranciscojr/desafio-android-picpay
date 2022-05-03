package com.picpay.desafio.android

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.desafio.domain.model.UserModel
import com.picpay.desafio.android.user.UserListAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

@BindingAdapter("userList")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<UserModel>?) {
    val adapter = recyclerView.adapter as UserListAdapter
    adapter.submitList(data)
}

