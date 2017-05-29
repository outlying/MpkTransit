package com.antyzero.mpk.transit.database.model

import java.time.LocalDateTime

data class StopDeparture(
        val variantId: Int,
        val no: Int,
        val pointId: Int,
        val pointName: String,
        val onDemand: Boolean,
        val stopId: Int,
        val stopName: String,
        val stopSymbol: String,
        val streetId: Int,
        val streetName: String,
        val lineName: String,
        val sortCol: Int,
        val lastPoint: Int,
        val lastStopId: Int,
        val lastStopName: String,
        val from: LocalDateTime,
        val to: LocalDateTime)