package com.antyzero.mpk.transit.scrapper

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class ScrapperTest {

    private lateinit var scrapper: Scrapper

    @BeforeEach
    internal fun setUp() {
        scrapper = Scrapper(MockTimetablesSites())
    }

    @Test
    internal fun timetableTimes() {
        scrapper.timetablesDates().test()
                .assertComplete()
    }
}