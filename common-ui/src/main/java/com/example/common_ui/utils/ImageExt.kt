package com.example.common_ui.utils

fun String.setImage(imageType: ImageType = ImageType.ORIGINAL) : String {
    val imageUrl = "https://image.tmdb.org/t/p/"

    val trimmedResponse = this.trim()
    if (trimmedResponse.isEmpty() || !trimmedResponse.startsWith("/")) {
        throw IllegalArgumentException("Invalid image response: $this")
    }
    return "$imageUrl${imageType.path}$trimmedResponse"
}