package com.nexus.stocksim.controller

import com.nexus.stocksim.domain.PriceQuote
import com.nexus.stocksim.generator.PriceQuoteGenerator
import com.nexus.stocksim.repository.PriceQuoteRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/priceQuotes")
class PriceQuoteController(val priceQuoteGenerator: PriceQuoteGenerator, val priceQuoteRepository: PriceQuoteRepository) {

    @GetMapping("/")
    fun priceQuoteList(): List<PriceQuote> = priceQuoteGenerator.priceQuotes

    @GetMapping("{uuid}")
    fun priceQuoteDetail(@PathVariable uuid: UUID) = priceQuoteRepository.findById(uuid)
}