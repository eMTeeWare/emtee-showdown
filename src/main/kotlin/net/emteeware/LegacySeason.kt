package net.emteeware

import io.quarkus.qute.TemplateData
import java.time.LocalDate
import java.time.Year

@TemplateData
data class LegacySeason(
    val show: String,
    val number: Int,
    val totalSeasons: Int,
    val yearOfFirstAiring: Year,
    val numberOfEpisodes: Int,
    val lastSeen: LocalDate,
    val id: String,
    var selected: Boolean = false
)
