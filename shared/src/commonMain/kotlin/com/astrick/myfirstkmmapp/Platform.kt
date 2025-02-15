package com.astrick.myfirstkmmapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform