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

    val connection: Connection = DriverManager.getConnection(databasePath)
    val statement: Statement = connection.createStatement()

    init {
        print(lines().joinToString(separator = "\n"))
    }

    fun lines(): List<Line> {
        val resultSet = statement.executeQuery("SELECT * FROM 'Lines' ORDER BY SortCol")
        return mutableListOf<Line>().apply {
            while (resultSet.next()) {
                add(convertToLine(resultSet))
            }
        }
    }

    fun points() = with(statement.executeQuery("SELECT * FROM 'Points'")){
         mutableListOf<Point>().apply {
            while (this@with.next()) {
                add(convertToPoint(this@with))
            }
        }
    }

    fun shedules() = with(statement.executeQuery("SELECT * FROM 'Shedules'")) {
        mutableListOf<Shedule>().apply {
            while (this@with.next()) {
                add(convertToShedule(this@with))
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
}

