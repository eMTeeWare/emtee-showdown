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
import org.junit.jupiter.api.Assertions.assertEquals

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
            """<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>eMTee Showdown - Seasons</title>
    <link rel="stylesheet" href="seasons.css"/>
    <link rel="stylesheet" href="styles.css"/>
    <link rel="stylesheet" href="/settings/dark-mode.css" />
    <script type="application/javascript" src="seasons.js" defer></script>
    <script src="settings/load-settings.js" defer></script>
    <link
        rel="icon"
        type="image/png"
        sizes="64x64"
        href="emtee-showdown-icon.png"
    />
</head>
<body>
<div class="top-of-page">
<nav class="nav">
  <ul class="nav-ul">
    <li><img src="emtee-showdown-icon.png" alt="emtee-showdown logo" /></li>
    <li ><a href="/index.html">Home</a></li>
    <li class="nav-selected"><a href="/update">Update</a></li>
    <li ><a href="/seasons">Seasons</a></li>
    <li ><a href="/selection">Selection</a></li>
    <li ><a href="/settings/index.html">Settings</a></li>
    <li ><a href="/legal">Legal</a></li>
  </ul>
</nav>
<style>
  .nav {
    width: 100%;
    height: 3rem;
  }
  .nav img {
    padding: 7px 8px;
    max-height: 34px; /* 3rem (48px|3*16px) - 14px|2*7px */
  }
  .nav a {
    color: white;
  }
  .nav-selected a {
    background-color: #48cbdd;
  }
  .nav a:visited {
    color: white;
  }
  .nav ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
    height: 100%;
  }
  .nav li {
    float: left;
  }
  .nav a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
  }
  .nav a:hover {
    background-color: #111;
  }
  .nav-selected a:hover {
    background-color: #00A1E2;
  }
</style>
</div>
<div class="updated" id="updated" style="padding: 15px">
    1 seasons updated from Trakt.
</div>
</body>
</html>"""
        every { traktListService.update( any(), any(), any(), any(), any() ) } returns traktEntries
        every { traktShowService.getTranslation( any(), any(), any(), any() ) } returns traktShows
        val response = given().log().all().get("/update")
        response.then().assertThat().statusCode(200)
        println(response.body.asString())
        assertEquals(expectedJson, response.body.asString())

    }

    @AfterAll
    fun resetSeasons() {
        seasonChoice.reset()
    }
}