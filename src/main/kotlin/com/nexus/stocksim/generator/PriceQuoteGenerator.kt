package com.nexus.stocksim.generator

import com.nexus.stocksim.domain.PriceQuote
import com.nexus.stocksim.repository.PriceQuoteRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.concurrent.ThreadLocalRandom
import javax.annotation.PostConstruct

@Component
class PriceQuoteGenerator() {
    private val MAX_QUANTITY: Int = 5000
    private val MAX_PRICE: Long = 1500L

    private val STOCK_SYMBOLS: Array<String> = arrayOf("GOOG", "AAPL", "EG", "ACT")
    private val RAND: ThreadLocalRandom = ThreadLocalRandom.current()

    @Autowired
    private lateinit var priceQuoteRepository: PriceQuoteRepository

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
            val newPrice = BigDecimal(RAND.nextLong(MAX_PRICE))
            priceQuote.price = newPrice
            priceQuote.quantity = newQuantity

            log.info(priceQuote.toString())
        }
    }

    companion object {
        val log = LoggerFactory.getLogger(PriceQuoteGenerator::class.java)
    }
}