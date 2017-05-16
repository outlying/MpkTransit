package com.antyzero.mpk.transit.database

import org.junit.jupiter.api.Test


class MpkDatabaseTest {

    val mpkDatabase = MpkDatabase(MpkDatabaseDownloader().get())

    @Test
    internal fun lines() {
        print(mpkDatabase.lines().joinToString(separator = "\n"))
    }

    @Test
    internal fun points() {
        print(mpkDatabase.points().joinToString(separator = "\n"))
    }

    @Test
    internal fun shedules() {
        print(mpkDatabase.shedules().joinToString(separator = "\n"))
    }
}