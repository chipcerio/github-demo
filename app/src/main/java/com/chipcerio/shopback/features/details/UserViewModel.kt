package com.chipcerio.shopback.features.details

import com.chipcerio.shopback.data.dto.User
import com.chipcerio.shopback.data.source.UserSource
import io.reactivex.Observable

class UserViewModel(private val userSource: UserSource) {

    fun getUser(login: String): Observable<User> {
        return userSource.getUser(login)
    }
}