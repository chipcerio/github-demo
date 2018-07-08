package com.chipcerio.shopback.di

import com.chipcerio.shopback.api.ApiService
import com.chipcerio.shopback.data.source.UserSource
import com.chipcerio.shopback.data.source.remote.UserRemoteSource

class UserInjection {
    companion object {
        fun providesUserSource(apiService: ApiService): UserSource = UserRemoteSource(apiService)
    }
}