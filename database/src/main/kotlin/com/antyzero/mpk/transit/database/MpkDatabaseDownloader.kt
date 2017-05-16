package com.antyzero.mpk.transit.database

import okhttp3.*
import okio.Okio
import java.io.File


class MpkDatabaseDownloader(private val okHttpClient: OkHttpClient = OkHttpClient()) {

    fun get(): String {
        val request = Request.Builder()
                .method("POST", RequestBody.create(MediaType.parse("application/json"), ""))
                .url("http://m.rozklady.mpk.krakow.pl/Services/data.asmx/GetDatabase")
                .build()

        val response = okHttpClient.newCall(request).execute().body().string()
        val downloadUrl = "\\{\"d\":\"(.+?)\"}".toRegex().find(response)!!.groupValues[1]

        val downloadRequest = Request.Builder()
                .url(downloadUrl)
                .build()

        val targetFile = File.createTempFile("mpk", ".sqlite")
        download(okHttpClient.newCall(downloadRequest).execute(), targetFile)

        return "jdbc:sqlite:${targetFile.absoluteFile}"
    }

    private fun download(response: Response, target: File) {
        val sink = Okio.buffer(Okio.sink(target))
        sink.writeAll(response.body().source())
        sink.close()
    }
}