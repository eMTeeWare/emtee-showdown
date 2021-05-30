package net.emteeware

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.apache.http.HttpStatus
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

}