package com.theburyat.bigbrotheriswatchingyou.ActionHandlers

import com.intellij.codeInsight.editorActions.SelectWordHandler
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler

class TrackingSelectWordHandler(originalHandler: EditorActionHandler?) : SelectWordHandler(originalHandler) {
    override fun doExecute(editor: Editor, caret: Caret, dataContext: DataContext?) {
        println("sth was selected")
        super.doExecute(editor, caret, dataContext)
    }
}