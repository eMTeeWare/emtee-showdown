package net.emteeware

import java.time.LocalDate
import java.time.Year
import javax.inject.Singleton

@Singleton
class SeasonChoice {
    final val seasonList: ArrayList<Season> = ArrayList()

    init {
        seasonList.add(Season("How to Get Away with Murder", 3, 6, Year.of(2016), 22, LocalDate.parse("2020-03-08"), "100"))
        seasonList.add(
            Season("Marvel's Agents of S.H.I.E.L.D.", 5, 7, Year.of(2017), 15, LocalDate.parse("2020-03-08"), "200"))
        seasonList.add(Season("You", 2, 3, Year.of(2019), 10, LocalDate.parse("2020-02-18"), "300"))
        seasonList.add(Season("An enormously long running show with a rediculously long title", 128, 1024, Year.of(2019), 594, LocalDate.parse("2020-02-18"), "400"))
        seasonList.add(Season("You", 2, 3, Year.of(2019), 10, LocalDate.parse("2020-02-18"), "500"))
        seasonList.add(Season("You", 2, 3, Year.of(2019), 10, LocalDate.parse("2020-02-18"), "600"))
        seasonList.add(Season("You", 2, 3, Year.of(2019), 10, LocalDate.parse("2020-02-18"), "700"))
    }

    fun getSeasonById(id: String): Season? {
        return seasonList.find { it.id == id }
    }

    fun toggleSeasonSelectedById(id: String): Boolean {
        return if(seasonList.stream().filter { t -> t.id == id }.findFirst().isPresent) {
            seasonList.stream().filter { t -> t.id == id }.findFirst().get().selected = !(seasonList.stream().filter { t -> t.id == id }.findFirst().get().selected)
            true
        } else {
            false
        }

    }


}