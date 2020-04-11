package pl.jch.tests.kotlin.web_reactive.controller

import org.springframework.web.bind.annotation.*
import pl.jch.tests.kotlin.web_reactive.model.AverageHealthStatus
import pl.jch.tests.kotlin.web_reactive.model.HealthRecord
import pl.jch.tests.kotlin.web_reactive.repository.HealthRecordRepository
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/health")
class HealthRecordController(private val repository: HealthRecordRepository) {

    @PostMapping("/{profileId}/record")
    fun storeHealthRecord(@RequestBody record: HealthRecord): Mono<HealthRecord> =
            this.repository.save(record)

    @GetMapping("/{profileId}/avg")
    fun fetchHealthRecordAverage(@PathVariable("profileId") profileId: Long): Mono<AverageHealthStatus> =
            repository.findByProfileId(profileId)
                    .reduce(
                            AverageHealthStatus(cnt = 0, temperature = 0.0, bloodPressure = 0.0, heartRate = 0.0),
                            { s, r ->
                                AverageHealthStatus(
                                        cnt = s.cnt + 1,
                                        temperature = s.temperature + r.temperature,
                                        bloodPressure = s.bloodPressure + r.bloodPressure,
                                        heartRate = s.heartRate + r.heartRate
                                )
                            }
                    )
                    .map { s ->
                        AverageHealthStatus(
                                cnt = s.cnt,
                                temperature = if (s.cnt == 0) 0.0 else s.temperature / s.cnt,
                                bloodPressure = if (s.cnt == 0) 0.0 else s.bloodPressure / s.cnt,
                                heartRate = if (s.cnt == 0) 0.0 else s.heartRate / s.cnt
                        )
                    }
}
