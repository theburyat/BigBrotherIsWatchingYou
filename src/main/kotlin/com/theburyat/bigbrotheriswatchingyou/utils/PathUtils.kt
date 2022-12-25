package com.theburyat.bigbrotheriswatchingyou.utils

import java.nio.file.Path
import kotlin.io.path.createTempFile

object PathUtils {
    fun createTempFile(): Path {
        return createTempFile(suffix = ".log")
    }
}