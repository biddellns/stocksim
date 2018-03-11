package com.nexus.stocksim

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StocksimApplication

fun main(args: Array<String>) {
    runApplication<StocksimApplication>(*args)
}
