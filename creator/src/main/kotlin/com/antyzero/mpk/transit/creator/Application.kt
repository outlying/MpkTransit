package com.antyzero.mpk.transit.creator

import com.antyzero.mpk.transit.creator.model.CsvContainer
import com.antyzero.mpk.transit.database.MpkDatabase
import com.antyzero.mpk.transit.database.MpkDatabaseDownloader
import java.io.File
import java.util.concurrent.atomic.AtomicLong


fun main(args: Array<String>) {

    val startTime = System.currentTimeMillis()
    val totalSize: AtomicLong = AtomicLong()

    val databasePath = MpkDatabaseDownloader().get()
    val database = MpkDatabase(databasePath)
    val creator: Creator = DatabaseCreator(database)

    val targetDir = args.takeIf { !args.isEmpty() }?.get(0).let { File(it) } // File.createTempFile("something", "").parentFile
    val csvPrinter = CsvPrinter(targetDir)

    println("Creating files in ${csvPrinter.dir}\n")

    csvPrinter.print(creator.agency(), "agency.txt").sumSize(totalSize).printResult()
    csvPrinter.print(creator.routes(), "routes.txt").sumSize(totalSize).printResult()
    csvPrinter.print(creator.stops(), "stops.txt").sumSize(totalSize).printResult()

    println("\nOperation took ${(System.currentTimeMillis() - startTime).toFloat() / 1000} [s]")
    println("Total file size: ${totalSize.toLong() / 1024} [Kb]")
}

private fun Pair<Boolean, File>.printResult() = apply {
    println(if (first) "Created ${second.name}\t${second.absolutePath}" else "Failed ${second.name}")
}

private fun Pair<Boolean, File>.sumSize(total: AtomicLong) = apply {
    total.addAndGet(second.takeIf { first }?.length() ?: 0)
}

private class CsvPrinter(val dir: File) {

    init {
        dir.mkdirs()
    }

    fun print(cvs: CsvContainer<*>, fileName: String): Pair<Boolean, File> {
        val targetFile = File(dir, fileName)
        targetFile.takeIf { it.exists() && it.isFile }?.delete()
        targetFile.takeIf { it.createNewFile() }?.let {
            it.printWriter().use {
                it.print(cvs.toString())
            }
            return true to targetFile
        }
        return false to File("")
    }
}