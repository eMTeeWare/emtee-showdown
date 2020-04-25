package net.emteeware

import org.eclipse.microprofile.rest.client.inject.RestClient
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * Created by mteet on 25.04.2020.
 * Copyright 2020 eMTeeWare
 */

@Path("/country")
class CountryResource {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Inject
    @field: RestClient
    internal lateinit var countryService: CountryService

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    fun name(@PathParam("name") name: String) : Set<Country> {
        logger.info("Requested data for country $name")
        return countryService.getByName(name)
    }
}