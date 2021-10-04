package com.ar.homenetflixtv.model

import java.io.Serializable

data class Video(
    val title: String,
    val uri: String,
    val imageUrl: String?
) : Serializable {

    data class Builder(
        var title: String,
        var uri: String,
        var imageUrl: String? = null
    ) {

        fun build() = Video(
            title,
            uri,
            imageUrl
        )

        fun title(title: String) = apply { this.title = title }

        fun videoUri(uri: String) = apply { this.uri = uri }

        fun imageUri(uri: String) = apply { this.imageUrl = uri }
    }

}
