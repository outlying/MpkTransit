package com.antyzero.mpk.transit.creator

import com.antyzero.mpk.transit.creator.model.Stops

interface Creator {

    fun stops(): Stops
}