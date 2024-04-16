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
        legacySeasonList.add(LegacySeason("ALF", "ALF", "Pelziger Alien terrorisiert Familie Tanner", 2, 4, Year.of(1987), 26, LocalDate.parse("2020-03-08"), "100"))
        legacySeasonList.add(
            LegacySeason("Battlestar Galactica", "Kampfstern Galactica", "Die letzten Überlebenden der 12 Kolonien fliehen vor den Cylonen", 4, 4, Year.of(2008), 20, LocalDate.parse("2020-03-08"), "200"))
        legacySeasonList.add(LegacySeason("Captain Future", "Captain Future", "Captain Future und die Crew der Comet helfen den Menschen des Sonnensystems", 1, 14, Year.of(1978), 52, LocalDate.parse("2020-02-18"), "300"))
        legacySeasonList.add(LegacySeason("Doctor Who", "Doctor Who", "Ein Zeitlord auf der Reise durch Raum und Zeit in der Tardis",6, 12, Year.of(2011), 13, LocalDate.parse("2020-02-18"), "400"))
        legacySeasonList.add(LegacySeason("Earth 2", "Earth 2", "Eine Expedition landet auf einem unbekannten Planeten", 1, 1, Year.of(1994), 22, LocalDate.parse("2020-02-18"), "500"))
        legacySeasonList.add(LegacySeason("Firefly", "Firefly - Der Aufbruch der Serenity", "Mel und seine Crew transportieren mit ihrem Frachter Serenity was immer ihnen Geld einbringt", 1, 1, Year.of(2002), 14, LocalDate.parse("2020-02-18"), "600"))
        legacySeasonList.add(LegacySeason("Gotham", "Gotham", "Bevor Bruce Wayne Batman wurde, wurde Gotham von Jim Gordon beschützt",4, 5, Year.of(2017), 22, LocalDate.parse("2020-02-18"), "700"))
    }


}