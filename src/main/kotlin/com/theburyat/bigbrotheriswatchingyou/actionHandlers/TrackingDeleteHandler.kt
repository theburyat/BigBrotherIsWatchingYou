package com.theburyat.bigbrotheriswatchingyou.actionHandlers

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import com.intellij.openapi.editor.actions.DeleteSelectionHandler
import com.theburyat.bigbrotheriswatchingyou.enums.UserAction
import com.theburyat.bigbrotheriswatchingyou.models.AnalysisProcess

class TrackingDeleteHandler(handler: EditorActionHandler?) : DeleteSelectionHandler(handler) {
    override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
        AnalysisProcess.context.logger?.info(UserAction.Delete.toString())
        super.doExecute(editor, caret, dataContext)
    }
}