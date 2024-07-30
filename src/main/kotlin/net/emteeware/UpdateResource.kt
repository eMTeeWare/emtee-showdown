package net.emteeware

import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.slf4j.LoggerFactory
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Year
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

/**
 * Created by mteet on 25.04.2020.
 * Copyright 2020 eMTeeWare
 */

@Path("/update")
class UpdateResource {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Inject
    lateinit var seasonChoice: SeasonChoice

    @Inject
    @field: RestClient
    internal lateinit var traktListService: TraktListService

    @Inject
    @field: RestClient
    internal lateinit var traktShowService: TraktShowService

    @ConfigProperty(name = "trakt.api-key")
    lateinit var apiKey : String
    @ConfigProperty(name = "trakt.bearer-token")
    lateinit var authToken : String
    @ConfigProperty(name = "trakt.user-name")
    lateinit var userName : String
    @ConfigProperty(name = "trakt.list-name")
    lateinit var listName : String

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun update(): SeasonChoice {
        logger.info("update from Trakt requested")
        val updatedSeasons = traktListService.update("full", apiKey, authToken, userName, listName)
        seasonChoice.legacySeasonList.clear()
        updatedSeasons.forEach { u ->
            logger.info("translations for ${u.show.title} from Trakt requested")
            val show = traktShowService.getTranslation(apiKey, authToken, u.show.ids.trakt, "de")[0]
            logger.info("received translations")
            seasonChoice.legacySeasonList.add(
                LegacySeason(
                    u.show.title,
                    show.title?:u.show.title,
                    show.overview?:"No summary found",
                    u.season.number,
                    0,
                    Year.of(SimpleDateFormat("yyyy").format(u.season.first_aired).toInt()),
                    u.season.episode_count,
                    LocalDate.MIN,
                    u.id.toString()
                )
            )
        }
        return seasonChoice
    }
}