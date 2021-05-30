package net.emteeware

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.path.xml.XmlPath
import org.apache.http.HttpStatus
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@QuarkusTest
class SelectionResourceTest {

    @Test
    fun `test that hello endpoint does not exist`() {
        given()
            .`when`().get("/hello")
            .then()
            .statusCode(HttpStatus.SC_NOT_FOUND)
    }

    @Test
    fun `verify page title`() {
        val response = given()
            .`when`().get("/selection")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract().response()
        val htmlPath = XmlPath(XmlPath.CompatibilityMode.HTML, response.body.asString())
        assertEquals("eMTee Showdown - Selection", htmlPath.getString("html.head.title"))
    }



}

