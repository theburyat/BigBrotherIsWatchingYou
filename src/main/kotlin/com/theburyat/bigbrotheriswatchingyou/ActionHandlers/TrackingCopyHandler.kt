package com.theburyat.bigbrotheriswatchingyou.ActionHandlers

import com.intellij.codeInsight.editorActions.CopyHandler
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler


class TrackingCopyHandler(originalHandler: EditorActionHandler?) : CopyHandler(originalHandler) {
    override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
        println("Sht was copied")
        super.doExecute(editor, caret, dataContext)
    }
}