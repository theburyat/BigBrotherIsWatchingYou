package com.theburyat.bigbrotheriswatchingyou.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actions.MoveCaretDownWithSelectionAction
import java.util.logging.Logger

class TrackingMoveCaretDownWithSelectionAction(logger: Logger): MoveCaretDownWithSelectionAction() {
    private val _logger: Logger = logger

    override fun beforeActionPerformedUpdate(e: AnActionEvent) {
        _logger.info("Select")
        super.beforeActionPerformedUpdate(e)
    }
}