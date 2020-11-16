package com.enigmacamp.kotlin.kotlinresapi.model

data class WebResponse<T> (
        val code: Int,
        val status: String,
        val data: T
)
