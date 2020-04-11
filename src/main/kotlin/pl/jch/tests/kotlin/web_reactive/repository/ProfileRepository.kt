package pl.jch.tests.kotlin.web_reactive.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import pl.jch.tests.kotlin.web_reactive.model.Profile

@Repository
interface ProfileRepository: ReactiveCrudRepository<Profile, Long>
