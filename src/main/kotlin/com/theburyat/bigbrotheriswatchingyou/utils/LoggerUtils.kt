package com.theburyat.bigbrotheriswatchingyou.utils

import java.nio.file.Path
import java.util.logging.FileHandler
import java.util.logging.Level
import java.util.logging.Logger
import java.util.logging.SimpleFormatter

object LoggerUtils {
    fun createLogger(name: String, file: Path): Logger {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1\$tF_%1\$tT %5\$s%n")

        val fileHandler = FileHandler(file.toString())
        fileHandler.formatter = SimpleFormatter()
        fileHandler.level = Level.INFO

        val logger = Logger.getLogger(name)
        logger.level = Level.INFO
        logger.addHandler(fileHandler)
        logger.handlers.filter { x -> x == fileHandler }

        return logger
    }
}
