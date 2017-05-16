package com.antyzero.mpk.transit.database

import java.sql.DriverManager


class MpkDatabase(databasePath : String) {

    val connection = DriverManager.getConnection(databasePath)


}