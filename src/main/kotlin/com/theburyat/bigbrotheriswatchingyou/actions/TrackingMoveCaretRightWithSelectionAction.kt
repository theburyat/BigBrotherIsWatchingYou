package com.theburyat.bigbrotheriswatchingyou.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actions.MoveCaretRightWithSelectionAction
import com.theburyat.bigbrotheriswatchingyou.enums.UserAction
import java.util.logging.Logger

class TrackingMoveCaretRightWithSelectionAction(logger: Logger): MoveCaretRightWithSelectionAction() {
    private val _logger: Logger = logger

    override fun beforeActionPerformedUpdate(e: AnActionEvent) {
        _logger.info(UserAction.Select.toString())
        super.beforeActionPerformedUpdate(e)
    }
}