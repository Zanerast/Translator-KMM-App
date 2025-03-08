package com.astrick.translator.translate.data.remote

actual class HttpClientFactory {
    actual fun create(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }
        }
    }
}