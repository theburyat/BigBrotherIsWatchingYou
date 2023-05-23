package com.theburyat.bigbrotheriswatchingyou.listeners

import com.intellij.execution.ExecutionListener
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.runners.ExecutionEnvironment
import com.theburyat.bigbrotheriswatchingyou.enums.UserAction
import com.theburyat.bigbrotheriswatchingyou.models.AnalysisProcess
import org.jetbrains.annotations.NotNull

const val BUILD_STAGE_NAME = "Build"

class TrackingRunManagerListener: ExecutionListener {
    override fun processStarted(@NotNull executorId: String, @NotNull env: ExecutionEnvironment, @NotNull handler: ProcessHandler) {
        if (env.toString() == "$BUILD_STAGE_NAME ${env.project.name}")
            AnalysisProcess.context.logger?.info(UserAction.Build.toString())
        else
            AnalysisProcess.context.logger?.info(UserAction.Run.toString())

        super.processStarted(executorId, env, handler)
    }
}
