package net.emteeware

import io.quarkus.qute.Template
import io.quarkus.qute.TemplateInstance
import java.util.Collections
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType.*
import javax.ws.rs.core.Response

@Path("/hello")
class ExampleResource {

    @Inject
    lateinit var hello: Template

    @GET
    @Produces(TEXT_HTML)
    fun hello(@QueryParam("name") name: String?): TemplateInstance = hello.data("name", name ?: "Quarkus")
}

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
    fun select(id: String): Response {
        selectedShows.addSeason(id.substring(3))
        return Response.accepted().build()
    }

    @GET
    @Produces(TEXT_HTML)
    fun getSelectedSeasons() : TemplateInstance {
        return selection.data("seasons", seasonChoice.seasonList.filter { selectedShows.selectedSeasons.contains(it.id) }.shuffled())
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
    fun getSingleSeason(@PathParam("id") id: String) : TemplateInstance {
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