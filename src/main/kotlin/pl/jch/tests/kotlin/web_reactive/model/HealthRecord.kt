package pl.jch.tests.kotlin.web_reactive.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table
data class HealthRecord(
        @Id var id: Long?,
        var profileId: Long?,
        var temperature: Double,
        var bloodPressure: Double,
        var heartRate: Double,
        var date: LocalDate
)
