package com.chipcerio.shopback.data.source.remote

import com.chipcerio.shopback.api.ApiService
import com.chipcerio.shopback.data.dto.User
import com.chipcerio.shopback.data.source.UserSource
import io.reactivex.Observable

class UserRemoteSource(private val api: ApiService) : UserSource {

    override fun getUser(login: String): Observable<User> {
        return api.getUser(login)
    }
}