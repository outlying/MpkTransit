package com.antyzero.mpk.transit.scrapper

import com.antyzero.mpk.transit.scrapper.mock.Line144MockTimetablesSites
import com.antyzero.mpk.transit.scrapper.mock.MockTimetablesSites
import com.antyzero.mpk.transit.scrapper.site.Direction
import org.junit.jupiter.api.*
import java.time.LocalDate
import java.time.LocalDateTime

@DisplayName("Collecting data from website source")
class ScrapperTest {

    lateinit var scrapper: Scrapper

    @BeforeEach
    internal fun setUp() {
        scrapper = Scrapper(MockTimetablesSites())
    }

    @Test
    @DisplayName("Find current timetable date")
    internal fun currentTimetableDate() {
        scrapper.timetableCurrent().test()
                .assertValues(LocalDate.of(2017, 4, 24))
                .assertComplete()
    }

    @Test
    @DisplayName("Find all timetable update days")
    internal fun timetableTimes() {
        scrapper.timetableDates().test()
                .assertValueSequence(mutableListOf(
                        LocalDate.of(2017, 4, 24),
                        LocalDate.of(2017, 4, 25)
                ))
                .assertComplete()
    }

    @Test
    @DisplayName("Find last update date time")
    internal fun lastUpdate() {
        scrapper.lastUpdate().test()
                .assertValues(LocalDateTime.of(2017, 4, 20, 13, 22))
                .assertComplete()
    }

    @Nested
    @DisplayName("Collect data from particular timetable update")
    inner class TimetableTest {

        private lateinit var timetable: Timetable

        @BeforeEach
        internal fun setUp() {
            timetable = scrapper.timetable()
        }

        @Test
        @DisplayName("Get all stops in direction A for a line")
        internal fun lineStops() {
            timetable.lineStops(1, Direction.A).test()
                    .assertValueCount(22)
                    .assertComplete()
        }

        @Test
        @DisplayName("Extended information about line")
        internal fun lineInfo() {
            timetable.line(1).test()
                    .assertValueCount(1)
                    .assertComplete()
        }

        @Test
        @DisplayName("Find all lines")
        internal fun lines() {
            timetable.lines().test()
                    .assertValueCount(192)
                    .assertNoErrors()
                    .assertComplete()
        }

        @Test
        @DisplayName("Find all stops")
        internal fun stops() {
            timetable.stops().test()
                    .assertValueCount(1408)
                    .assertNoErrors()
                    .assertComplete()
        }

        @Test
        @DisplayName("Gather detailed data for all lines")
        @Disabled("This is stress test, just to see how long it take to scrap detailed line data")
        internal fun gatherAll() {
            val startTime = System.currentTimeMillis()
            timetable.lines()
                    .concatMap { timetable.line(it.lineNumber) }
                    .doOnEach {
                        println("$it\nSo far: ${(System.currentTimeMillis() - startTime)/1000} [s]")
                    }
                    .test()
                    .assertNoErrors()
        }

        @Nested
        @DisplayName("Line 144 with different stop sets for each direction")
        inner class DirectionTimetablesTest {

            private lateinit var timetables: Timetable

            @BeforeEach
            internal fun setUp() {
                scrapper = Scrapper(Line144MockTimetablesSites())
                timetables = scrapper.timetable()
            }

            @Test
            @DisplayName("Check stops in direction A")
            internal fun directionA() {
                timetables.lineStops(144, Direction.A).test()
                        .assertValueCount(28)
                        .assertNoErrors()
                        .assertComplete()
            }

            @Test
            @DisplayName("Check stops in direction B")
            internal fun directionB() {
                timetables.lineStops(144, Direction.B).test()
                        .assertValueCount(30)
                        .assertNoErrors()
                        .assertComplete()
            }
        }
    }
}