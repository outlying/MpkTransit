package com.antyzero.mpk.transit.creator.model

/**
 * Universal container for CSV like data
 */
open class CsvContainer<T>(values:Collection<T> = emptyList(), private val validator: CsvContainer<T>.(Any?) -> Unit = {}) where T : Map<String, Any?> {

    val keys: MutableSet<String> = mutableSetOf()
    val list: MutableList<T> = mutableListOf()

    init {
        values.forEach { add(it) }
    }

    fun add(element: T): Boolean {
        validator.invoke(this,element)
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