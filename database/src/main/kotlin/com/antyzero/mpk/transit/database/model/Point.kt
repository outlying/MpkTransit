package com.antyzero.mpk.transit.database.model

data class Point(
        val id: String,
        val name: String = "",
        val stopId: Int,
        val stopName: String)