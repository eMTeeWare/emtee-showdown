package net.emteeware

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class Selection {
    val selectedSeasons = ArrayList<String>()

    fun addSeason(id : String) {
        selectedSeasons.add(id)
    }
}
