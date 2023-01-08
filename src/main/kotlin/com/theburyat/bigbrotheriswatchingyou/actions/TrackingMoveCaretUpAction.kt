package com.theburyat.bigbrotheriswatchingyou.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actions.MoveCaretUpAction
import java.util.logging.Logger

class TrackingMoveCaretUpAction(logger: Logger): MoveCaretUpAction() {
    private val _logger: Logger = logger

    override fun beforeActionPerformedUpdate(e: AnActionEvent) {
        _logger.info("MoveCaretUp")
        super.beforeActionPerformedUpdate(e)
    }
}