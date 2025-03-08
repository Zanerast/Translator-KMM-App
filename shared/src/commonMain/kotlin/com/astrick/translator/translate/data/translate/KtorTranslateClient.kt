package com.astrick.translator.translate.data.translate

import com.astrick.translator.NetworkConstants
import com.astrick.translator.core.domain.language.Language
import com.astrick.translator.translate.domain.translate.TranslateClient
import com.astrick.translator.translate.domain.translate.TranslateError
import com.astrick.translator.translate.domain.translate.TranslateException

class KtorTranslateClient(
    private val httpClient: HttpClient
): TranslateClient {

    override suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String {
        val result = try {
            httpClient.post {
                url(NetworkConstants.BASE_URL + "/translate")
                contentType(ContentType.Application.Json)
                setBody(
                    TranslateDto(
                        textToTranslate = fromText,
                        sourceLanguageCode = fromLanguage.langCode,
                        targetLanguageCode = toLanguage.langCode
                    )
                )
            }
        } catch (e: IOException) {
            throw TranslateException(TranslateError.SERVER_ERROR)
        }
        when(result.status.value) {
            in 200..299 -> Unit
            500 -> throw TranslateException(TranslateError.SERVER_ERROR)
            in 400..499 -> throw TranslateException(TranslateError.UNKNOWN_ERROR)
        }
        return try {
            result.body<TranslatedDto().translateText
        } catch (e: Exception) {
            throw TranslateException(TranslateError.SERVER_ERROR)
        }
    }

}