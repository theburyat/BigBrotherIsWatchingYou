package com.theburyat.bigbrotheriswatchingyou.actionHandlers

import com.intellij.codeInsight.template.impl.editorActions.TemplateLineEndWithSelectionHandler
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import com.theburyat.bigbrotheriswatchingyou.enums.UserAction
import com.theburyat.bigbrotheriswatchingyou.models.AnalysisProcess

class TrackingLineEndWithSelectionHandler(originalHandler: EditorActionHandler?) : TemplateLineEndWithSelectionHandler(originalHandler) {
    override fun doExecute(editor: Editor, caret: Caret, dataContext: DataContext?) {
        AnalysisProcess.context.logger?.info(UserAction.Select.toString())
        super.doExecute(editor, caret, dataContext)
    }
}