package com.nexus.stocksim.repository

import com.nexus.stocksim.domain.PriceQuote
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PriceQuoteRepository : JpaRepository<PriceQuote, UUID>