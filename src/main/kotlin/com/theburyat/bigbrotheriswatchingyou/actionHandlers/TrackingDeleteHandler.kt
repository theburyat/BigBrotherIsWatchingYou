package com.theburyat.bigbrotheriswatchingyou.actionHandlers

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import com.intellij.openapi.editor.actions.DeleteSelectionHandler
import org.slf4j.Logger

class TrackingDeleteHandler(handler: EditorActionHandler?, logger: Logger) : DeleteSelectionHandler(handler) {
    private val _logger: Logger = logger

    override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
        _logger.info("Delete")
        super.doExecute(editor, caret, dataContext)
    }
}