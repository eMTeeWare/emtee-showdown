package net.emteeware

import java.time.LocalDateTime
import java.util.Date
import javax.enterprise.context.ApplicationScoped

/**
 * Created by mteet on 25.04.2020.
 * Copyright 2020 eMTeeWare
 */


@ApplicationScoped
class TraktListEntry {

    var rank: Int = 0
    var id: Int = 0
    lateinit var season: Season
    lateinit var show: Show

    class Show {
        lateinit var title: String
        var year: Int = 0
    }

    // Property names are defined by output of Trakt rest API
    @Suppress("PropertyName")
    class Season {
        var number: Int = 0
        var episode_count: Int = 0
        lateinit var first_aired: Date

    }

}
