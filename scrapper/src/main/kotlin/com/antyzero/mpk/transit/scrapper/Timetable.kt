package com.antyzero.mpk.transit.scrapper

import com.antyzero.mpk.transit.scrapper.model.Line
import com.antyzero.mpk.transit.scrapper.model.Modifications
import com.antyzero.mpk.transit.scrapper.model.Stop
import com.antyzero.mpk.transit.scrapper.model.Type
import com.antyzero.mpk.transit.scrapper.site.Direction
import com.antyzero.mpk.transit.scrapper.site.TimetablesSites
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import java.time.LocalDate

class Timetable(private val timetablesSites: TimetablesSites, private val timetableDay: LocalDate) {

    fun line(vararg lineNumber: Int) = line(Flowable.fromIterable(lineNumber.toList()))

    fun lineF(flowable: Flowable<Line>) = line(flowable.map(Line::lineNumber))

    fun line(flowable: Flowable<Int>): Flowable<Triple<Line, List<Stop>, List<Stop>>> {
        return flowable
                .concatMap {
                    val lineNumber = it
                    val stopsA = lineStops(it, Direction.A).toList().toFlowable()
                    val stopsB = lineStops(it, Direction.B).toList().toFlowable()

                    return@concatMap lines()
                            .filter { it.lineNumber == lineNumber }
                            .zipWith(stopsA, BiFunction<Line, List<Stop>, Pair<Line, List<Stop>>> { line, stopListA ->
                                line to stopListA
                            })
                            .zipWith(stopsB, BiFunction<Pair<Line, List<Stop>>, List<Stop>, Triple<Line, List<Stop>, List<Stop>>> { lineWithStopsA, stopListB ->
                                Triple(lineWithStopsA.first, lineWithStopsA.second, stopListB)
                            })
                }
    }

    fun lineStops(lineNumber: Int, direction: Direction): Flowable<Stop> {
        return timetablesSites.line(lineNumber, direction, timetableDay)
                .concatMap { Flowable.fromIterable(REGEXP_LINE_STOPS.findAll(it).asIterable()) }
                .map {
                    val stopName = it.groupValues[1].trim()
                    val stopId = it.groupValues[2]
                    Stop(stopId, stopName)
                }
    }

    fun lines(): Flowable<Line> {
        return timetablesSites.lines(timetableDay)
                .concatMap { Flowable.fromIterable(REGEXP_LINE_DATA.findAll(it).asIterable()) }
                .map {
                    val mods = mutableSetOf<Modifications>()
                    val lineType = it.groupValues[1]
                    val lowRiderMod = it.groupValues[2]
                    val lineNumber = it.groupValues[3].toInt()

                    if (lowRiderMod == "deepskyblue") {
                        mods.add(Modifications.LOW_RIDER)
                    }

                    Line(lineNumber, Type.findByTypeName(lineType), mods)
                }
    }

    fun stops(): Flowable<Stop> {
        return timetablesSites.stops(timetableDay)
                .concatMap { Flowable.fromIterable(REGEXP_STOP.findAll(it).asIterable()) }
                .map {
                    val stopId = it.groupValues[1]
                    val stopName = it.groupValues[2].trim()
                    Stop(stopId, stopName)
                }
    }

    companion object {

        private val REGEXP_LINE_DATA = "<a.+?class='(linia\\w?)' style='\\s+border:\\s2px\\ssolid\\s(.+?);.+?>\\s+?(\\d+?)\\s+?</a>".toRegex()
        private val REGEXP_LINE_STOPS = "<td style=' text-align: right; '>(?:.+?<span.+?>)?(.+?)(?:</span>.+?)?</td>.+?<a.+?&przystanek=(\\w+?)'".toRegex(RegexOption.DOT_MATCHES_ALL)
        private val REGEXP_STOP = "&przystanek=([\\w]+?)'.+?<span.+?>(.+?)<".toRegex(RegexOption.DOT_MATCHES_ALL)
    }
}