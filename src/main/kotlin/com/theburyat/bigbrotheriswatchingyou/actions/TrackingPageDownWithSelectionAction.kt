package com.theburyat.bigbrotheriswatchingyou.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actions.PageDownWithSelectionAction
import java.util.logging.Logger

class TrackingPageDownWithSelectionAction(logger: Logger): PageDownWithSelectionAction() {
    private val _logger: Logger = logger

    override fun beforeActionPerformedUpdate(e: AnActionEvent) {
        _logger.info("Select")
        super.beforeActionPerformedUpdate(e)
    }
}