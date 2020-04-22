package net.emteeware


import org.slf4j.LoggerFactory
import java.util.Arrays
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class Selection {
    private val logger = LoggerFactory.getLogger(javaClass)
    val selectedSeasons = HashMap<String, ArrayList<String>>()

    fun addSeason(id : String, user: String) {
        when {
            selectedSeasons.containsKey(user) -> {
                selectedSeasons[user]?.add(id)
            }
            else -> {
                selectedSeasons[user] = arrayListOf(id)
            }
        }
        logger.info("Added season $id for $user")
    }

    fun removeSeason(id: String, user: String) {
        when {
            selectedSeasons.containsKey(user) -> {
                selectedSeasons[user]?.remove(id)
            }
        }
        logger.info("Removed season $id for $user")
    }

    fun clear() {
        selectedSeasons.clear()
    }
}
