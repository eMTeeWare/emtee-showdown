package net.emteeware

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.GET
import javax.ws.rs.HeaderParam
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam


@Path("/users/methom/lists")
@RegisterRestClient
interface TraktListService {

    @GET
    @Path("/sm-available/items")
    @Produces("application/json")
    @ClientHeaderParam(name = "trakt-api-version", value = ["2"])
    fun update(
        @QueryParam("extended") extended: String,
        @HeaderParam("trakt-api-key") apiKey: String,
        @HeaderParam("Authorization") bearerToken: String
    ): ArrayList<TraktListEntry>
}
