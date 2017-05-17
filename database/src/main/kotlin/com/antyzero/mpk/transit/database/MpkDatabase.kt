package com.antyzero.mpk.transit.database

import com.antyzero.mpk.transit.database.model.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement


class MpkDatabase(databasePath: String) {

    private val connection: Connection = DriverManager.getConnection(databasePath)
    private val statement: Statement = connection.createStatement()

    fun lines() = with(statement.executeQuery("SELECT * FROM 'Lines' ORDER BY SortCol")) {
        mutableListOf<Line>().apply {
            this@with.collect {
                add(convertToLine(it))
            }
        }
    }

    fun points() = with(statement.executeQuery("SELECT * FROM 'Points'")) {
        mutableListOf<Point>().apply {
            this@with.collect {
                add(convertToPoint(it))
            }
        }
    }

    fun shedules() = with(statement.executeQuery("SELECT * FROM 'Shedules'")) {
        mutableListOf<Shedule>().apply {
            this@with.collect {
                add(convertToShedule(it))
            }
        }
    }

    fun routes() = with(statement.executeQuery("SELECT * FROM 'Routes'")) {
        mutableListOf<Route>().apply {
            this@with.collect {
                add(convertToRoute(it))
            }
        }
    }

    fun stopDepartures() = with(statement.executeQuery("SELECT * FROM 'StopDepartures'")) {
        mutableListOf<StopDeparture>().apply {
            this@with.collect {
                add(convertToStopDeparture(it))
            }
        }
    }

    fun stops() = with(statement.executeQuery("SELECT * FROM 'Stops'")){
        mutableListOf<Stop>().apply {
            this@with.collect {
                add(convertToStop(it))
            }
        }
    }

    fun streets() = with(statement.executeQuery("SELECT * FROM 'Street'")){
        mutableListOf<Street>().apply {
            this@with.collect {
                add(convertToStreet(it))
            }
        }
    }

    private fun convertToStreet(resultSet: ResultSet)=Street(
            id = resultSet.getInt("Id"),
            name = resultSet.getString("Name"),
            firstLetter = resultSet.getString("FirstLetter")
    )

    private fun convertToStop(resultSet: ResultSet) = Stop(
            id = resultSet.getInt("Id"),
            name = resultSet.getString("Name"),
            symbol = resultSet.getObject("Symbol").toString(),
            firstLetter = resultSet.getObject("firstLetter").toString().toCharArray()[0]
    )

    private fun convertToStopDeparture(resultSet: ResultSet) = StopDeparture(
            variantId = resultSet.getInt("VariantId"),
            no = resultSet.getInt("No"),
            pointId = resultSet.getInt("PointId"),
            pointName = resultSet.getString("PointName"),
            onDemand = resultSet.getBoolean("OnDemand"),
            stopId = resultSet.getInt("StopId"),
            stopName = resultSet.getString("StopName"),
            stopSymbol = resultSet.getString("StopSymbol"),
            streetId = resultSet.getInt("StreetId"),
            streetName = resultSet.getString("StreetName"),
            lineName = resultSet.getString("LineName"),
            sortCol = resultSet.getInt("SortCol"),
            lastPoint = resultSet.getInt("LastPoint"),
            lastStopId = resultSet.getInt("LastStopId"),
            lastStopName = resultSet.getString("LastStopName"),
            from = resultSet.getString("From").toDateTime(),
            to = resultSet.getString("To").toDateTime()
    )

    private fun convertToRoute(resultSet: ResultSet) = Route(
            variantId = resultSet.getInt("VariantId"),
            no = resultSet.getInt("No"),
            pointId = resultSet.getInt("PointId"),
            pointName = resultSet.getString("PointName"),
            onDemand = resultSet.getBoolean("OnDemand"),
            stopId = resultSet.getInt("StopId"),
            stopSymbol = resultSet.getString("StopSymbol"),
            streetId = resultSet.getInt("StreetId"),
            stopName = resultSet.getString("StopName"),
            streetName = resultSet.getString("StreetName")
    )

    private fun convertToShedule(resultSet: ResultSet) = Shedule(
            id = resultSet.getInt("Id"),
            type = resultSet.getInt("Type"),
            lineName = resultSet.getInt("LineName"),
            from = resultSet.getString("From").toDateTime(),
            stopFrom = resultSet.getString("StopFrom").toDateTime(),
            to = resultSet.getString("To").toDateTime(),
            lastUpdate = resultSet.getString("LastUpdate").toDateTime()
    )

    private fun convertToPoint(resultSet: ResultSet) = Point(
            id = resultSet.getInt("Id"),
            name = resultSet.getString("Name"),
            stopId = resultSet.getInt("StopId"),
            stopName = resultSet.getString("StopName")
    )

    private fun convertToLine(resultSet: ResultSet) = Line(
            name = resultSet.getInt("Name"),
            transportType = TransportType.findByValue(resultSet.getInt("LineType")),
            lineGroup = resultSet.getInt("LineGroup"),
            lineCarrier = resultSet.getInt("LineCarrier"),
            legend = resultSet.getInt("Legend")
    )

    private fun ResultSet.collect(collectingFunction: (ResultSet) -> Unit) {
        while (this.next()) {
            collectingFunction.invoke(this)
        }
    }
}

