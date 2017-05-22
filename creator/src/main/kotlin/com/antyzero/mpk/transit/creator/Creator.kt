package com.antyzero.mpk.transit.creator

import com.antyzero.mpk.transit.creator.model.Agency
import com.antyzero.mpk.transit.creator.model.CsvContainer
import com.antyzero.mpk.transit.creator.model.Stop

interface Creator {

    fun agency(): CsvContainer<Agency>

    fun stops(): CsvContainer<Stop>
}