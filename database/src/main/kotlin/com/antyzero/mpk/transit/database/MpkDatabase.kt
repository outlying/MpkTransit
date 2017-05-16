package com.antyzero.mpk.transit.database

import com.antyzero.mpk.transit.database.model.Line
import com.antyzero.mpk.transit.database.model.Point
import com.antyzero.mpk.transit.database.model.Shedule
import com.antyzero.mpk.transit.database.model.TransportType
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

    private fun convertToShedule(resultSet: ResultSet) = Shedule(
            id = resultSet.getInt("Id"),
            type = resultSet.getInt("Type"),
            lineName = resultSet.getInt("LineName"),
            from = resultSet.getString("From"),
            stopFrom = resultSet.getString("StopFrom"),
            to = resultSet.getString("To"),
            lastUpdate = resultSet.getString("LastUpdate")
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

