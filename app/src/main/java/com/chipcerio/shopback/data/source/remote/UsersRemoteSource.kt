package com.chipcerio.shopback.data.source.remote

import com.chipcerio.shopback.data.dto.User
import com.chipcerio.shopback.data.source.UsersSource
import io.reactivex.Observable

class UsersRemoteSource : UsersSource {
    override fun getUsers(): Observable<List<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}