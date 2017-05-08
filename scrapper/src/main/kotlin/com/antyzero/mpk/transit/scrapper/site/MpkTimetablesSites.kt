package com.antyzero.mpk.transit.scrapper.site

import io.reactivex.Flowable
import okhttp3.OkHttpClient
import okhttp3.Request
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MpkTimetablesSites(
        private val okHttpClient: OkHttpClient = OkHttpClient(),
        private val baseUrl: String = DEFAULT_URL) : TimetablesSites {

    override fun main(): Flowable<String> {
        val request = Request.Builder().apply {
            url(baseUrl)
        }.build()
        return execute(request)
    }

    override fun line(line: Int, direction: Direction, timetableDay: LocalDate): Flowable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun lines(timetableDay: LocalDate): Flowable<String> {
        val request = Request.Builder().apply {
            url(baseUrl)
        }.build()
        return execute(request)
    }

    override fun stop(stopId: String, timetableDay: LocalDate): Flowable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stops(timetableDay: LocalDate): Flowable<String> {
        val request = Request.Builder().apply {
            url(baseUrl + "?rozklad=${timetableDay.format(DATE_FORMAT)}&akcja=przystanek")
        }.build()
        return execute(request)
    }

    private fun execute(request: Request): Flowable<String> =
            Flowable.just(okHttpClient.newCall(request).execute().body().string())

    companion object {
        private const val DEFAULT_URL = "http://rozklady.mpk.krakow.pl/"
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("ddMMyyyy")
    }


}