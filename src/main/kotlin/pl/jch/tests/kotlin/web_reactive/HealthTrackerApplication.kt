package pl.jch.tests.kotlin.web_reactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class HealthTrackerApplication

fun main(args: Array<String>) {
    runApplication<HealthTrackerApplication>(*args)
}
