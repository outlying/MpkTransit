package com.antyzero.mpk.transit.scrapper

import com.antyzero.mpk.transit.scrapper.model.Line
import com.antyzero.mpk.transit.scrapper.model.Modifications
import com.antyzero.mpk.transit.scrapper.model.Stop
import com.antyzero.mpk.transit.scrapper.model.Type
import com.antyzero.mpk.transit.scrapper.site.TimetablesSites
import io.reactivex.Flowable
import java.time.LocalDate

class Timetable(private val timetablesSites: TimetablesSites, private val timetableDay: LocalDate) {

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
                    Stop()
                }
    }

    companion object {

        private val REGEXP_LINE_DATA = "<a.+?class='(linia\\w?)' style='\\s+border:\\s2px\\ssolid\\s(.+?);.+?>\\s+?(\\d+?)\\s+?<\\/a>".toRegex()
        private val REGEXP_STOP = "&przystanek=([\\w]+?)'.+?<span.+?>(.+?)<".toRegex(RegexOption.DOT_MATCHES_ALL)
    }
}