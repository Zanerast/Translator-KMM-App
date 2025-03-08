package com.astrick.translator.translate.data.translate

@kotlinx.serialization.Serializable
data class TranslateDto(
    @SerialName("q")
    val textToTranslate: String,
    @SerialName("source")
    val sourceLanguageCode: String,
    @SerialName("target")
    val targetLanguageCode: String,
)