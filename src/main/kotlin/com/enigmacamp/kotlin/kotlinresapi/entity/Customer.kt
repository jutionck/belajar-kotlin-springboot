package com.enigmacamp.kotlin.kotlinresapi.entity

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "m_customer")
data class Customer (

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(
        name = "UUID",
                strategy = "org.hibernate.id.UUIDGenerator",
        )
        @Column(name = "id", updatable = false, nullable = false)
        val id: String,

        @Column(name = "first_name")
        val firstName: String,

        @Column(name = "last_name")
        val lastName: String,

        @Column(name = "address")
        val address: String,

        @Column(name = "created_at")
        val createdAt: Date,

        @Column(name = "updated_at")
        val updatedAt: Date?
)
