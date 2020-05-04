package net.emteeware

import java.time.LocalDate
import java.time.Year
import javax.inject.Singleton

@Singleton
class SeasonChoice {
    final val legacySeasonList: ArrayList<LegacySeason> = ArrayList()

    init {
        legacySeasonList.add(LegacySeason("How to Get Away with Murder", 3, 6, Year.of(2016), 22, LocalDate.parse("2020-03-08"), "100"))
        legacySeasonList.add(
            LegacySeason("Marvel's Agents of S.H.I.E.L.D.", 5, 7, Year.of(2017), 15, LocalDate.parse("2020-03-08"), "200"))
        legacySeasonList.add(LegacySeason("You", 2, 3, Year.of(2019), 10, LocalDate.parse("2020-02-18"), "300"))
        legacySeasonList.add(LegacySeason("An enormously long running show with a rediculously long title", 128, 1024, Year.of(2019), 594, LocalDate.parse("2020-02-18"), "400"))
        legacySeasonList.add(LegacySeason("You", 2, 3, Year.of(2019), 10, LocalDate.parse("2020-02-18"), "500"))
        legacySeasonList.add(LegacySeason("You", 2, 3, Year.of(2019), 10, LocalDate.parse("2020-02-18"), "600"))
        legacySeasonList.add(LegacySeason("You", 2, 3, Year.of(2019), 10, LocalDate.parse("2020-02-18"), "700"))
    }

    fun getSeasonById(id: String): LegacySeason? {
        return legacySeasonList.find { it.id == id }
    }

    fun toggleSeasonSelectedById(id: String): Boolean {
        return if(legacySeasonList.stream().filter { t -> t.id == id }.findFirst().isPresent) {
            legacySeasonList.stream().filter { t -> t.id == id }.findFirst().get().selected = !(legacySeasonList.stream().filter { t -> t.id == id }.findFirst().get().selected)
            true
        } else {
            false
        }

    }


}