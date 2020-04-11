package pl.jch.tests.kotlin.web_reactive.repository

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import pl.jch.tests.kotlin.web_reactive.model.HealthRecord
import reactor.core.publisher.Flux

interface HealthRecordRepository: ReactiveCrudRepository<HealthRecord, Long> {

    //at the moment query derivation is not supported with spring data r2dbc
    // so we have to write queries manually
    @Query("select p.* from health_record p where p.profile_id = :profileId")
    fun findByProfileId(profileId: Long): Flux<HealthRecord>
}
