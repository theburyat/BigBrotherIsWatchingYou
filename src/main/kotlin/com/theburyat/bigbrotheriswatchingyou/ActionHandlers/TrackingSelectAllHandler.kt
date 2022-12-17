package com.theburyat.bigbrotheriswatchingyou.ActionHandlers

import com.intellij.codeInsight.template.impl.editorActions.SelectAllHandler
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler

class TrackingSelectAllHandler(originalHandler: EditorActionHandler?) : SelectAllHandler(originalHandler) {
    override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
        println("Sth was selected")
        super.doExecute(editor, caret, dataContext)
    }
}