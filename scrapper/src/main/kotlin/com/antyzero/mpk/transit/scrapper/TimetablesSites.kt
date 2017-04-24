package com.antyzero.mpk.transit.scrapper

import io.reactivex.Flowable
import java.time.LocalDate


interface TimetablesSites {

    fun main(): Flowable<String>

    // Lines

    fun line(line: Int, timetableDay: LocalDate = LocalDate.now()): Flowable<String>

    fun lines(timetableDay: LocalDate = LocalDate.now()): Flowable<String>

    // Stops

    fun stop(stopId: String, timetableDay: LocalDate = LocalDate.now()): Flowable<String>

    fun stops(timetableDay: LocalDate = LocalDate.now()): Flowable<String>


}