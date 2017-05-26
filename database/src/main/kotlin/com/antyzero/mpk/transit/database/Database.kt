package com.antyzero.mpk.transit.database

import com.antyzero.mpk.transit.database.model.*

interface Database {
    fun lines(): List<Line>
    fun points(): List<Point>
    fun schedules(): List<Schedule>
    fun routes(): List<Route>
    fun stopDepartures(): List<StopDeparture>
    fun stops(): List<Stop>
    fun streets(): List<Street>
    fun variants(): List<Variant>
}