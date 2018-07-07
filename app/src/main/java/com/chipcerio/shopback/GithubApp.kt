package com.chipcerio.shopback

import android.app.Application
import com.chipcerio.shopback.api.ApiService
import com.chipcerio.shopback.config.ApiServiceProvider

class GithubApp : Application() {

    private lateinit var apiService: ApiService

    override fun onCreate() {
        super.onCreate()
        apiService = ApiServiceProvider.provides()
    }

    fun getApiService(): ApiService = apiService
}