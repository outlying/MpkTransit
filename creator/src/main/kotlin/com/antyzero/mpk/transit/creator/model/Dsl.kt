package com.antyzero.mpk.transit.creator.model


internal fun <K, V> MutableMap<K, V>.putIfNotNull(key: K, value: V?) {
    if (value != null) {
        this.put(key, value)
    }
}