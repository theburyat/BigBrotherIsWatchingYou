package com.theburyat.bigbrotheriswatchingyou.ActionHandlers

import com.intellij.codeInsight.editorActions.PasteHandler
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler

class TrackingPasteHandler(originalAction: EditorActionHandler?) : PasteHandler(originalAction) {
    override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
        val project = editor.project
        println("Sth was pasted")
        super.doExecute(editor, caret, dataContext)
    }
}