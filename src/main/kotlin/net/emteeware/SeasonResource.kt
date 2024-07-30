package net.emteeware

import io.quarkus.qute.Template
import io.quarkus.qute.TemplateInstance
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/seasons")
class SeasonResource {

    @Inject
    lateinit var seasons: Template

    @Inject
    lateinit var seasonChoice: SeasonChoice


    @GET
    @Produces(MediaType.TEXT_HTML)
    fun seasons(): TemplateInstance {
        return seasons.data("seasons", seasonChoice.legacySeasonList)
    }

}