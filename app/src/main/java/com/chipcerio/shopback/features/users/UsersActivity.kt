package com.chipcerio.shopback.features.users

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.chipcerio.shopback.GithubApp
import com.chipcerio.shopback.R
import com.chipcerio.shopback.data.dto.User
import com.chipcerio.shopback.di.UsersInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.android.synthetic.main.toolbar.*

class UsersActivity : AppCompatActivity(), UsersAdapter.OnUserSelectedListener {

    companion object {
        const val TAG = "UsersActivity"
    }

    private lateinit var viewModel: UsersViewModel

    private lateinit var disposables: CompositeDisposable

    private lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        toolbarView.title = "Github Users"

        val app = application as GithubApp
        val api = app.getApiService()
        viewModel = UsersViewModel(UsersInjection.providesUsersSource(api))
        disposables = CompositeDisposable()

        adapter = UsersAdapter(mutableListOf(), this)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@UsersActivity)
            adapter = this@UsersActivity.adapter
        }
    }

    override fun onStart() {
        super.onStart()
        disposables.add(
            viewModel.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    setGithubUsers(it)
                }, {
                    Log.e(TAG, "Error:", it)
                }))
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    override fun onUserSelected(user: User) {
        Toast.makeText(this, user.login, Toast.LENGTH_SHORT).show()
    }

    private fun setGithubUsers(users: List<User>) {
        users.forEach {
            adapter.add(users.indexOf(it), it)
        }
    }
}
