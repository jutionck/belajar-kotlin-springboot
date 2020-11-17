package com.enigmacamp.kotlin.kotlinresapi.service.impl

import com.enigmacamp.kotlin.kotlinresapi.entity.Customer
import com.enigmacamp.kotlin.kotlinresapi.model.CreateCustomerRequest
import com.enigmacamp.kotlin.kotlinresapi.model.CustomerResponse
import com.enigmacamp.kotlin.kotlinresapi.model.ListCustomerRequest
import com.enigmacamp.kotlin.kotlinresapi.model.UpdateCustomerRequest
import com.enigmacamp.kotlin.kotlinresapi.repository.CustomerRepository
import com.enigmacamp.kotlin.kotlinresapi.service.CustomerService
import com.enigmacamp.kotlin.kotlinresapi.utils.NotFoundException
import com.enigmacamp.kotlin.kotlinresapi.utils.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

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
        val customer = findCustomerByIdOrThrowNotFound(id);

        return convertCustomerToCustomerResponse(customer);

    }

    override fun update(id: String, updateCustomerRequest: UpdateCustomerRequest): CustomerResponse {
        val customer = findCustomerByIdOrThrowNotFound(id);

        validationUtil.validate(updateCustomerRequest);

        customer.apply {
            firstName = updateCustomerRequest.firstName!!
            lastName = updateCustomerRequest.lastName!!
            address = updateCustomerRequest.address!!
            updatedAt = Date()
        }

        customerRepository.save(customer);
        return convertCustomerToCustomerResponse(customer);
    }

    override fun delete(id: String) {
        val customer = findCustomerByIdOrThrowNotFound(id);
        customerRepository.delete(customer);
    }

    override fun list(listCustomerRequest: ListCustomerRequest): List<CustomerResponse> {
        val page = customerRepository.findAll(PageRequest.of(listCustomerRequest.page, listCustomerRequest.size));
        val customers: List<Customer> = page.get().collect(Collectors.toList())
        return customers.map { convertCustomerToCustomerResponse(it) }
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

    private fun findCustomerByIdOrThrowNotFound(id: String): Customer {
        val customer = customerRepository.findByIdOrNull(id)
        if(customer == null) {
            throw NotFoundException();
        } else {
            return customer
        }
    }
}
