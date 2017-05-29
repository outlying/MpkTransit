package com.antyzero.mpk.transit.database.model


data class Variant(
        val id: Int,
        val lineName: String,
        val default: Boolean,
        val type: Int,
        val sheduleId: Int,
        val letter: String,
        val name: String,
        val description: String,
        val description2: String,
        val firstPoint: Int,
        val firstStopId: Int,
        val firstStopName: String,
        val lastPoint: Int,
        val lastStopId: Int,
        val lastStopName: String,
        val pointCount: Int,
        val visible: Boolean)