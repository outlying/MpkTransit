package com.antyzero.mpk.transit.scrapper.site

import io.reactivex.Flowable
import java.time.LocalDate


interface TimetablesSites {

    fun main(): Flowable<String>

    // Lines

    fun line(line: Int, direction: Direction = Direction.A, timetableDay: LocalDate = LocalDate.now()): Flowable<String>

    fun lines(timetableDay: LocalDate = LocalDate.now()): Flowable<String>

    // Stops

    fun stop(stopId: String, timetableDay: LocalDate = LocalDate.now()): Flowable<String>

    fun stops(timetableDay: LocalDate = LocalDate.now()): Flowable<String>

}

enum class Direction(val directionValue: Int) {
    A(1), B(2)
}