package com.antyzero.mpk.transit.database.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder


data class Shedule(
        val id : Int,
        val type: Int,
        val lineName: Int,
        val from: LocalDateTime,
        val stopFrom: LocalDateTime,
        val to : LocalDateTime,
        val lastUpdate: LocalDateTime){

    constructor(
            id : Int,
            type: Int,
            lineName: Int,
            from: String,
            stopFrom: String,
            to : String,
            lastUpdate: String
    ):this(
            id = id,
            type = type,
            lineName = lineName,
            from = LocalDateTime.parse(from, DATATIME_FORMATTER),
            stopFrom = LocalDateTime.parse(stopFrom, DATATIME_FORMATTER),
            to = LocalDateTime.parse(to, DATATIME_FORMATTER),
            lastUpdate = LocalDateTime.parse(lastUpdate, DATATIME_FORMATTER))

    companion object {
        private val DATATIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    }
}