package com.nexus.stocksim

import com.nexus.stocksim.domain.PriceQuote
import com.nexus.stocksim.repository.PriceQuoteRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class MyCommandLineRunner : CommandLineRunner {

    @Autowired
    private lateinit var priceQuoteRepository: PriceQuoteRepository

    override fun run(vararg args: String) {
        log.info(".......................")
        log.info("Adding GOOG")

        val goog = PriceQuote("GOOO", 50, BigDecimal(1.11))
        priceQuoteRepository.save(goog)
        log.info("Added GOOG")

        log.info("Finding all PriceQuotes")
        priceQuoteRepository.findAll().forEach({ quote -> log.info(quote.toString()) })

    }

    companion object {
        val log: Logger =
                LoggerFactory.getLogger(MyCommandLineRunner::class.java)
    }
}