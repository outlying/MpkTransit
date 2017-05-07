package com.antyzero.mpk.transit.scrapper.mock

import com.antyzero.mpk.transit.scrapper.site.Direction
import com.antyzero.mpk.transit.scrapper.site.TimetablesSites
import io.reactivex.Flowable
import java.io.File
import java.io.IOException
import java.time.LocalDate
import java.util.*


open class MockTimetablesSites : TimetablesSites {

    override fun main(): Flowable<String> = getFileFlowable("site_main.html")

    override fun line(line: Int, direction: Direction, timetableDay: LocalDate): Flowable<String> = getFileFlowable("site_line.html")

    override fun lines(timetableDay: LocalDate): Flowable<String> = getFileFlowable("site_main.html")

    override fun stop(stopId: String, timetableDay: LocalDate): Flowable<String> = getFileFlowable("site_stop.html")

    override fun stops(timetableDay: LocalDate): Flowable<String> = getFileFlowable("site_stops.html")

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

    protected fun getFileFlowable(fileName: String): Flowable<String> = Flowable.just(getFile(fileName))
}