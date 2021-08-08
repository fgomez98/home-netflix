package com.ar.homenetflixtv.model

enum class Categories(private val value: String) {

    ALL("All");

    fun getValue(): String {
        return value
    }

}