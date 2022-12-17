package com.theburyat.bigbrotheriswatchingyou.Actions

import com.intellij.codeInsight.template.impl.editorActions.TemplateLineStartWithSelectionHandler
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actions.PageUpWithSelectionAction

class TrackingPageUpWithSelectionAction: PageUpWithSelectionAction() {
    override fun beforeActionPerformedUpdate(e: AnActionEvent) {
        println("Sth was selected")
        super.beforeActionPerformedUpdate(e)
    }
}
