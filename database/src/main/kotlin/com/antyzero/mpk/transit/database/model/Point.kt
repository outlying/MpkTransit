package com.antyzero.mpk.transit.database.model

data class Point(
        val id: Int,
        val name: String = "",
        val stopId: Int,
        val stopName: String)