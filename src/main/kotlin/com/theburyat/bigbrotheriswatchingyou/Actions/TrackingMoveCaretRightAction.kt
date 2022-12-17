package com.theburyat.bigbrotheriswatchingyou.Actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actions.MoveCaretRightAction

class TrackingMoveCaretRightAction: MoveCaretRightAction() {
    override fun beforeActionPerformedUpdate(e: AnActionEvent) {
        println("Caret moved right")
        super.beforeActionPerformedUpdate(e)
    }
}