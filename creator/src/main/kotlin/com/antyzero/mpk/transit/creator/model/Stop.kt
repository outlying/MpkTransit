package com.antyzero.mpk.transit.creator.model


data class Stop(
        val id: String,
        val name: String,
        val latitude: Float,
        val longitude: Float,
        val description: String? = null) :

        CsvData(mutableMapOf(
                "stop_id" to id,
                "stop_name" to name,
                "stop_lat" to latitude,
                "stop_lon" to longitude)
                .apply {
                    this.putIfNotNull("stop_description", description)
                })