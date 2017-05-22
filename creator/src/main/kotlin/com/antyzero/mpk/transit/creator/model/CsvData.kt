package com.antyzero.mpk.transit.creator.model

open class CsvData(internal val map: Map<String, Any?> = mutableMapOf()) : Map<String, Any?> by map {

    init {
        map.forEach { t, u ->
            
        }
    }
}