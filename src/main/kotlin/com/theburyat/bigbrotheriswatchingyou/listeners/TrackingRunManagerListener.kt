package com.theburyat.bigbrotheriswatchingyou.listeners

import com.intellij.execution.ExecutionListener
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.runners.ExecutionEnvironment
import com.theburyat.bigbrotheriswatchingyou.enums.UserAction
import org.jetbrains.annotations.NotNull
import java.util.logging.Logger

const val BUILD_STAGE_NAME = "Build"

class TrackingRunManagerListener(logger: Logger): ExecutionListener {
    private val _logger: Logger = logger

    override fun processStarted(@NotNull executorId: String, @NotNull env: ExecutionEnvironment, @NotNull handler: ProcessHandler) {
        if (env.toString() == "$BUILD_STAGE_NAME ${env.project.name}") _logger.info(UserAction.Build.toString())
        else _logger.info(UserAction.Run.toString())

        super.processStarted(executorId, env, handler)
    }
}
