package com.chipcerio.shopback.di

import com.chipcerio.shopback.api.ApiService
import com.chipcerio.shopback.data.source.UsersSource
import com.chipcerio.shopback.data.source.remote.UsersRemoteSource

class UsersInjection {
    companion object {
        fun providesUsersSource(apiService: ApiService): UsersSource = UsersRemoteSource(apiService)
    }
}