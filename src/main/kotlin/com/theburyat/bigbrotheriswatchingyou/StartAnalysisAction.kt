package com.theburyat.bigbrotheriswatchingyou

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actionSystem.EditorActionManager
import com.theburyat.bigbrotheriswatchingyou.utils.IdeEventsUtils
import com.theburyat.bigbrotheriswatchingyou.utils.LoggerUtils

class StartAnalysisAction: AnAction() {

    override fun update(e: AnActionEvent) {
        val current = e.project
        e.presentation.isEnabledAndVisible = current != null
    }

    override fun actionPerformed(e: AnActionEvent) {
        val logger = LoggerUtils.createLogger(this.javaClass.name)

        val project = e.project!!
        val actionManager = ActionManager.getInstance()
        val editorActionManager = EditorActionManager.getInstance()

        IdeEventsUtils.subscribeOnMessagesFromBus(project, logger)

        IdeEventsUtils.setCaretMoveActions(actionManager, logger)
        IdeEventsUtils.setTextSelectionActions(actionManager, logger)

        IdeEventsUtils.setCutCopyPasteHandlers(editorActionManager, logger)
        IdeEventsUtils.setTextSelectionHandlers(editorActionManager, logger)
        IdeEventsUtils.setDeleteHandlers(editorActionManager, logger)
        IdeEventsUtils.setInsertSymbolHandlers(editorActionManager, logger)
    }
}
