package com.antyzero.mpk.transit.scrapper.mock

import com.antyzero.mpk.transit.scrapper.site.Direction
import io.reactivex.Flowable
import java.time.LocalDate

class Line144MockTimetablesSites : MockTimetablesSites() {

    override fun line(line: Int, direction: Direction, timetableDay: LocalDate): Flowable<String> {
        return when(direction){
            Direction.A -> getFileFlowable("site_line_144_d1.html")
            Direction.B -> getFileFlowable("site_line_144_d2.html")
        }
    }
}