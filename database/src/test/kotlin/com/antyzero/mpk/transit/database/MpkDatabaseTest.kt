package com.antyzero.mpk.transit.database

import org.junit.jupiter.api.Test


class MpkDatabaseTest {

    val mpkDatabase = MpkDatabase(MpkDatabaseDownloader().get())

    @Test
    internal fun lines() {
        mpkDatabase.lines().presentResults()
    }

    @Test
    internal fun points() {
        mpkDatabase.points().presentResults()
    }

    @Test
    internal fun shedules() {
        mpkDatabase.shedules().presentResults()
    }

    @Test
    internal fun routes() {
        mpkDatabase.routes().presentResults()
    }

    @Test
    internal fun stopDepartures() {
        mpkDatabase.stopDepartures().presentResults()
    }

    @Test
    internal fun stops() {
        mpkDatabase.stops().presentResults()
    }

    @Test
    internal fun streets() {
        mpkDatabase.streets().presentResults()
    }

    private fun Collection<Any>.presentResults() {
        //print(this.joinToString(separator = "\n"))
    }
}