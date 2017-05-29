package com.antyzero.mpk.transit.database.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private object Consts {
    val DATETIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
}

fun String.toDateTime(): LocalDateTime = LocalDateTime.parse(this, Consts.DATETIME_FORMATTER)
