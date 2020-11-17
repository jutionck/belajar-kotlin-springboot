package com.enigmacamp.kotlin.kotlinresapi.service

import com.enigmacamp.kotlin.kotlinresapi.model.CreateCustomerRequest
import com.enigmacamp.kotlin.kotlinresapi.model.CustomerResponse
import com.enigmacamp.kotlin.kotlinresapi.model.ListCustomerRequest
import com.enigmacamp.kotlin.kotlinresapi.model.UpdateCustomerRequest

interface CustomerService {

    fun create(createCustomerRequest: CreateCustomerRequest): CustomerResponse

    fun get(id: String): CustomerResponse

    fun update(id: String, updateCustomerRequest: UpdateCustomerRequest): CustomerResponse

    fun delete(id: String)

    fun list(listCustomerRequest: ListCustomerRequest): List<CustomerResponse>


}

