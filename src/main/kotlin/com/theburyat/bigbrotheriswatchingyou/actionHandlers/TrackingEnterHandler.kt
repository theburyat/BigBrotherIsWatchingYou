package com.theburyat.bigbrotheriswatchingyou.actionHandlers

import com.intellij.codeInsight.editorActions.EnterHandler
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import com.theburyat.bigbrotheriswatchingyou.enums.UserAction
import com.theburyat.bigbrotheriswatchingyou.models.AnalysisProcess

class TrackingEnterHandler(originalHandler: EditorActionHandler?) : EnterHandler(originalHandler) {
    override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
        AnalysisProcess.context.logger?.info(UserAction.Enter.toString())
        super.doExecute(editor, caret, dataContext)
    }
}