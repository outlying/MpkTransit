package com.antyzero.mpk.transit.creator

import com.antyzero.mpk.transit.database.MpkDatabase
import com.antyzero.mpk.transit.database.MpkDatabaseDownloader
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CreatorTest {

    lateinit var data: DatabaseCreator

    @BeforeEach
    internal fun setUp() {
        // TODO provide local database for testing
        data = DatabaseCreator(MpkDatabase(MpkDatabaseDownloader().get()))
    }

    @Test
    internal fun agency() {
        print(data.agency())
    }

    @Test
    internal fun stops() {
        print(data.stops())
    }

    @Test
    internal fun routes() {
        print(data.routes())
    }
}