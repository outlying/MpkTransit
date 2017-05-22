package com.antyzero.mpk.transit.creator

import com.antyzero.mpk.transit.creator.model.Stop
import com.antyzero.mpk.transit.database.MpkDatabase
import com.antyzero.mpk.transit.database.MpkDatabaseDownloader
import org.junit.jupiter.api.Test

class CreatorTest {

    @Test
    internal fun hahaha() {
        val stops = DatabaseCreator(MpkDatabase(MpkDatabaseDownloader().get())).stops()

        stops.add(Stop(
                id = "666",
                name = "Satan",
                latitude = 66f,
                longitude = 66f
        ))

        print(stops)

    }
}