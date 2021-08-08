package com.ar.homenetflixtv.model

data class Video(private val title: String, private val uri: String) {

    fun getTitle(): String {
        return title
    }

    fun getUri(): String {
        return uri
    }

    companion object {
        val videos = listOf(Video("bbb", "http://localhost:1935/foo"),
            Video("bbb", "http://localhost:1935/bar"))}

}
