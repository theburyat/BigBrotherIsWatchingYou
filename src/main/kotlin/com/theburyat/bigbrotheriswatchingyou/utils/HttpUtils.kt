package com.theburyat.bigbrotheriswatchingyou.utils

import com.theburyat.bigbrotheriswatchingyou.models.AnalysisProcess
import khttp.structures.files.FileLike

object HttpUtils {
    fun trySendLogsToServer(): Boolean {
        try {
            val studentInfo = AnalysisProcess.studentInfo
            val response = khttp.post(
                url = "${AnalysisProcess.studentInfo.serverUrl!!}/exams",
                params = mapOf("userName" to studentInfo.name!!, "userGroup" to studentInfo.group!!),
                files = listOf(FileLike("logFile", AnalysisProcess.context.logFile.toString()))
            )

            return response.statusCode == 200
        } catch (ex: Exception) {
            return false
        }
    }
}
