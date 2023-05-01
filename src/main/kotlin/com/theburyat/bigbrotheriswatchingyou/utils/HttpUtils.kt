package com.theburyat.bigbrotheriswatchingyou.utils

import com.theburyat.bigbrotheriswatchingyou.models.AnalysisProcess
import khttp.structures.files.FileLike
import java.io.File

object HttpUtils {
    fun trySendLogsToServer(): Boolean {
        return try {
            val studentInfo = AnalysisProcess.studentInfo
            val response = khttp.post(
                url = "${AnalysisProcess.studentInfo.serverUrl!!}/exams",
                params = mapOf("userName" to studentInfo.name!!, "userGroup" to studentInfo.group!!),
                files = listOf(FileLike("logFile", File(AnalysisProcess.context.logFile.toString())))
            )

            response.statusCode == 200
        } catch (ex: Exception) {
            false
        }
    }
}
