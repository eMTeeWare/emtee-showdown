package net.emteeware

import javax.enterprise.context.ApplicationScoped

/**
 * Created by mteet on 25.04.2020.
 * Copyright 2020 eMTeeWare
 */
@ApplicationScoped
class Country {

    lateinit var name: String
    lateinit var alpha2Code: String
    lateinit var capital: String
    lateinit var currencies: List<Currency>

    class Currency {
        lateinit var code: String
        lateinit var name: String
        lateinit var symbol: String
    }
}