package com.theburyat.bigbrotheriswatchingyou.ActionHandlers

import com.intellij.codeInsight.editorActions.EnterHandler
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler

class TrackingEnterHandler(originalHandler: EditorActionHandler?) : EnterHandler(originalHandler) {
    override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
        println("Inserted new string")
        super.doExecute(editor, caret, dataContext)
    }
}