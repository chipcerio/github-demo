package com.chipcerio.shopback.data.source.remote

import android.util.Log
import com.chipcerio.shopback.api.ApiService
import com.chipcerio.shopback.data.dto.User
import com.chipcerio.shopback.data.source.UsersSource
import io.reactivex.Observable

class UsersRemoteSource(private val api: ApiService) : UsersSource {

    companion object {
        const val TAG = "UsersRemoteSource"
    }

    override fun getUsers(): Observable<List<User>> {
        return Observable.create<List<User>> {
            val call = api.getUsersSync().execute()
            if (call.isSuccessful) {
                call.body()?.let { users ->
                    it.onNext(users)
                }

                Log.d(TAG, "headers: ${call.headers().size()}")

            } else it.onError(Throwable("Error"))
        }
    }
}