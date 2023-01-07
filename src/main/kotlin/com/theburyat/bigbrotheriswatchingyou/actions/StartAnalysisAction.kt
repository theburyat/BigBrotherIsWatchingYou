package com.theburyat.bigbrotheriswatchingyou.actions

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actionSystem.EditorActionManager
import com.intellij.openapi.ui.Messages
import com.theburyat.bigbrotheriswatchingyou.MessageConstants
import com.theburyat.bigbrotheriswatchingyou.dialogs.StudentInfoDialog
import com.theburyat.bigbrotheriswatchingyou.enums.AnalysisState
import com.theburyat.bigbrotheriswatchingyou.models.AnalysisContext
import com.theburyat.bigbrotheriswatchingyou.utils.IdeEventsUtils

class StartAnalysisAction: AnAction() {

    override fun update(e: AnActionEvent) {
        val project = e.project
        e.presentation.isVisible = project != null
        e.presentation.isEnabled = project != null && AnalysisContext[project].state == AnalysisState.DISABLED
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project!!
        val actionManager = ActionManager.getInstance()
        val editorActionManager = EditorActionManager.getInstance()

        val dialog = StudentInfoDialog(project)
        val isDialogOk = dialog.showAndGet()
        if (!isDialogOk || !dialog.isFilled()) {
            val errorMessage =
                if (isDialogOk) MessageConstants.studentInfoDialogInvalidMessage
                else MessageConstants.studentInfoDialogSkippedMessage

            Messages.showMessageDialog(
                project,
                errorMessage,
                MessageConstants.studentInfoDialogInvalidTitle,
                Messages.getErrorIcon()
            )
            return
        }

        dialog.fillContextStudentInfo()
        AnalysisContext.startAnalysis(project)
        IdeEventsUtils.setLoggerToUserActions(project, actionManager, editorActionManager, AnalysisContext[project].logger!!)
    }
}
