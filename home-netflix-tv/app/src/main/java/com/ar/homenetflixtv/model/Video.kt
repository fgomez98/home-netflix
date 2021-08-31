package com.ar.homenetflixtv.model

data class Video(
    val title: String,
    val uri: String,
    val imageUrl: String
) {

    companion object {
        val videos = listOf(
            Video(
                "bbb",
                "http://localhost:1935/foo",
                "https://iv1.lisimg.com/image/13684264/382full-big-buck-bunny.jpg"
            ),
            Video(
                "bbb",
                "http://localhost:1935/bar",
                "https://iv1.lisimg.com/image/13684264/382full-big-buck-bunny.jpg"
            )
        )
    }

}
