package com.theburyat.bigbrotheriswatchingyou.utils

import com.theburyat.bigbrotheriswatchingyou.models.AnalysisContext
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import kotlin.io.path.createTempFile

object PathUtils {
    fun createTempFile(): Path {
        val homeDirectory = System.getProperty("user.home")
        val formatter = DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd_HH-mm-ss").toFormatter()

        val currentDate = LocalDateTime.now().format(formatter)

        val file = Paths.get(homeDirectory, "${currentDate}-${AnalysisContext.studentInfo.name}.log").toFile()
        if (file.createNewFile()) {
            return file.toPath()
        }

        return createTempFile(prefix = homeDirectory, suffix = ".log")
        //return createTempFile(suffix = ".log")
    }
}