package net.emteeware

import java.time.LocalDate
import java.time.Year
import javax.inject.Singleton

@Singleton
class SeasonChoice {
    val legacySeasonList: ArrayList<LegacySeason> = ArrayList()

    init {
        reset()
    }

    fun getSeasonById(id: String): LegacySeason? {
        return legacySeasonList.find { it.id == id }
    }

    fun reset() {
        legacySeasonList.clear()
        legacySeasonList.add(LegacySeason("ALF", 2, 4, Year.of(1987), 26, LocalDate.parse("2020-03-08"), "100"))
        legacySeasonList.add(
            LegacySeason("Battlestar Galactica", 4, 4, Year.of(2008), 20, LocalDate.parse("2020-03-08"), "200"))
        legacySeasonList.add(LegacySeason("Captain Future", 1, 14, Year.of(1978), 52, LocalDate.parse("2020-02-18"), "300"))
        legacySeasonList.add(LegacySeason("Doctor Who", 6, 12, Year.of(2011), 13, LocalDate.parse("2020-02-18"), "400"))
        legacySeasonList.add(LegacySeason("Earth 2", 1, 1, Year.of(1994), 22, LocalDate.parse("2020-02-18"), "500"))
        legacySeasonList.add(LegacySeason("Firefly", 1, 1, Year.of(2002), 14, LocalDate.parse("2020-02-18"), "600"))
        legacySeasonList.add(LegacySeason("Gotham", 4, 5, Year.of(2017), 22, LocalDate.parse("2020-02-18"), "700"))
    }


}