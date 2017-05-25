package com.antyzero.mpk.transit.creator.model

open class CsvData(internal val map: Map<String, Any?> = mutableMapOf()) : Map<String, Any?> by map {

    private val failWhiteChars = { input: String ->
        if (input.contains("\\w")) {
            throw IllegalArgumentException("Illegal white char in $input")
        }
    }
    private val failCommas = { input: String ->
        if (input.contains(',')) {
            throw IllegalArgumentException("Illegal white char in $input")
        }
    }

    init {
        map.forEach { key, value ->
            failWhiteChars.invoke(key)
            failCommas.invoke(key)
            failCommas.invoke(value.toString())
        }
    }
}