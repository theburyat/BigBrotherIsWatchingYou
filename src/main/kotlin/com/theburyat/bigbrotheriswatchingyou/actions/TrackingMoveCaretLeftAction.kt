package com.theburyat.bigbrotheriswatchingyou.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actions.MoveCaretLeftAction
import org.slf4j.Logger

class TrackingMoveCaretLeftAction(logger: Logger): MoveCaretLeftAction() {
    private val _logger: Logger = logger

    override fun beforeActionPerformedUpdate(e: AnActionEvent) {
        _logger.info("MoveCaretLeft")
        super.beforeActionPerformedUpdate(e)
    }
}