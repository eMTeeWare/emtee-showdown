package net.emteeware

import io.mockk.every
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Test
import io.restassured.RestAssured.given
import io.quarkiverse.test.junit.mockk.InjectMock
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.hamcrest.Matchers.`is`

import java.time.Instant
import java.util.*

@QuarkusTest
internal class UpdateResourceTest {

    @InjectMock
    @RestClient
    lateinit var traktListService: TraktListService

    @Test
    fun update() {
        val traktListEntry = TraktListEntry()
        traktListEntry.id = 42
        traktListEntry.season = TraktListEntry.Season()
        traktListEntry.season.episode_count = 14
        traktListEntry.season.number = 3
        traktListEntry.season.first_aired = Date.from(Instant.parse("2021-04-03T00:00:00Z"))
        traktListEntry.rank = 9
        traktListEntry.show = TraktListEntry.Show()
        traktListEntry.show.title = "The Testers"
        traktListEntry.show.year = 2019
        val traktEntries = arrayListOf(traktListEntry)
        every { traktListService.update( any(), any(), any(), any(), any() ) } returns traktEntries
        given().`when`().get("/update")
            .then()
            .statusCode(200)
            .body(`is`("[{\"id\":42,\"rank\":9,\"season\":{\"episode_count\":14,\"first_aired\":\"2021-04-03T00:00:00Z[UTC]\",\"number\":3},\"show\":{\"title\":\"The Testers\",\"year\":2019}}]"))

    }
}