package com.antyzero.mpk.transit.creator

import com.antyzero.mpk.transit.database.MpkDatabase
import com.antyzero.mpk.transit.database.MpkDatabaseDownloader
import org.junit.jupiter.api.Test

class CreatorTest {

    val data: DatabaseCreator = DatabaseCreator(MpkDatabase(MpkDatabaseDownloader().get()))

    @Test
    internal fun agency() {
        print(data.agency())
    }

    @Test
    internal fun stops() {
        print(data.stops())
    }
}