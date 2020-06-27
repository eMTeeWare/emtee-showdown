package net.emteeware

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.path.xml.XmlPath
import org.apache.http.HttpStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@QuarkusTest
class SeasonEndpointTest {

    @Test
    fun verifyPageTitle() {
        val response = given()
            .`when`().get("/seasons")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .and().contentType(ContentType.HTML).extract().response()
        val htmlPath = XmlPath(XmlPath.CompatibilityMode.HTML, response.body.asString())
        assertEquals("eMTee Selection", htmlPath.getString("html.head.title"))
    }

    @Test
    fun `verify properties of season with id 400`() {
        val response = given()
            .`when`().get("/seasons")
            .then()
            .statusCode(HttpStatus.SC_OK).extract().response()
        val htmlPath = XmlPath(XmlPath.CompatibilityMode.HTML, response.body.asString())
        assertEquals("Doctor Who", htmlPath.getString("html.body.div.p.find{ it.@id == 'title-400' }"))
        assertEquals("Staffel 6", htmlPath.getString("html.body.div.p.find{ it.@id == 'count-400' }"))
        assertEquals("Erstausstrahlung: 2011", htmlPath.getString("html.body.div.p.find{ it.@id == 'year-400' }"))
        assertEquals("13 Episoden", htmlPath.getString("html.body.div.p.find{ it.@id == 'episodes-400' }"))
        assertEquals("", htmlPath.getString("html.body.div.p.find{ it.@id == 'lastseen-400' }"))
    }

}