package com.nexus.stocksim

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class StocksimApplication

fun main(args: Array<String>) {
    runApplication<StocksimApplication>(*args)
}
