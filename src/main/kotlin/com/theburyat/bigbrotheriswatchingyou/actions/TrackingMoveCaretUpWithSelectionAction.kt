package com.theburyat.bigbrotheriswatchingyou.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actions.MoveCaretUpWithSelectionAction
import com.theburyat.bigbrotheriswatchingyou.enums.UserAction
import com.theburyat.bigbrotheriswatchingyou.models.AnalysisProcess

class TrackingMoveCaretUpWithSelectionAction: MoveCaretUpWithSelectionAction() {
    override fun beforeActionPerformedUpdate(e: AnActionEvent) {
        AnalysisProcess.context.logger?.info(UserAction.Select.toString())
        super.beforeActionPerformedUpdate(e)
    }
}