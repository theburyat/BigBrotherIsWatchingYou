package com.theburyat.bigbrotheriswatchingyou.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actions.MoveCaretDownAction
import java.util.logging.Logger

class TrackingMoveCaretDownAction(logger: Logger): MoveCaretDownAction() {
    private val _logger: Logger = logger

    override fun beforeActionPerformedUpdate(e: AnActionEvent) {
        _logger.info("MoveCaretDown")
        super.beforeActionPerformedUpdate(e)
    }
}