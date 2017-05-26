package com.antyzero.mpk.transit.database

import com.antyzero.mpk.transit.database.model.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement


class MpkDatabase(databasePath: String) : Database {

    private val connection: Connection = DriverManager.getConnection(databasePath)
    private val statement: Statement = connection.createStatement()

    override fun lines() = with(statement.executeQuery("SELECT * FROM 'Lines' ORDER BY SortCol")) {
        mutableListOf<Line>().apply {
            this@with.collect {
                add(convertToLine(it))
            }
        }
    }

    override fun points() = with(statement.executeQuery("SELECT * FROM 'Points'")) {
        mutableListOf<Point>().apply {
            this@with.collect {
                add(convertToPoint(it))
            }
        }
    }

    override fun schedules() = with(statement.executeQuery("SELECT * FROM 'Shedules'")) {
        mutableListOf<Schedule>().apply {
            this@with.collect {
                add(convertToShedule(it))
            }
        }
    }

    override fun routes() = with(statement.executeQuery("SELECT * FROM 'Routes'")) {
        mutableListOf<Route>().apply {
            this@with.collect {
                add(convertToRoute(it))
            }
        }
    }

    override fun stopDepartures() = with(statement.executeQuery("SELECT * FROM 'StopDepartures'")) {
        mutableListOf<StopDeparture>().apply {
            this@with.collect {
                add(convertToStopDeparture(it))
            }
        }
    }

    override fun stops() = with(statement.executeQuery("SELECT * FROM 'Stops'")) {
        mutableListOf<Stop>().apply {
            this@with.collect {
                add(convertToStop(it))
            }
        }
    }

    override fun streets() = with(statement.executeQuery("SELECT * FROM 'Streets'")) {
        mutableListOf<Street>().apply {
            this@with.collect {
                add(convertToStreet(it))
            }
        }
    }

    override fun variants() = with(statement.executeQuery("SELECT * FROM 'Variants'")) {
        mutableListOf<Variant>().apply {
            this@with.collect {
                add(convertToVariant(it))
            }
        }
    }

    private fun convertToVariant(it: ResultSet) = Variant(
            id = it.getInt("Id"),
            lineName = it.getString("LineName"),
            default = it.getBoolean("Default"),
            type = it.getInt("Type"),
            sheduleId = it.getInt("SheduleId"),
            letter = it.getString("Letter"),
            name = it.getString("Name"),
            description = it.getString("Description"),
            description2 = it.getString("Description2"),
            firstPoint = it.getInt("FirstPoint"),
            firstStopId = it.getInt("FirstStopId"),
            firstStopName = it.getString("FirstStopName"),
            lastPoint = it.getInt("LastPoint"),
            lastStopId = it.getInt("LastStopId"),
            lastStopName = it.getString("LastStopName"),
            pointCount = it.getInt("PointCount"),
            visible = it.getBoolean("Visible"))

    private fun convertToStreet(resultSet: ResultSet) = Street(
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

    private fun convertToShedule(resultSet: ResultSet) = Schedule(
            id = resultSet.getInt("Id"),
            type = resultSet.getInt("Type"),
            lineName = resultSet.getInt("LineName"),
            from = resultSet.getString("From").toDateTime(),
            stopFrom = resultSet.getString("StopFrom").toDateTime(),
            to = resultSet.getString("To").toDateTime(),
            lastUpdate = resultSet.getString("LastUpdate").toDateTime()
    )

    private fun convertToPoint(resultSet: ResultSet) = Point(
            id = resultSet.getString("Id"),
            name = resultSet.getString("Name"),
            stopId = resultSet.getInt("StopId"),
            stopName = resultSet.getString("StopName")
    )

    private fun convertToLine(resultSet: ResultSet) = Line(
            name = resultSet.getString("Name"),
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

