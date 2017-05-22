package com.antyzero.mpk.transit.creator

import com.antyzero.mpk.transit.creator.model.CsvContainer
import com.antyzero.mpk.transit.creator.model.Stop

interface Creator {

    fun stops(): CsvContainer<Stop>
}