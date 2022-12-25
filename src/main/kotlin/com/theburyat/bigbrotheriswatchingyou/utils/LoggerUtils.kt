package com.theburyat.bigbrotheriswatchingyou.utils

import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.FileAppender
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.Path

object LoggerUtils {
    fun createLogger(name: String, file: Path): Logger {
        val lc = LoggerFactory.getILoggerFactory() as LoggerContext
        lc.reset()

        val ple = PatternLayoutEncoder()

        ple.pattern = "%d{dd.MM.yyyy HH:mm:ss}-%msg%n"
        ple.context = lc
        ple.start()
        val fileAppender = FileAppender<ILoggingEvent>()
        fileAppender.file = file.toString()
        fileAppender.encoder = ple
        fileAppender.context = lc
        fileAppender.start()

        val logger = LoggerFactory.getLogger(name) as ch.qos.logback.classic.Logger
        logger.addAppender(fileAppender)

        return logger
    }
}
