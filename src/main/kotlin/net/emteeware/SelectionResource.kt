package net.emteeware

import io.quarkus.qute.Template
import io.quarkus.qute.TemplateInstance
import io.quarkus.logging.Log
import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.FormParam
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED
import jakarta.ws.rs.core.MediaType.TEXT_HTML
import jakarta.ws.rs.core.Response


@Path("/selection")
class SelectionResource {

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
        Log.info("Adding season $id for $user")
        selectedShows.addSeason(id, user)
        return Response.accepted().build()
    }

    @DELETE
    @Consumes(APPLICATION_FORM_URLENCODED)
    fun deselect(@FormParam("id") id: String, @FormParam("user") user: String): Response {
        Log.info("Deleting season $id for $user")
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
        val theSelection = arrayListOf<LegacySeason>()
        selectedShows.selectedSeasons.forEach { (user, seasonList) ->
            seasonList.forEach innerFor@{
                val currentSeason = seasonChoice.getSeasonById(it)?.copy() ?: return@innerFor
                currentSeason.nominatingUser = user
                theSelection.add(currentSeason)
            }
        }
        return selection.data(
            "seasons",
            theSelection.shuffled()
        )
    }
}