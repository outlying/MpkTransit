package com.antyzero.mpk.transit.database.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder


data class Shedule(
        val id: Int,
        val type: Int,
        val lineName: Int,
        val from: LocalDateTime,
        val stopFrom: LocalDateTime,
        val to: LocalDateTime,
        val lastUpdate: LocalDateTime)