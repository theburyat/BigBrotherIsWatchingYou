package com.theburyat.bigbrotheriswatchingyou.actions

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actionSystem.EditorActionManager
import com.theburyat.bigbrotheriswatchingyou.enums.AnalysisState
import com.theburyat.bigbrotheriswatchingyou.models.AnalysisContext
import com.theburyat.bigbrotheriswatchingyou.utils.IdeEventsUtils

class StartAnalysisAction: AnAction() {

    override fun update(e: AnActionEvent) {
        val project = e.project
        e.presentation.isVisible = project != null
        e.presentation.isEnabled = project != null && AnalysisContext[project].state == AnalysisState.DISABLED
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project!!
        val actionManager = ActionManager.getInstance()
        val editorActionManager = EditorActionManager.getInstance()

        AnalysisContext.startAnalysis(project)
        IdeEventsUtils.setLoggerToUserActions(project, actionManager, editorActionManager, AnalysisContext[project].logger!!)
    }
}