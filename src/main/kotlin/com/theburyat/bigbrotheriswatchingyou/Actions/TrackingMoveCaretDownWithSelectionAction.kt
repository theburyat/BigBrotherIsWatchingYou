package com.theburyat.bigbrotheriswatchingyou.Actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actions.MoveCaretDownWithSelectionAction

class TrackingMoveCaretDownWithSelectionAction: MoveCaretDownWithSelectionAction() {
    override fun beforeActionPerformedUpdate(e: AnActionEvent) {
        println("Sth was selected")
        super.beforeActionPerformedUpdate(e)
    }
}