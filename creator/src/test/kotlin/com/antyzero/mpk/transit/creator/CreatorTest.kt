package com.antyzero.mpk.transit.creator

import com.antyzero.mpk.transit.database.MpkDatabase
import com.antyzero.mpk.transit.database.MpkDatabaseDownloader
import org.junit.jupiter.api.Test

class CreatorTest {

    @Test
    internal fun hahaha() {
        print(DatabaseCreator(MpkDatabase(MpkDatabaseDownloader().get())).stops().joinToString(separator = "\n"))
    }
}