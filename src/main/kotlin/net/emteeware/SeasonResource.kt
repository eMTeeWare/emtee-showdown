package net.emteeware

import io.quarkus.qute.Template
import io.quarkus.qute.TemplateInstance
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

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