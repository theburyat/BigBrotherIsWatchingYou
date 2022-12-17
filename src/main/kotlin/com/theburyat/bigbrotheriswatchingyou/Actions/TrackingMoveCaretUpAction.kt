package com.theburyat.bigbrotheriswatchingyou.Actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actions.MoveCaretUpAction

class TrackingMoveCaretUpAction: MoveCaretUpAction() {
    override fun beforeActionPerformedUpdate(e: AnActionEvent) {
        println("Caret moved up")
        super.beforeActionPerformedUpdate(e)
    }
}