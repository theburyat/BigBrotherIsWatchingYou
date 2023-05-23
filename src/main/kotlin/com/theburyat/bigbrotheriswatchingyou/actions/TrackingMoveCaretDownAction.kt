package com.theburyat.bigbrotheriswatchingyou.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actions.MoveCaretDownAction
import com.theburyat.bigbrotheriswatchingyou.enums.UserAction
import com.theburyat.bigbrotheriswatchingyou.models.AnalysisProcess

class TrackingMoveCaretDownAction: MoveCaretDownAction() {
    override fun beforeActionPerformedUpdate(e: AnActionEvent) {
        AnalysisProcess.context.logger?.info(UserAction.MoveCaretDown.toString())
        super.beforeActionPerformedUpdate(e)
    }
}