package com.chipcerio.shopback.data.source

import com.chipcerio.shopback.data.dto.User
import io.reactivex.Observable

interface UsersSource {
    fun getUsers(): Observable<List<User>>
}