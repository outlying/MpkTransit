package com.antyzero.mpk.transit.creator.model

class Stops(private val list: MutableList<Stop> = mutableListOf()) : MutableList<Stop> by list {

    val keys: MutableSet<String> = mutableSetOf()

    override fun add(stop: Stop): Boolean {
        stop.keys
                .takeIf { it.containsAll(keys) }
                ?.let { keys.addAll(stop.keys) }
        return list.add(stop)
    }
}