package com.antyzero.mpk.transit.database.model


data class Line(
        val name: Int,
        val transportType: TransportType,
        val lineGroup: Int,
        val lineCarrier: Int,
        val legend: Int)

enum class TransportType(private val value: Int) {
    TRAM(0), BUS(1);

    companion object {
        fun findByValue(findValue: Int) = TransportType.values().first { it.value == findValue }
    }
}
