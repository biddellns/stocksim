package com.nexus.stocksim.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.math.BigDecimal
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class PriceQuote (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: UUID,

        @NotBlank
        @NotNull
        val symbol: String = "",

        @NotNull
        val quantity: Int = 0,

        @NotNull
        val price: BigDecimal = BigDecimal(0),

        @Column(nullable = false, updatable = false)
        @Temporal(TemporalType.TIMESTAMP)
        @CreatedDate
        val create_date: Date = Date(),

        @Column(nullable = false, updatable = false)
        @Temporal(TemporalType.TIMESTAMP)
        @LastModifiedDate
        val update_date: Date = Date()
)
