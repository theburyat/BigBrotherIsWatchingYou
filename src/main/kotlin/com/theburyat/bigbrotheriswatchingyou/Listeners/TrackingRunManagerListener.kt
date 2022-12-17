package com.theburyat.bigbrotheriswatchingyou.Listeners

import com.intellij.execution.ExecutionListener
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.runners.ExecutionEnvironment
import org.jetbrains.annotations.NotNull

const val BuildStageName = "Build"

class TrackingRunManagerListener: ExecutionListener {

    override fun processStarted(@NotNull executorId: String, @NotNull env: ExecutionEnvironment, @NotNull handler: ProcessHandler) {
        if (env.toString() == "$BuildStageName ${env.project.name}") println("build")
        else println("run")

        super.processStarted(executorId, env, handler)
    }
}