package com.picpay.desafio.android.user

import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.desafio.domain.model.UserModel
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListItemViewHolder(
    private val binding:  ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: UserModel) {
        binding.user = user
        itemView.progressBar.visibility = View.VISIBLE
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(itemView.picture, object : Callback {
                override fun onSuccess() {
                    itemView.progressBar.visibility = View.GONE
                }
                override fun onError(e: Exception?) {
                    itemView.progressBar.visibility = View.GONE
                }
            })
    }
}