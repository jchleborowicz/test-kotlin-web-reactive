package pl.jch.tests.kotlin.web_reactive.controller

import org.springframework.web.bind.annotation.*
import pl.jch.tests.kotlin.web_reactive.model.Profile
import pl.jch.tests.kotlin.web_reactive.repository.ProfileRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/profiles")
class ProfileController(private val repository: ProfileRepository) {

    @PostMapping
    fun save(@RequestBody profile: Profile): Mono<Profile> = repository.save(profile)

    @GetMapping
    fun list(): Flux<Profile> = repository.findAll()
}
