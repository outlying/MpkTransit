package com.antyzero.mpk.transit.scrapper

import io.reactivex.Flowable
import okhttp3.OkHttpClient
import okhttp3.Request
import java.time.LocalDate


class MpkTimetablesSites(
        private val okHttpClient: OkHttpClient = OkHttpClient(),
        private val baseUrl: String = DEFAULT_URL) : TimetablesSites {

    override fun main(): Flowable<String> {
        val request = Request.Builder().apply {
            url(baseUrl)
        }.build()
        return execute(request)
    }

    override fun line(line: Int, timetableDay: LocalDate): Flowable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun lines(timetableDay: LocalDate): Flowable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stop(stopId: String, timetableDay: LocalDate): Flowable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stops(timetableDay: LocalDate): Flowable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun execute(request: Request): Flowable<String> =
            Flowable.just(okHttpClient.newCall(request).execute().body().string())

    companion object {
        val DEFAULT_URL = "http://rozklady.mpk.krakow.pl/"
    }


}