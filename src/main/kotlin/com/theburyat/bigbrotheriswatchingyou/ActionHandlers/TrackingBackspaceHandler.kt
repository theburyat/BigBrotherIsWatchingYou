package com.theburyat.bigbrotheriswatchingyou.ActionHandlers

import com.intellij.codeInsight.editorActions.BackspaceHandler
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler

class TrackingBackspaceHandler(originalHandler: EditorActionHandler?) : BackspaceHandler(originalHandler) {
    override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
        println("Sth was deleted")
        super.doExecute(editor, caret, dataContext)
    }
}