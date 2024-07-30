package net.emteeware

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.GET
import jakarta.ws.rs.HeaderParam
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces

@Path("/shows")
@RegisterRestClient
@ApplicationScoped
interface TraktShowService {


    @GET
    @Path("/{showid}/translations/{lang}")
    @Produces("application/json")
    @ClientHeaderParam(name = "trakt-api-version", value = ["2"])
    fun getTranslation(
        @HeaderParam("trakt-api-key") apiKey: String,
        @HeaderParam("Authorization") bearerToken: String,
        @PathParam("showid") showId: Int,
        @PathParam("lang") isoLanguageId: String,
    ): ArrayList<TraktShow>
}
