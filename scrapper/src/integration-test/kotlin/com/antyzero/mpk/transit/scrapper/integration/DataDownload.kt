package com.antyzero.mpk.transit.scrapper.integration

import com.antyzero.mpk.transit.scrapper.Scrapper
import com.antyzero.mpk.transit.scrapper.site.MpkTimetablesSites
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DataDownload {

    lateinit var scrapper: Scrapper

    @BeforeEach
    internal fun setUp() {
        scrapper = Scrapper(MpkTimetablesSites())
    }

    @Test
    @DisplayName("Checking timetable update dates")
    internal fun downloadDates() {
        scrapper.timetableDates().test()
                .assertComplete()
                .assertOf {
                    assertThat(it.valueCount())
                            .overridingErrorMessage("Expected at least one value of timetable time (current)")
                            .isGreaterThan(0)
                }
    }

    @Test
    internal fun downloadLastUpdate() {
        scrapper.lastUpdate().test()
                .assertComplete()
                .assertValueCount(1)
    }

    @Nested
    inner class TimetablesData {

        @Test
        internal fun downloadAllLinesSimple() {
            scrapper.timetable().lines().test()
                    .assertComplete()
                    .assertOf {
                        assertThat(it.valueCount()).isGreaterThan(100)
                    }
        }

        @Test
        internal fun downloadAllStops() {
            scrapper.timetable().stops().test()
                    .assertComplete()
                    .assertOf {
                        assertThat(it.valueCount()).isGreaterThan(1000)
                    }
        }
    }
}
