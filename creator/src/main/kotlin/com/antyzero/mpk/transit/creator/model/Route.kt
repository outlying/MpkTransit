package com.antyzero.mpk.transit.creator.model


data class Route(
        val id: String,
        val nameShort: String,
        val nameLong: String,
        val type: Type,
        val agencyId: String? = null) :

        CsvData(mutableMapOf(
                "route_id" to id,
                "route_short_name" to nameShort,
                "route_long_name" to nameLong,
                "route_type" to type.value.toString())
                .apply {
                    this.putIfNotNull("agency_id", agencyId)
                }) {

    enum class Type(val value: Int) {
        TRAM(0),
        BUS(3),
    }
}