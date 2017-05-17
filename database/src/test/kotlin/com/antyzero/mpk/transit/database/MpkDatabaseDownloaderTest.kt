package com.antyzero.mpk.transit.database

import org.junit.jupiter.api.Test


class MpkDatabaseDownloaderTest {

    @Test
    internal fun download() {
        MpkDatabaseDownloader().get()
    }
}