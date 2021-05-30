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

    @Test
    fun `verify that no seasons are selected by default`() {
        val response = given()
            .`when`().get("/selection")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract().response()
        val htmlPath = XmlPath(XmlPath.CompatibilityMode.HTML, response.body.asString())
        assertEquals("", htmlPath.getString("html.body"))
    }

    @Test
    fun `verify that season appears in selection when it is selected`() {
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("id", "100")
            .formParam("user", "user1")
            .`when`().post("/selection")
            .then()
            .statusCode(HttpStatus.SC_ACCEPTED)

        val response= given()
            .`when`().get("/selection")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract().response()
        val htmlPath = XmlPath(XmlPath.CompatibilityMode.HTML, response.body.asString())
        assertEquals("ALF", htmlPath.getString("html.body.div.div.div[1].div.b"))
    }

}

