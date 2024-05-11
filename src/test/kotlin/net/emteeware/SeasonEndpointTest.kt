package net.emteeware

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.path.xml.XmlPath
import org.apache.http.HttpStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

@QuarkusTest
class SeasonEndpointTest {

    @Test
    fun `verify page title`() {
        val response = given()
            .`when`().get("/seasons")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .and().contentType(ContentType.HTML).extract().response()
        val htmlPath = XmlPath(XmlPath.CompatibilityMode.HTML, response.body.asString())
        assertEquals("eMTee Showdown - Seasons", htmlPath.getString("html.head.title"))
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/season-properties.csv"])
    fun `verify properties of seasons`(id: String, title: String, localizedTitle: String, count: String, totalSeasons: String, episodes: String, year: String, lastseen: String, summary: String) {
        val response = given()
            .`when`().get("/seasons")
            .then()
            .statusCode(HttpStatus.SC_OK).extract().response()
        val htmlPath = XmlPath(XmlPath.CompatibilityMode.HTML, response.body.asString())
        if (title == localizedTitle) {
            assertEquals(title, htmlPath.getString("html.body.div.div.p.find{ it.@id == 'title-$id' }.b"))
        } else {
            assertEquals("$localizedTitle ($title)", htmlPath.getString("html.body.div.div.p.find{ it.@id == 'title-$id' }.text()"))
        }
        assertEquals("$count, $episodes, $year", htmlPath.getString("html.body.div.div.p.find{ it.@id == 'info-$id' }"))
        assertEquals(lastseen, htmlPath.getString("html.body.div.div.p.find{ it.@id == 'lastseen-$id' }"))
        assertEquals(summary, htmlPath.getString("html.body.div.div.p.find{ it.@id == 'summary-$id' }"))
    }

}