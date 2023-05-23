package com.theburyat.bigbrotheriswatchingyou.actions

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actionSystem.EditorActionManager
import com.intellij.openapi.ui.Messages
import com.theburyat.bigbrotheriswatchingyou.MessageConstants
import com.theburyat.bigbrotheriswatchingyou.dialogs.StudentInfoDialog
import com.theburyat.bigbrotheriswatchingyou.enums.AnalysisState
import com.theburyat.bigbrotheriswatchingyou.models.AnalysisProcess
import com.theburyat.bigbrotheriswatchingyou.utils.IdeEventsUtils

class StartAnalysisAction: AnAction() {

    override fun update(e: AnActionEvent) {
        val project = e.project
        e.presentation.isVisible = project != null
        e.presentation.isEnabled = project != null && project.isOpen && AnalysisProcess.context.state == AnalysisState.DISABLED
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project!!
        val actionManager = ActionManager.getInstance()
        val editorActionManager = EditorActionManager.getInstance()

        val dialog = StudentInfoDialog(project)
        val isDialogOk = dialog.showAndGet()

        val errorMessage = when {
            !isDialogOk -> MessageConstants.DIALOG_INVALID_DIALOG
            !dialog.isNameValid() -> MessageConstants.DIALOG_INVALID_NAME
            !dialog.isGroupValid() -> MessageConstants.DIALOG_INVALID_GROUP
            !dialog.isServerUrlValid() -> MessageConstants.DIALOG_INVALID_SERVER_URI
            else -> null
        }

        if (errorMessage != null) {
            Messages.showMessageDialog(
                project,
                errorMessage,
                MessageConstants.ERROR_INVALID_INFO_TITLE,
                Messages.getErrorIcon()
            )

            return
        }

        AnalysisProcess.studentInfo = dialog.getStudentInfo()
        AnalysisProcess.startAnalysis()
        IdeEventsUtils.addActionsLogging(project, actionManager, editorActionManager)
    }
}
