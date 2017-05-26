package com.antyzero.mpk.transit.creator

import com.antyzero.mpk.transit.database.MpkDatabase
import com.antyzero.mpk.transit.database.MpkDatabaseDownloader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CreatorTest {

    lateinit var data: DatabaseCreator

    @BeforeEach
    internal fun setUp() {
        data = DatabaseCreator(MpkDatabase(MpkDatabaseDownloader().get()))
    }

    @Test
    internal fun agency() {
        assertThat(data.agency().list).isNotEmpty
    }

    @Test
    internal fun stops() {
        assertThat(data.stops().list).isNotEmpty
        data.stops()
    }

    @Test
    internal fun routes() {
        assertThat(data.routes().list).isNotEmpty
    }
}