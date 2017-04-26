package com.antyzero.mpk.transit.scrapper.model

data class Line(
        val lineNumber: Int,
        val type: Type,
        val mods: Set<Modifications>)

enum class Type(val lineTypeName: String) {
    // Normal line
    NORMAL("linia"),

    // Night line
    NIGHT("liniaN"),

    // Route temporarily changed
    TEMPORARILY_CHANGED_ROUTE("liniaO"),

    // New line or route changed permanently
    NEW_CHANGED("liniaZ");

    companion object {

        fun findByTypeName(name: String): Type {
            values().forEach {
                if (it.lineTypeName.toLowerCase() == name.toLowerCase()) {
                    return it
                }
            }
            throw IllegalStateException("Unable to find TYPE value for \"$name\"")
        }
    }
}

enum class Modifications {

    LOW_RIDER
}