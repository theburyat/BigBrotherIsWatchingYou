package com.theburyat.bigbrotheriswatchingyou.listeners

import com.intellij.execution.ExecutionListener
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.runners.ExecutionEnvironment
import org.jetbrains.annotations.NotNull
import org.slf4j.Logger

const val BuildStageName = "Build"

class TrackingRunManagerListener(logger: Logger): ExecutionListener {
    private val _logger: Logger = logger

    override fun processStarted(@NotNull executorId: String, @NotNull env: ExecutionEnvironment, @NotNull handler: ProcessHandler) {
        if (env.toString() == "$BuildStageName ${env.project.name}") _logger.info("Build")
        else _logger.info("Run")

        super.processStarted(executorId, env, handler)
    }
}