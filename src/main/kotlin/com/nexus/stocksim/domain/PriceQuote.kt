package com.nexus.stocksim.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.math.BigDecimal
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@EntityListeners(AuditingEntityListener::class)
@JsonIgnoreProperties(value = ["createdAt", "updatedAt"], allowGetters = true)
class PriceQuote: Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: UUID? = null

    @NotBlank
    @NotNull
    var symbol: String? = null

    @NotNull
    var quantity: Int = 0

    @NotNull
    var price: BigDecimal? = null

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    val createdAt: Date? = null

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    val updatedAt: Date? = null

    constructor() { }

    constructor(symbol: String, quantity: Int, price: BigDecimal) {
        this.symbol = symbol
        this.quantity = quantity
        this.price = price
    }


}
