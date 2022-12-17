package com.theburyat.bigbrotheriswatchingyou.ActionHandlers

import com.intellij.codeInsight.editorActions.CutHandler
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler

class TrackingCutHandler(originalHandler: EditorActionHandler?) : CutHandler(originalHandler) {
    override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
        val project = editor.project
        println("Sth was cut")
        super.doExecute(editor, caret, dataContext)
    }
}