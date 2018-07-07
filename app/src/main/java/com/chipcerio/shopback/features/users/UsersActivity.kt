package com.chipcerio.shopback.features.users

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.chipcerio.shopback.R
import com.chipcerio.shopback.di.UsersInjection

class UsersActivity : AppCompatActivity() {

    // provide injection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        UsersViewModel(UsersInjection.providesUsersSource())
    }

    override fun onStart() {
        super.onStart()
    }
}
