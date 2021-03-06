package com.chipcerio.shopback.features.users

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.chipcerio.shopback.GithubApp
import com.chipcerio.shopback.R
import com.chipcerio.shopback.data.dto.User
import com.chipcerio.shopback.di.UsersInjection
import com.chipcerio.shopback.features.details.UserDetailsActivity
import com.chipcerio.shopback.features.users.UsersAdapter.OnEndReachedListener
import com.chipcerio.shopback.features.users.UsersAdapter.OnUserSelectedListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.android.synthetic.main.toolbar.*

class UsersActivity : AppCompatActivity(), OnUserSelectedListener, OnEndReachedListener {

    companion object {
        const val TAG = "UsersActivity"
    }

    private lateinit var viewModel: UsersViewModel

    private lateinit var disposables: CompositeDisposable

    private lateinit var adapter: UsersAdapter

    private val paginate = PublishSubject.create<String>()

    private var pageUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        toolbarView.title = "Github Users"

        val app = application as GithubApp
        val api = app.getApiService()
        viewModel = UsersViewModel(UsersInjection.providesUsersSource(api))

        adapter = UsersAdapter(mutableListOf(), this, this)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@UsersActivity)
            adapter = this@UsersActivity.adapter
        }

        disposables = CompositeDisposable()

        disposables.add(
            paginate.observeOn(Schedulers.io())
                .concatMap {
                    viewModel.getUsers(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    Log.d(TAG, "next: ${it.next}")
                    pageUrl = it.next
                }
                .subscribe({
                    setGithubUsers(it.users)
                }, {
                    Log.e(TAG, "Error:", it)
                })
        )

        paginate.onNext(pageUrl)
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    override fun onUserSelected(user: User) {
        val intent = Intent(this, UserDetailsActivity::class.java)
        intent.putExtra(UserDetailsActivity.EXTRA_LOGIN, user.login)
        startActivity(intent)
    }

    override fun onEndReached() {
        Toast.makeText(this, "Fetching users...", Toast.LENGTH_SHORT).show()
        paginate.onNext(pageUrl)
    }

    private fun setGithubUsers(users: List<User>) {
        users.forEach {
            adapter.add(users.indexOf(it), it)
        }
    }
}
