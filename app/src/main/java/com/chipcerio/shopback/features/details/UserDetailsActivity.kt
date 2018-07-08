package com.chipcerio.shopback.features.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.chipcerio.shopback.GithubApp
import com.chipcerio.shopback.R
import com.chipcerio.shopback.data.dto.User
import com.chipcerio.shopback.di.UserInjection
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetailsActivity : AppCompatActivity() {

    companion object {
        const val TAG = "UserDetailsActivity"
        const val EXTRA_LOGIN = "extra:login"
    }

    private lateinit var disposables: CompositeDisposable

    private lateinit var viewModel: UserViewModel

    private lateinit var githubLogin: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        disposables = CompositeDisposable()
        githubLogin = intent.getStringExtra(EXTRA_LOGIN)

        val app = application as GithubApp
        val api = app.getApiService()
        viewModel = UserViewModel(UserInjection.providesUserSource(api))
    }

    override fun onStart() {
        super.onStart()
        disposables.add(viewModel.getUser(githubLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mapUserToViews(it)
            }, {
                Log.e(TAG, "Something went wrong", Throwable("Error in getting github user"))
            }))
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    private fun mapUserToViews(user: User) {
        Picasso.get().load(user.avatar_url).into(profile_image)
        name.text = user.name
        bio.text = user.bio
        login.text = user.login
        location.text = user.location
        blog.text = user.blog
        if (user.site_admin) {
            staff.visibility = View.VISIBLE
        } else {
            staff.visibility = View.GONE
        }
        clear.setOnClickListener {
            this@UserDetailsActivity.finish()
        }
    }
}
