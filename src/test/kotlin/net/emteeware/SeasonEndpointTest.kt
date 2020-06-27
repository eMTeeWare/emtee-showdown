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
}