package com.chipcerio.shopback.data.source.remote

import com.chipcerio.shopback.api.ApiService
import com.chipcerio.shopback.api.GitHubResponse
import com.chipcerio.shopback.api.PageLinks
import com.chipcerio.shopback.api.PageResponse
import com.chipcerio.shopback.data.source.UsersSource
import io.reactivex.Observable

class UsersRemoteSource(private val api: ApiService) : UsersSource {

    companion object {
        const val TAG = "UsersRemoteSource"
    }

    override fun getUsers(pageUrl: String): Observable<PageResponse> {
        val page = if (pageUrl.isNotEmpty()) {
            pageUrl
        } else {
            "https://api.github.com/users?per_page=20&since=0"
        }

        return Observable.create<PageResponse> {
            val res = api.getUsersSync(page).execute()
            if (res.isSuccessful) {
                res.body()?.let { users ->
                    val githubResponse = GitHubResponse(res, res.body())
                    val pageLinks = PageLinks(githubResponse)
                    val pageResponse = PageResponse(pageLinks.next, users)
                    it.onNext(pageResponse)
                }
            } else it.onError(Throwable("Error"))
        }
    }
}