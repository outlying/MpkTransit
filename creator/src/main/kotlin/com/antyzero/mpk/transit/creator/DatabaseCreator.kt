package com.antyzero.mpk.transit.creator

import com.antyzero.mpk.transit.creator.model.Agency
import com.antyzero.mpk.transit.creator.model.CsvContainer
import com.antyzero.mpk.transit.creator.model.Route
import com.antyzero.mpk.transit.creator.model.Stop
import com.antyzero.mpk.transit.database.MpkDatabase
import com.antyzero.mpk.transit.database.model.TransportType
import java.util.*


class DatabaseCreator(mpkDatabase: MpkDatabase) : Creator {

    private val lines = mpkDatabase.lines()
    private val points = mpkDatabase.points()
    private val shedules = mpkDatabase.shedules()
    private val routesMpk = mpkDatabase.routes()
    private val stopDepartures = mpkDatabase.stopDepartures()
    private val stopsMpk = mpkDatabase.stops()
    private val streets = mpkDatabase.streets()
    private val variants = mpkDatabase.variants()

    private val agency: CsvContainer<Agency> by lazy {
        CsvContainer(Agency(
                id = "0",
                name = "MPK S.A. w Krakowie",
                url = "http://www.mpk.krakow.pl/",
                timeZone = TimeZone.getTimeZone("Europe/Warsaw")).single(),checkForDuplicates())
    }

    private val stops: CsvContainer<Stop> by lazy {
        with(points
                .sortedBy { it.stopName }
                .map {
                    Stop(
                            id = it.id,
                            name = it.stopName,
                            latitude = 0f,
                            longitude = 0f
                    )
                }) {
            CsvContainer(this, checkForDuplicates())
        }
    }

    private val routes: CsvContainer<Route> by lazy {
        with(lines
                .map {
                    Route(
                            id = it.name,
                            nameShort = it.name,
                            nameLong = it.name,
                            type = when (it.transportType) {
                                TransportType.TRAM -> Route.Type.TRAM
                                TransportType.BUS -> Route.Type.BUS
                                else -> throw IllegalArgumentException("Unsupported TransportType value ${it.transportType}")
                            },
                            agencyId = agency.list[0].id // Only one agency in Kraków
                    )
                }) {
            CsvContainer(this, checkForDuplicates())
        }
    }

    override fun agency(): CsvContainer<Agency> = agency

    override fun stops(): CsvContainer<Stop> = stops

    override fun routes(): CsvContainer<Route> = routes

    /**
     * Validator method, universal
     */
    private fun <T : Map<String, Any?>> checkForDuplicates(): CsvContainer<T>.(Any?) -> Unit = { stop ->
        list.forEach {
            if (it == stop) {
                throw IllegalStateException("Duplicate value $it")
            }
        }
    }

}

private fun <T> T.single() = arrayListOf(this)
