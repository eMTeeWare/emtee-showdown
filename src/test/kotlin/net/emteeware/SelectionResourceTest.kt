package net.emteeware

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.path.xml.XmlPath
import io.restassured.response.Response
import org.apache.http.HttpStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@QuarkusTest
class SelectionResourceTest {

    @Inject
    lateinit var selectedSeasons: Selection

    @BeforeEach
    fun cleanSeasons() {
        selectedSeasons.clear()
    }

    @Test
    fun `verify page title`() {
        val html = getHtml()
        assertEquals("eMTee Showdown - Selection", html.getString("html.head.title"))
    }

    @Test
    fun `verify that no seasons are selected by default`() {
        val html = getHtml()
        assertEquals("", html.getString("html.body"))
    }

    @Test
    fun `verify that season appears in selection when it is selected`() {
        postSeason("100", "user1")
        val html = getHtml()
        assertEquals("ALF", html.getString("html.body.div.div.div[1].div.b"))
    }

    @Test
    fun `verify that unselected season is removed from selection`() {
        postSeason("100", "user1")
        deleteSeason("100", "user1")
        val html = getHtml()
        assertEquals("", html.getString("html.body"))
    }

    @Test
    fun `verify that a season appears twice on the selection page if two users select it`() {
        postSeason("100", "user1")
        postSeason("100", "user2")
        val html = getHtml()
        assertEquals("ALF", html.getString("html.body.div[0].div.div[1].div.b"))
        assertEquals("ALF", html.getString("html.body.div[1].div.div[1].div.b"))
    }

    @Test
    fun `verify that clear removes all selected seasons`() {
        postSeason("100", "user1")
        postSeason("200", "user2")
        postSeason("200", "user1")
        var html = getHtml()
        assertEquals(3, html.getNodeChildren("html.body.div").size())
        deleteSeason("*", "user3")
        html = getHtml()
        assertEquals("", html.getString("html.body"))
    }

    private fun getHtml(): XmlPath {
        val response = getSeasons()
        val htmlPath = XmlPath(XmlPath.CompatibilityMode.HTML, response.body.asString())
        return htmlPath
    }


    private fun deleteSeason(seasonId: String, userId: String) {
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("id", seasonId)
            .formParam("user", userId)
            .`when`().delete("/selection")
            .then()
            .statusCode(HttpStatus.SC_ACCEPTED)
    }

    private fun getSeasons(): Response {
        return given()
            .`when`().get("/selection")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract().response()
    }

    private fun postSeason(seasonId: String, userId: String) {
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("id", seasonId)
            .formParam("user", userId)
            .`when`().post("/selection")
            .then()
            .statusCode(HttpStatus.SC_ACCEPTED)
    }

}

