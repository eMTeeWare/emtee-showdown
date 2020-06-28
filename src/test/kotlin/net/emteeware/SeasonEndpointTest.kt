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
        assertEquals("eMTee Selection", htmlPath.getString("html.head.title"))
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/season-properties.csv"])
    fun `verify properties of seasons`(id: String, title: String, count: String, year: String, episodes: String, lastseen: String) {
        val response = given()
            .`when`().get("/seasons")
            .then()
            .statusCode(HttpStatus.SC_OK).extract().response()
        val htmlPath = XmlPath(XmlPath.CompatibilityMode.HTML, response.body.asString())
        assertEquals(title, htmlPath.getString("html.body.div.p.find{ it.@id == 'title-$id' }"))
        assertEquals(count, htmlPath.getString("html.body.div.p.find{ it.@id == 'count-$id' }"))
        assertEquals(year, htmlPath.getString("html.body.div.p.find{ it.@id == 'year-$id' }"))
        assertEquals(episodes, htmlPath.getString("html.body.div.p.find{ it.@id == 'episodes-$id' }"))
        assertEquals(lastseen, htmlPath.getString("html.body.div.p.find{ it.@id == 'lastseen-$id' }"))
    }

}