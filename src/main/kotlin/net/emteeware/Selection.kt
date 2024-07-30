package net.emteeware


import io.quarkus.logging.Log
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class Selection {
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
        Log.info("Added season $id for $user")
    }

    fun removeSeason(id: String, user: String) {
        when {
            selectedSeasons.containsKey(user) -> {
                selectedSeasons[user]?.remove(id)
            }
        }
        Log.info("Removed season $id for $user")
    }

    fun clear() {
        selectedSeasons.clear()
    }
}
