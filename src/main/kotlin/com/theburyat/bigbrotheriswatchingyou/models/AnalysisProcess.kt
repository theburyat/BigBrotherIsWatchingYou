package com.theburyat.bigbrotheriswatchingyou.models

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import com.intellij.openapi.editor.actionSystem.TypedActionHandler
import com.intellij.util.messages.MessageBusConnection
import com.theburyat.bigbrotheriswatchingyou.MessageConstants
import com.theburyat.bigbrotheriswatchingyou.enums.AnalysisState
import com.theburyat.bigbrotheriswatchingyou.utils.LoggerUtils
import com.theburyat.bigbrotheriswatchingyou.utils.PathUtils
import java.util.logging.LogManager

object AnalysisProcess {
    lateinit var studentInfo: StudentInfo
    lateinit var originalTypeHandler: TypedActionHandler
    lateinit var messageBusConnection: MessageBusConnection

    var context: AnalysisContext = AnalysisContext()
    var originalHandlers: MutableMap<String, EditorActionHandler> = mutableMapOf()
    var originalActions: MutableMap<String, AnAction> = mutableMapOf()

    fun startAnalysis() {
        val logFile = PathUtils.createTempFile()
        context = AnalysisContext(
            LoggerUtils.createLogger(MessageConstants.LOGGER_NAME, logFile),
            logFile,
            AnalysisState.RUNNING)
    }

    fun stopAnalysis() {
        context.reset()
        studentInfo.reset()
        originalHandlers.clear()
        originalActions.clear()

        LogManager.getLogManager().reset()
    }
}
