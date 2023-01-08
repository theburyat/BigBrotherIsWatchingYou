package com.theburyat.bigbrotheriswatchingyou.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actions.MoveCaretUpWithSelectionAction
import java.util.logging.Logger

class TrackingMoveCaretUpWithSelectionAction(logger: Logger): MoveCaretUpWithSelectionAction() {
    private val _logger: Logger = logger

    override fun beforeActionPerformedUpdate(e: AnActionEvent) {
        _logger.info("Select")
        super.beforeActionPerformedUpdate(e)
    }
}