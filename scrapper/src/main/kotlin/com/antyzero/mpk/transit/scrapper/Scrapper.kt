package com.antyzero.mpk.transit.scrapper

import io.reactivex.Flowable
import java.time.LocalDate
import java.time.LocalDateTime


class Scrapper(private val timetablesSites: TimetablesSites) {

    fun timetableCurrent(): Flowable<LocalDate> = timetableDates().take(1)

    fun timetableDates(): Flowable<LocalDate> {
        return timetablesSites.main()
                .concatMap { Flowable.fromIterable(REGEXP_TIMETABLE_DATE.findAll(it).asIterable()) }
                .map {
                    LocalDate.of(
                            it.groupValues[1].toInt(),
                            it.groupValues[2].toInt(),
                            it.groupValues[3].toInt())
                }
    }

    fun lastUpdate(): Flowable<LocalDateTime> {
        return timetablesSites.main()
                .concatMap { Flowable.fromIterable(REGEXP_LAST_UPDATE.findAll(it).asIterable()) }
                .map {
                    LocalDateTime.of(
                            it.groupValues[1].toInt(),
                            it.groupValues[2].toInt(),
                            it.groupValues[3].toInt(),
                            it.groupValues[4].toInt(),
                            it.groupValues[5].toInt())
                }
    }

    fun timetable(timetableDay: LocalDate) = Timetable(timetablesSites, timetableDay)

    companion object {

        private val REGEXP_TIMETABLE_DATE = "rozklad=(\\d{4})(\\d{2})(\\d{2})\'.+?\\d{4}-\\d{2}-\\d{2}".toRegex()
        private val REGEXP_LAST_UPDATE = "aktualizacja (\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2})".toRegex()
    }
}