package com.chipcerio.shopback.api

import com.chipcerio.shopback.data.dto.User

data class PageResponse(val next: String, val users: List<User>)