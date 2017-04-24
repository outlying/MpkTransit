package com.antyzero.mpk.transit.scrapper

import io.reactivex.Flowable
import java.io.File
import java.io.IOException
import java.time.LocalDate
import java.util.*


class MockTimetablesSites : TimetablesSites {

    override fun main(): Flowable<String> = Flowable.just("site_main.html")

    override fun line(line: Int, timetableDay: LocalDate): Flowable<String> = Flowable.just("site_line.html")

    override fun lines(timetableDay: LocalDate): Flowable<String> = Flowable.just("site_main.html")

    override fun stop(stopId: String, timetableDay: LocalDate): Flowable<String> = Flowable.just("site_stop.html")

    override fun stops(timetableDay: LocalDate): Flowable<String> = Flowable.just(getFile("site_stops.html"))

    private fun getFile(fileName: String): String {
        val result = StringBuilder("")

        val classLoader = javaClass.classLoader
        val file = File(classLoader.getResource(fileName)!!.file)

        try {
            Scanner(file).use({ scanner ->
                while (scanner.hasNextLine()) {
                    val line = scanner.nextLine()
                    result.append(line).append("\n")
                }
            })
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result.toString()

    }
}