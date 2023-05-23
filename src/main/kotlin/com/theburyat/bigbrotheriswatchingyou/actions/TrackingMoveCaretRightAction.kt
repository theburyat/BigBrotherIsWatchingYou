package com.theburyat.bigbrotheriswatchingyou.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actions.MoveCaretRightAction
import com.theburyat.bigbrotheriswatchingyou.enums.UserAction
import com.theburyat.bigbrotheriswatchingyou.models.AnalysisProcess

class TrackingMoveCaretRightAction: MoveCaretRightAction() {
    override fun beforeActionPerformedUpdate(e: AnActionEvent) {
        AnalysisProcess.context.logger?.info(UserAction.MoveCaretRight.toString())
        super.beforeActionPerformedUpdate(e)
    }
}