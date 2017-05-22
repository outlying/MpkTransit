package com.antyzero.mpk.transit.creator.model

import java.util.*

data class Agency(
        val name: String,
        val url: String,
        val timeZone: TimeZone,
        val id: String? = null,
        val language: String? = null,
        val phone: String? = null,
        val fareUrl: String? = null,
        val email: String? = null) :

        CsvData(mutableMapOf(
                "agency_name" to name,
                "agency_url" to url,
                "agency_timezone" to timeZone.toZoneId().toString())
                .apply {
                    this.putIfNotNull("agency_id", id)
                    this.putIfNotNull("agency_lang", language)
                    this.putIfNotNull("agency_phone", phone)
                    this.putIfNotNull("agency_fare_url", fareUrl)
                    this.putIfNotNull("agency_email", email)
                })