package com.theburyat.bigbrotheriswatchingyou.Actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actions.MoveCaretDownAction

class TrackingMoveCaretDownAction: MoveCaretDownAction() {
    override fun beforeActionPerformedUpdate(e: AnActionEvent) {
        println("Caret moved down")
        super.beforeActionPerformedUpdate(e)
    }
}