package com.chipcerio.shopback.features.users

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chipcerio.shopback.R
import com.chipcerio.shopback.data.dto.User
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.*

class UsersAdapter(
    private val users: MutableList<User>,
    private val onUserSelectedListener: OnUserSelectedListener,
    private val onEndReachedListener: OnEndReachedListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UsersVH(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UsersVH).bind(users[position], position)
        if (position == users.lastIndex) {
            onEndReachedListener.onEndReached()
        }
    }

    override fun getItemCount(): Int = users.size

    fun add(position: Int, user: User) {
        users.add(user)
        notifyItemChanged(position)
    }

    inner class UsersVH(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(user: User, position: Int) {
            github_user.text = user.login
            Picasso.get().load(user.avatar_url).into(profile_image)
            containerView.setOnClickListener {
                onUserSelectedListener.onUserSelected(user)
            }
            if (position == 0) {
                sectionView.visibility = View.VISIBLE
            } else {
                sectionView.visibility = View.GONE
            }
        }
    }

    interface OnUserSelectedListener {
        fun onUserSelected(user: User)
    }

    interface OnEndReachedListener {
        fun onEndReached()
    }
}