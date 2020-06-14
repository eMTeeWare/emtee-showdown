package net.emteeware

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.path.xml.XmlPath
import org.apache.http.HttpStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@QuarkusTest
class IndexPageTest {

    @Test
    fun testIndexPage() {
        val response = given()
            .`when`().get("/")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .and().contentType(ContentType.HTML).extract().response()
        val htmlPath = XmlPath(XmlPath.CompatibilityMode.HTML, response.body.asString())
        assertEquals("eMTee Showdown", htmlPath.getString("html.head.title"))
        assertEquals("You are just a few steps away from watching a new tv show.", htmlPath.getString("html.body.div.div.p.find {it.@class == 'lead'}"))
        assertEquals(listOf("seasons", "https://www.trakt.tv", "update", "selection").sorted(), htmlPath.getList<String>("html.body.div.div.p.a.@href").toMutableList().sorted())
    }

}