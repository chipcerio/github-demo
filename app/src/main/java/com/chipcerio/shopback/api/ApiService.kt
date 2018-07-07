package com.chipcerio.shopback.api

import com.chipcerio.shopback.data.dto.User
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    fun getUsers(): Observable<List<User>>
}