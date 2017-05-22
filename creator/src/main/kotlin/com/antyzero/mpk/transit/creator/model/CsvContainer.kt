package com.antyzero.mpk.transit.creator.model

/**
 * Universal container for CSV like data
 */
class CsvContainer<T> where T : Map<String, Any?> {

    val keys: MutableSet<String> = mutableSetOf()
    val list: MutableList<T> = mutableListOf()

    fun add(element: T): Boolean {
        element.keys
                .takeIf { it.containsAll(this.keys) }
                ?.let {
                    this.keys.clear()
                    this.keys.addAll(element.keys)
                }
        return list.add(element)
    }

    override fun toString() = keys.joinToString(separator = ",") + "\n" + stopsToString()

    private fun stopsToString(): String {
        val stopsCsv = mutableListOf<String>()
        for (stop in list) {
            val values = mutableListOf<String>()
            keys.forEachIndexed { index, key ->
                values.add(index, if (stop.containsKey(key)) {
                    stop[key].toString()
                } else {
                    ""
                })
            }
            stopsCsv.add(values.joinToString(separator = ","))
        }
        return stopsCsv.joinToString(separator = "\n")
    }
}