package com.chipcerio.shopback.api

import com.chipcerio.shopback.data.dto.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Observable<User>

    @GET
    fun getUsersSync(@Url pageUrl: String): Call<List<User>>
}