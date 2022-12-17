package com.theburyat.bigbrotheriswatchingyou.Actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actions.PageDownWithSelectionAction

class TrackingPageDownWithSelectionAction: PageDownWithSelectionAction() {
    override fun beforeActionPerformedUpdate(e: AnActionEvent) {
        println("Sth was selected")
        super.beforeActionPerformedUpdate(e)
    }
}