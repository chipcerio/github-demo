package com.chipcerio.shopback.data.source.remote

import com.chipcerio.shopback.api.ApiService
import com.chipcerio.shopback.data.dto.User
import com.chipcerio.shopback.data.source.UsersSource
import io.reactivex.Observable

class UsersRemoteSource(private val api: ApiService) : UsersSource {

    override fun getUsers(): Observable<List<User>> {
        return api.getUsers()
    }
}