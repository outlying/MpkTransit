package com.antyzero.mpk.transit.database.model


data class Route(
        val variantId: Int,
        val no: Int,
        val pointId: Int,
        val pointName: String,
        val onDemand: Boolean,
        val stopId: Int,
        val stopSymbol: String,
        val streetId: Int,
        val stopName: String,
        val streetName: String?)