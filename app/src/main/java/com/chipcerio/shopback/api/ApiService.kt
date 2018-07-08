package com.chipcerio.shopback.api

import com.chipcerio.shopback.data.dto.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    fun getUsers(): Observable<List<User>>

    @GET("users")
    fun getUsersSync(): Call<List<User>>
}