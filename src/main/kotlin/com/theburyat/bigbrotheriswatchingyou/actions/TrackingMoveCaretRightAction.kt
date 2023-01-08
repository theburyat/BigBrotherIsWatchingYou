package com.theburyat.bigbrotheriswatchingyou.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actions.MoveCaretRightAction
import java.util.logging.Logger

class TrackingMoveCaretRightAction(logger: Logger): MoveCaretRightAction() {
    private val _logger: Logger = logger

    override fun beforeActionPerformedUpdate(e: AnActionEvent) {
        _logger.info("MoveCaretRight")
        super.beforeActionPerformedUpdate(e)
    }
}