package com.theburyat.bigbrotheriswatchingyou.actionHandlers

import com.intellij.codeInsight.editorActions.SelectWordHandler
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import java.util.logging.Logger

class TrackingSelectWordHandler(originalHandler: EditorActionHandler?, logger: Logger) : SelectWordHandler(originalHandler) {
    private val _logger: Logger = logger

    override fun doExecute(editor: Editor, caret: Caret, dataContext: DataContext?) {
        _logger.info("Select")
        super.doExecute(editor, caret, dataContext)
    }
}