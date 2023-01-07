package com.theburyat.bigbrotheriswatchingyou.models

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import com.intellij.openapi.editor.actionSystem.TypedActionHandler
import com.intellij.openapi.project.Project
import com.intellij.util.messages.MessageBusConnection
import com.theburyat.bigbrotheriswatchingyou.enums.AnalysisState
import com.theburyat.bigbrotheriswatchingyou.utils.LoggerUtils
import com.theburyat.bigbrotheriswatchingyou.utils.PathUtils
import org.slf4j.Logger
import java.nio.file.Path

object AnalysisContext {

    data class Context(
        var project: Project?,
        var logger: Logger?,
        var logFile: Path?,
        var state: AnalysisState
    )

    lateinit var currentContext: Context
    lateinit var originalTypeHandler: TypedActionHandler
    lateinit var messageBusConnection: MessageBusConnection

    var originalHandlers: MutableMap<String, EditorActionHandler> = mutableMapOf()
    var originalActions: MutableMap<String, AnAction> = mutableMapOf()

    operator fun get(project: Project): Context {
        if (project.isDisposed)
            throw IllegalStateException("Project is disposed")

        if (!::currentContext.isInitialized || isDropped())
            currentContext = Context(project, null, null, AnalysisState.DISABLED)

        return currentContext
    }

    fun startAnalysis(project: Project) {
        val logFile = PathUtils.createTempFile()
        currentContext = Context(project, LoggerUtils.createLogger("Big Brother", logFile), logFile, AnalysisState.RUNNING)
    }

    fun stopAnalysis() {
        currentContext.project = null
        currentContext.logger = null
        currentContext.logFile = null
        currentContext.state = AnalysisState.DISABLED

        originalHandlers.clear()
    }

    private fun isDropped(): Boolean {
        return currentContext.project == null
                && currentContext.logger == null
                && currentContext.logFile == null
                && currentContext.state == AnalysisState.DISABLED
    }
}