package com.antyzero.mpk.transit.database

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class MpkDatabaseTest {

    val mpkDatabase = MpkDatabase(MpkDatabaseDownloader().get())

    @Test
    internal fun lines() {
        mpkDatabase.lines()
                .generalTest()
                .presentResults()
    }

    @Test
    internal fun points() {
        mpkDatabase.points()
                .generalTest()
                .presentResults()
    }

    @Test
    internal fun shedules() {
        mpkDatabase.shedules()
                .generalTest()
                .presentResults()
    }

    @Test
    internal fun routes() {
        mpkDatabase.routes()
                .generalTest()
                .presentResults()
    }

    @Test
    internal fun stopDepartures() {
        mpkDatabase.stopDepartures()
                .generalTest()
                .presentResults()
    }

    @Test
    internal fun stops() {
        mpkDatabase.stops()
                .generalTest()
                .presentResults()
    }

    @Test
    internal fun streets() {
        mpkDatabase.streets()
                .generalTest()
                .presentResults()
    }

    @Test
    internal fun variants() {
        mpkDatabase.variants()
                .generalTest()
                .presentResults()
    }

    private fun Collection<Any>.generalTest() = this.apply {
        assertThat(this)
                .isNotNull()
                .isNotEmpty
    }

    private fun Collection<Any>.presentResults() = this.apply {
        //print(this.joinToString(separator = "\n"))
    }
}