package com.theburyat.bigbrotheriswatchingyou.actionHandlers

import com.intellij.codeInsight.editorActions.EnterHandler
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import org.slf4j.Logger

class TrackingEnterHandler(originalHandler: EditorActionHandler?, logger: Logger) : EnterHandler(originalHandler) {
    private val _logger: Logger = logger

    override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
        _logger.info("Enter")
        super.doExecute(editor, caret, dataContext)
    }
}