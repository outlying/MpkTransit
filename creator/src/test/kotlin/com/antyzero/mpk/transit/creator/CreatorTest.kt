package com.antyzero.mpk.transit.creator

import com.antyzero.mpk.transit.database.MpkDatabase
import com.antyzero.mpk.transit.database.MpkDatabaseDownloader
import org.junit.jupiter.api.Test

class CreatorTest {

    @Test
    internal fun hahaha() {
        val stops = DatabaseCreator(MpkDatabase(MpkDatabaseDownloader().get())).stops()

        print(stops.keys.joinToString { "," })
        print(stops.joinToString(separator = "\n"))

    }
}