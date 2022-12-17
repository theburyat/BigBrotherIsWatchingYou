package com.theburyat.bigbrotheriswatchingyou.ActionHandlers

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import com.intellij.openapi.editor.actions.DeleteSelectionHandler

class TrackingDeleteHandler(handler: EditorActionHandler?) : DeleteSelectionHandler(handler) {
    override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
        println("Sth was deleted")
        super.doExecute(editor, caret, dataContext)
    }
}