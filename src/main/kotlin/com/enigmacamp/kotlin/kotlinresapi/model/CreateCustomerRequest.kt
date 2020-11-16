package com.enigmacamp.kotlin.kotlinresapi.model

data class CreateCustomerRequest (

        val id: String,

        val firstName: String,

        val lastName: String,

        val address: String,
)
