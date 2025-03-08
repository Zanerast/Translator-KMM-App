package com.astrick.translator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform