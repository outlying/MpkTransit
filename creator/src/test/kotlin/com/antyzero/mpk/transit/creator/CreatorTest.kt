package com.antyzero.mpk.transit.creator

import com.antyzero.mpk.transit.database.MpkDatabase
import com.antyzero.mpk.transit.database.MpkDatabaseDownloader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CreatorTest {

    lateinit var creator: DatabaseCreator

    @BeforeEach
    internal fun setUp() {
        creator = DatabaseCreator(MpkDatabase(MpkDatabaseDownloader().get()))
    }

    @Test
    internal fun agency() {
        assertThat(creator.agency().list).isNotEmpty
    }

    @Test
    internal fun stops() {
        assertThat(creator.stops().list).isNotEmpty
    }

    @Test
    internal fun routes() {
        assertThat(creator.routes().list).isNotEmpty
    }
}