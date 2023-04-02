package com.theburyat.bigbrotheriswatchingyou.actionHandlers

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import com.intellij.openapi.editor.actions.DeleteSelectionHandler
import com.theburyat.bigbrotheriswatchingyou.enums.UserAction
import java.util.logging.Logger

class TrackingDeleteHandler(handler: EditorActionHandler?, logger: Logger) : DeleteSelectionHandler(handler) {
    private val _logger: Logger = logger

    override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
        _logger.info(UserAction.Delete.toString())
        super.doExecute(editor, caret, dataContext)
    }
}