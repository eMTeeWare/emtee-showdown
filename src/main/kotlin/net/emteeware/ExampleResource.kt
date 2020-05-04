package net.emteeware

import io.quarkus.qute.Template
import io.quarkus.qute.TemplateInstance
import org.slf4j.LoggerFactory
import java.util.Collections
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.FormParam
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.*
import javax.ws.rs.core.Response


@Path("/selection")
class SelectionResource {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Inject
    lateinit var selectedShows: Selection

    @Inject
    lateinit var seasonChoice: SeasonChoice

    @Inject
    lateinit var selection: Template

    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    fun select(
        @FormParam("id") id: String,
        @FormParam("user") user: String
    ): Response {
        logger.info("Adding season $id for $user")
        selectedShows.addSeason(id, user)
        return Response.accepted().build()
    }

    @DELETE
    @Consumes(APPLICATION_FORM_URLENCODED)
    fun deselect(@FormParam("id") id: String, @FormParam("user") user: String): Response {
        logger.info("Deleting season $id for $user")
        if (id == "*") {
            selectedShows.clear()
        } else {
            selectedShows.removeSeason(id, user)
        }
        return Response.accepted().build()
    }

    @GET
    @Produces(TEXT_HTML)
    fun getSelectedSeasons(): TemplateInstance {
        val theSelection = arrayListOf<Season>()
        selectedShows.selectedSeasons.flatMap { p -> p.value }.forEach {
            seasonChoice.getSeasonById(it)?.let { season -> theSelection.add(season) }
        }
        return selection.data(
            "seasons",
            theSelection.shuffled()
        )
    }
}

@Path("/seasons")
class SeasonResource {

    @Inject
    lateinit var seasons: Template

    @Inject
    lateinit var seasonChoice: SeasonChoice


    @GET
    @Produces(TEXT_HTML)
    fun seasons(): TemplateInstance {

        return seasons.data("seasons", seasonChoice.seasonList)
    }

    @GET
    @Path("/{id}")
    @Produces(TEXT_HTML)
    fun getSingleSeason(@PathParam("id") id: String): TemplateInstance {
        return seasons.data("seasons", Collections.singletonList(seasonChoice.getSeasonById(id)))
    }

    @PUT
    @Path("/{id}")
    fun setSeasonSelected(@PathParam("id") id: String): Response {
        return if (seasonChoice.toggleSeasonSelectedById(id)) {
            Response.accepted().build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }

}