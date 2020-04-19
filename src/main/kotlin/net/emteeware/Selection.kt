package net.emteeware


import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class Selection {
    private val logger = LoggerFactory.getLogger(javaClass)
    val selectedSeasons = ArrayList<String>()

    fun addSeason(id : String) {
        selectedSeasons.add(id)
        logger.info("Added season $id")
    }

    fun removeSeason(id: String) {
        selectedSeasons.remove(id)
        logger.info("Removed season $id")
    }
}
