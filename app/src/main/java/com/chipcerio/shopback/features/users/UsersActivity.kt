package com.chipcerio.shopback.features.users

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.chipcerio.shopback.GithubApp
import com.chipcerio.shopback.R
import com.chipcerio.shopback.di.UsersInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UsersActivity : AppCompatActivity() {

    companion object {
        const val TAG = "UsersActivity"
    }

    private lateinit var viewModel: UsersViewModel

    private lateinit var disposables: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        val app = application as GithubApp
        val api = app.getApiService()
        viewModel = UsersViewModel(UsersInjection.providesUsersSource(api))
        disposables = CompositeDisposable()
    }

    override fun onStart() {
        super.onStart()
        disposables.add(
            viewModel.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d(TAG ,"size: ${it.size}")
                }, {
                    Log.e(TAG, "Error:", it)
                }))
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }
}
