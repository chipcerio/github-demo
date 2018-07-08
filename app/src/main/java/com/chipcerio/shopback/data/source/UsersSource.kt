package com.chipcerio.shopback.data.source

import com.chipcerio.shopback.api.PageResponse
import io.reactivex.Observable

interface UsersSource {
    fun getUsers(pageUrl: String): Observable<PageResponse>
}