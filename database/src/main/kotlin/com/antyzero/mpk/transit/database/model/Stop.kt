package com.antyzero.mpk.transit.database.model

data class Stop(
        val id: Int,
        val name: String,
        val symbol: String,
        val firstLetter: Char)