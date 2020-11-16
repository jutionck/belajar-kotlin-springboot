package com.enigmacamp.kotlin.kotlinresapi.service.impl

import com.enigmacamp.kotlin.kotlinresapi.entity.Customer
import com.enigmacamp.kotlin.kotlinresapi.model.CreateCustomerRequest
import com.enigmacamp.kotlin.kotlinresapi.model.CustomerResponse
import com.enigmacamp.kotlin.kotlinresapi.repository.CustomerRepository
import com.enigmacamp.kotlin.kotlinresapi.service.CustomerService
import com.enigmacamp.kotlin.kotlinresapi.utils.ValidationUtil
import com.enigmacamp.kotlin.kotlinresapi.utils.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerServiceImpl(
        val customerRepository: CustomerRepository,
        val validationUtil: ValidationUtil
    ): CustomerService {

    override fun create(createCustomerRequest: CreateCustomerRequest): CustomerResponse {

        validationUtil.validate(createCustomerRequest);

        val customer = Customer (
                id = createCustomerRequest.id!!,
                firstName = createCustomerRequest.firstName!!,
                lastName = createCustomerRequest.lastName!!,
                address = createCustomerRequest.address!!,
                createdAt = Date(),
                updatedAt = null
        )

        customerRepository.save(customer);

        return convertCustomerToCustomerResponse(customer);
    }

    override fun get(id: String): CustomerResponse {
        val customer = customerRepository.findByIdOrNull(id);
        if(customer == null) {
            throw NotFoundException();
        } else {
            return convertCustomerToCustomerResponse(customer);
        }
    }

    private fun convertCustomerToCustomerResponse(customer: Customer): CustomerResponse {
        return CustomerResponse(
                id = customer.id,
                firstName = customer.firstName,
                lastName = customer.lastName,
                address = customer.address,
                createdAt = customer.createdAt,
                updatedAt = customer.updatedAt
        )
    }
}
