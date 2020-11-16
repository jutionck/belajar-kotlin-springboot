package com.enigmacamp.kotlin.kotlinresapi.model

import java.util.*

data class CustomerResponse (

        val id: String,

        val firstName: String,

        val lastName: String,

        val address: String,

        val createdAt: Date,

        val updatedAt: Date?
)
