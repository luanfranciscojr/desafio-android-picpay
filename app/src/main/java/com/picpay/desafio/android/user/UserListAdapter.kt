package com.picpay.desafio.android.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.desafio.domain.model.UserModel
import com.picpay.desafio.android.databinding.ListItemUserBinding

class UserListAdapter :  ListAdapter<UserModel, UserListItemViewHolder>(
    UserListDiffCallback
) {

    companion object UserListDiffCallback : DiffUtil.ItemCallback<UserModel>() {

        override fun areItemsTheSame(oldItemPosition: UserModel, newItemPosition: UserModel): Boolean {
            return oldItemPosition == newItemPosition
        }

        override fun areContentsTheSame(oldItemPosition: UserModel, newItemPosition: UserModel): Boolean {
            return oldItemPosition.name == newItemPosition.name
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        return UserListItemViewHolder(
            ListItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

}