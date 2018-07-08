package com.chipcerio.shopback.features.users

import com.chipcerio.shopback.data.dto.User
import com.chipcerio.shopback.data.source.UsersSource
import io.reactivex.Observable

class UsersViewModel(private val usersSource: UsersSource) {

    fun getUsers(pageUrl: String): Observable<List<User>> {
        return usersSource.getUsers(pageUrl)
    }
}