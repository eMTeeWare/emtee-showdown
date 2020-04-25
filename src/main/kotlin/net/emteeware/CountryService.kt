package net.emteeware

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces

/**
 * Created by mteet on 25.04.2020.
 * Copyright 2020 eMTeeWare
 */

@Path("/v2")
@RegisterRestClient
interface CountryService {

    @GET
    @Path("/name/{name}")
    @Produces("application/json")
    fun getByName(@PathParam("name") name: String) : Set<Country>
}