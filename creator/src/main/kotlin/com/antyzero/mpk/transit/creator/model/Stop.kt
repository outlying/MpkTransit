package com.antyzero.mpk.transit.creator.model


data class Stop constructor(val map: Map<String, Any?> = mutableMapOf()) : Map<String, Any?> by map {

    constructor(
            id: String,
            name: String,
            latitude: Float,
            longitude: Float,
            description: String? = null) : this(mutableMapOf(
            "stop_id" to id,
            "stop_name" to name,
            "stop_lat" to latitude,
            "stop_lon" to longitude).apply {

        this.putIfNotNull("stop_description", description)
    })
}