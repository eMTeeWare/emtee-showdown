package net.emteeware

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.GET
import javax.ws.rs.HeaderParam
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces

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
