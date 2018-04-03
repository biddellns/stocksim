package com.nexus.stocksim.generator

import com.nexus.stocksim.domain.PriceQuote
import com.nexus.stocksim.generator.constants.MAX_PRICE
import com.nexus.stocksim.generator.constants.MAX_QUANTITY
import com.nexus.stocksim.generator.strategy.IUpdatePriceStrategy
import com.nexus.stocksim.repository.PriceQuoteRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.concurrent.ThreadLocalRandom
import javax.annotation.PostConstruct

@Service
class PriceQuoteGenerator() {

    private val STOCK_SYMBOLS: Array<String> = arrayOf("GOOG", "AAPL", "EG", "ACT")
    private val RAND: ThreadLocalRandom = ThreadLocalRandom.current()

    @Autowired
    private lateinit var priceQuoteRepository: PriceQuoteRepository

    @Autowired
    @Qualifier("randomUpdatePriceStrategy")
    private lateinit var updatePriceStrategy: IUpdatePriceStrategy

    val priceQuotes: MutableList<PriceQuote> = mutableListOf()

    @PostConstruct
    private fun init() {

        for (stock in STOCK_SYMBOLS) {
            val quantity = RAND.nextInt(MAX_QUANTITY)
            val price = BigDecimal(RAND.nextLong(MAX_PRICE))

            val priceQuote = PriceQuote(stock, quantity, price)
            log.info(priceQuote.toString())
            priceQuotes.add(priceQuote)
            priceQuoteRepository.save(priceQuote)

        }
    }

    @Scheduled(fixedDelay = 2000L)
    private fun updateQuotes() {
        for (priceQuote in priceQuotes) {
            val newQuantity = RAND.nextInt(MAX_QUANTITY)
            val newPrice = updatePriceStrategy.generateNewPrice(priceQuote.price)

            priceQuote.price = newPrice
            priceQuote.quantity = newQuantity

            log.info(priceQuote.toString())
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(PriceQuoteGenerator::class.java)
    }
}