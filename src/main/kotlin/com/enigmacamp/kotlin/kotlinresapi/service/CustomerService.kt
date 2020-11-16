package com.enigmacamp.kotlin.kotlinresapi.service

import com.enigmacamp.kotlin.kotlinresapi.model.CreateCustomerRequest
import com.enigmacamp.kotlin.kotlinresapi.model.CustomerResponse

interface CustomerService {

    fun create(createCustomerRequest: CreateCustomerRequest): CustomerResponse

    fun get(id: String): CustomerResponse
}

