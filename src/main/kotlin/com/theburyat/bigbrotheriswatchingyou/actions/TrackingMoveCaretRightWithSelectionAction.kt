package com.theburyat.bigbrotheriswatchingyou.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actions.MoveCaretRightWithSelectionAction
import org.slf4j.Logger

class TrackingMoveCaretRightWithSelectionAction(logger: Logger): MoveCaretRightWithSelectionAction() {
    private val _logger: Logger = logger

    override fun beforeActionPerformedUpdate(e: AnActionEvent) {
        _logger.info("Select")
        super.beforeActionPerformedUpdate(e)
    }
}