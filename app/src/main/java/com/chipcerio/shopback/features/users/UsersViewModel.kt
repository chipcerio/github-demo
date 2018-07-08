package com.chipcerio.shopback.features.users

import com.chipcerio.shopback.api.PageResponse
import com.chipcerio.shopback.data.source.UsersSource
import io.reactivex.Observable

class UsersViewModel(private val usersSource: UsersSource) {

    fun getUsers(pageUrl: String): Observable<PageResponse> {
        return usersSource.getUsers(pageUrl)
    }
}