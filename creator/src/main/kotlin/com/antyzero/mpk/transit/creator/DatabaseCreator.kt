package com.antyzero.mpk.transit.creator

import com.antyzero.mpk.transit.creator.model.CsvContainer
import com.antyzero.mpk.transit.creator.model.Stop
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

    override fun stops(): CsvContainer<Stop> = with(points
            .sortedBy { it.stopName }
            .map {
                Stop(
                        id = it.id,
                        name = it.stopName,
                        latitude = 0f,
                        longitude = 0f
                )
            }) {
        CsvContainer<Stop>().apply { this@with.forEach { this.add(it) } }
    }
}