package net.emteeware

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.GET
import jakarta.ws.rs.HeaderParam
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam


@Path("/users/{username}/lists")
@RegisterRestClient
@ApplicationScoped
interface TraktListService {

    @GET
    @Path("/{listname}/items")
    @Produces("application/json")
    @ClientHeaderParam(name = "trakt-api-version", value = ["2"])
    fun update(
        @QueryParam("extended") extended: String,
        @HeaderParam("trakt-api-key") apiKey: String,
        @HeaderParam("Authorization") bearerToken: String,
        @PathParam("username") userName: String,
        @PathParam("listname") listName: String
    ): ArrayList<TraktListEntry>
}
