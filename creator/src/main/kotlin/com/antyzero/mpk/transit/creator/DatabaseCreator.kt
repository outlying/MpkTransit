package com.antyzero.mpk.transit.creator

import com.antyzero.mpk.transit.database.MpkDatabase


class DatabaseCreator(mpkDatabase: MpkDatabase) : Creator {

    private val lines = mpkDatabase.lines()
    private val points = mpkDatabase.points()
    private val shedules = mpkDatabase.shedules()
    private val routes = mpkDatabase.routes()
    private val stopDepartures = mpkDatabase.stopDepartures()
    private val stops = mpkDatabase.stops()
    private val streets = mpkDatabase.streets()
    private val variants = mpkDatabase.variants()

    override fun stops(): List<Stop> {

        return points
                .sortedBy { it.stopName }
                .map {
                    Stop(
                            stop_id = it.id.toString(),
                            stop_name = it.stopName,
                            stop_lat = 0f.toString())
                }
    }
}