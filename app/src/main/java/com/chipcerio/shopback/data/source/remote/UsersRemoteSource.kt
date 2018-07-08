package com.chipcerio.shopback.data.source.remote

import android.util.Log
import com.chipcerio.shopback.api.ApiService
import com.chipcerio.shopback.api.GitHubResponse
import com.chipcerio.shopback.api.PageLinks
import com.chipcerio.shopback.data.dto.User
import com.chipcerio.shopback.data.source.UsersSource
import io.reactivex.Observable

class UsersRemoteSource(private val api: ApiService) : UsersSource {

    companion object {
        const val TAG = "UsersRemoteSource"
    }

    override fun getUsers(): Observable<List<User>> {
        return Observable.create<List<User>> {
            val res = api.getUsersSync().execute()
            if (res.isSuccessful) {
                res.body()?.let { users ->
                    it.onNext(users)
                }

                res.headers().names().forEach {
                    Log.d(TAG, "names: $it")
                }

                val githubResponse = GitHubResponse(res, res.body())
                val headerVal = githubResponse.getHeader("Link")
                Log.d(TAG, "link: $headerVal")

                val pageLinks = PageLinks(githubResponse)
                Log.d(TAG, "next: ${pageLinks.next}")

            } else it.onError(Throwable("Error"))
        }
    }
}