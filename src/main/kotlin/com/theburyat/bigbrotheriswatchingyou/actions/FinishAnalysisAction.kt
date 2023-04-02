package com.theburyat.bigbrotheriswatchingyou.actions

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actionSystem.EditorActionManager
import com.intellij.openapi.ui.Messages
import com.theburyat.bigbrotheriswatchingyou.MessageConstants
import com.theburyat.bigbrotheriswatchingyou.enums.AnalysisState
import com.theburyat.bigbrotheriswatchingyou.models.AnalysisProcess
import com.theburyat.bigbrotheriswatchingyou.utils.HttpUtils
import com.theburyat.bigbrotheriswatchingyou.utils.IdeEventsUtils

class FinishAnalysisAction: AnAction() {

    override fun update(e: AnActionEvent) {
        val project = e.project
        e.presentation.isVisible = project != null
        e.presentation.isEnabled = project != null && AnalysisProcess.context.state == AnalysisState.RUNNING
    }

    override fun actionPerformed(e: AnActionEvent) {
        val actionManager = ActionManager.getInstance()
        val editorActionManager = EditorActionManager.getInstance()

        IdeEventsUtils.disableActionsLogging(actionManager, editorActionManager)

        val isLogsSent = HttpUtils.trySendLogsToServer()
        if (!isLogsSent) {
            Messages.showMessageDialog(
                e.project,
                MessageConstants.ERROR_CAN_NOT_SEND_LOGS_MESSAGE,
                MessageConstants.ERROR_CAN_NOT_SEND_LOGS_TITLE,
                Messages.getErrorIcon()
            )
        }

        AnalysisProcess.stopAnalysis()
    }
}