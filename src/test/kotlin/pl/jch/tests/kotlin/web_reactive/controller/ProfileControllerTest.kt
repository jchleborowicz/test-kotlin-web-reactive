package pl.jch.tests.kotlin.web_reactive.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import pl.jch.tests.kotlin.web_reactive.model.Profile
import java.time.LocalDateTime

//todo correct test
@Disabled
@SpringBootTest
internal class ProfileControllerTest {
    @Autowired
    lateinit var controller: ProfileController

    @Autowired
    lateinit var mapper: ObjectMapper

    lateinit var client: WebTestClient
    lateinit var profile: String

    @BeforeEach
    fun setup() {
        this.client = WebTestClient.bindToController(controller)
                .build()
        val profileObject = Profile(
                id = null,
                firstName = "kotlin",
                lastName = "reactive",
                birthDate = LocalDateTime.now()
        )
        this.profile = mapper.writeValueAsString(profileObject)
    }

    @Test
    fun whenRequestProfile_thenStatusShouldBeOk() {
        this.client.post()
                .uri("/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(this.profile)
                .exchange()
                .expectStatus().isOk
    }

    @Test
    fun whenRequestProfile_thenIdShouldBeNotNull() {
        this.client.post()
                .uri("/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(profile)
                .exchange()
                .expectBody()
                .jsonPath("$.id").isNotEmpty
    }
}
