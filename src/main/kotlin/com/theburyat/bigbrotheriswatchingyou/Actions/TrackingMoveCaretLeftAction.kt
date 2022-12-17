package com.theburyat.bigbrotheriswatchingyou.Actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actions.MoveCaretLeftAction

class TrackingMoveCaretLeftAction: MoveCaretLeftAction() {
    override fun beforeActionPerformedUpdate(e: AnActionEvent) {
        println("Caret moved left")
        super.beforeActionPerformedUpdate(e)
    }
}