package com.enigmacamp.kotlin.kotlinresapi.model

import javax.validation.constraints.NotBlank

data class CreateCustomerRequest (

        @field:NotBlank
        val id: String?,

        @field:NotBlank
        val firstName: String?,

        @field:NotBlank
        val lastName: String?,

        @field:NotBlank
        val address: String?,
)
