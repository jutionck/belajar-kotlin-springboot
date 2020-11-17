package com.enigmacamp.kotlin.kotlinresapi.controller

import com.enigmacamp.kotlin.kotlinresapi.model.*
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

    @PutMapping(
            value = ["/{customerId}"],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun updateCustomer(@PathVariable("customerId") id: String,
                       @RequestBody updateCustomerRequest: UpdateCustomerRequest): WebResponse<CustomerResponse> {
        val customerResponse = customerService.update(id, updateCustomerRequest)
        return WebResponse(
                code = 200,
                status = "OK",
                data = customerResponse
        )
    }

    @DeleteMapping(
            value = ["/{customerId}"],
            produces = ["application/json"]
    )
    fun deleteCustomer(@PathVariable("customerId") id: String): WebResponse<String> {
        customerService.delete(id);
        return WebResponse(
                code = 200,
                status = "OK",
                data = "$id Success Deleted"
        )
    }

    @GetMapping(
            value = [""],
            produces = ["application/json"]
    )
    fun listCustomer(@RequestParam(value = "size", defaultValue = "10") size: Int,
                     @RequestParam(value = "page", defaultValue = "0") page: Int): WebResponse<List<CustomerResponse>> {
        val request = ListCustomerRequest(page = page, size = size);
        val responses = customerService.list(request)
        return WebResponse(
                code = 200,
                status = "OK",
                data = responses
        )
    }
}
