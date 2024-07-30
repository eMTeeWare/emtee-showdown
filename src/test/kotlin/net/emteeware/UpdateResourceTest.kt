package net.emteeware

import io.mockk.every
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Test
import io.restassured.RestAssured.given
import io.quarkiverse.test.junit.mockk.InjectMock
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.skyscreamer.jsonassert.JSONAssert

import java.time.Instant
import java.util.*
import jakarta.inject.Inject

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class UpdateResourceTest {

    @InjectMock
    @RestClient
    lateinit var traktListService: TraktListService

    @InjectMock
    @RestClient
    lateinit var traktShowService: TraktShowService

    @Inject
    lateinit var seasonChoice: SeasonChoice

    val traktListEntry = TraktListEntry()
    val traktEntries by lazy { arrayListOf(traktListEntry) }

    val traktShow = TraktShow()
    val traktShows by lazy { arrayListOf(traktShow) }

    @BeforeAll
    fun setup() {
        traktListEntry.id = 42
        traktListEntry.season = TraktListEntry.Season()
        traktListEntry.season.episode_count = 14
        traktListEntry.season.number = 3
        traktListEntry.season.first_aired = Date.from(Instant.parse("2021-04-03T00:00:00Z"))
        traktListEntry.rank = 9
        traktListEntry.show = TraktListEntry.Show()
        traktListEntry.show.title = "The Testers"
        traktListEntry.show.year = 2019
        traktListEntry.show.ids = TraktListEntry.Ids()
        traktListEntry.show.ids.trakt = 4711

        traktShow.title = "Die Tester"
        traktShow.overview = "Fry und Bender testen neue Möglichkeiten Geld zu verdienen"
        traktShow.language = "de"
        traktShow.country = "de"
    }

    @Test
    fun update() {
        val expectedJson =
            """{
    "legacySeasonList": [
        {
            "id": "42",
            "lastSeen": "-999999999-01-01",
            "localizedTitle": "Die Tester",
            "number": 3,
            "numberOfEpisodes": 14,
            "selected": false,
            "show": "The Testers",
            "summary": "Fry und Bender testen neue Möglichkeiten Geld zu verdienen",
            "totalSeasons": 0,
            "yearOfFirstAiring": {
                "leap": false,
                "value": 2021
            }
        }
    ]
}"""
        every { traktListService.update( any(), any(), any(), any(), any() ) } returns traktEntries
        every { traktShowService.getTranslation( any(), any(), any(), any() ) } returns traktShows
        val response = given().log().all().get("/update")
        response.then().assertThat().statusCode(200)
        println(response.body.asString())
        JSONAssert.assertEquals(expectedJson, response.body.asString(), false)

    }

    @AfterAll
    fun resetSeasons() {
        seasonChoice.reset()
    }
}