package com.enigmacamp.kotlin.kotlinresapi.controller

import com.enigmacamp.kotlin.kotlinresapi.model.CreateCustomerRequest
import com.enigmacamp.kotlin.kotlinresapi.model.CustomerResponse
import com.enigmacamp.kotlin.kotlinresapi.model.WebResponse
import com.enigmacamp.kotlin.kotlinresapi.service.CustomerService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/customers"])
class CustomerController(val customerService: CustomerService) {

    @PostMapping(
            value = [""],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun createCustomer(@RequestBody body: CreateCustomerRequest): WebResponse<CustomerResponse> {
        val customerResponse =  customerService.create(body)
        return WebResponse(
                code = 200,
                status = "OK",
                data = customerResponse
        )
    }

    @GetMapping(
            value = ["/{customerId}"],
            produces = ["application/json"],
    )
    fun getCustomer(@PathVariable("customerId") id: String): WebResponse<CustomerResponse> {
        val customerResponse = customerService.get(id);
        return WebResponse(
                code = 200,
                status = "OK",
                data = customerResponse
        )
    }
}
