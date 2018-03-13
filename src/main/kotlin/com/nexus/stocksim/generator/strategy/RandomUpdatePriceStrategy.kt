package com.nexus.stocksim.generator.strategy

import com.nexus.stocksim.generator.constants.MAX_PRICE
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.concurrent.ThreadLocalRandom

@Component
class RandomUpdatePriceStrategy: IUpdatePriceStrategy {
    private val RAND: ThreadLocalRandom = ThreadLocalRandom.current()

    override fun generateNewPrice(currentPrice: BigDecimal?): BigDecimal {
        return BigDecimal(RAND.nextLong(MAX_PRICE))
    }
}