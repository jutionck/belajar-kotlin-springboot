package com.enigmacamp.kotlin.kotlinresapi.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.enigmacamp.kotlin.kotlinresapi.entity.Customer
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer, String> {

}
