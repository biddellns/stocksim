package com.nexus.stocksim.generator.strategy

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
interface IUpdatePriceStrategy {
    fun generateNewPrice(currentPrice: BigDecimal?): BigDecimal
}