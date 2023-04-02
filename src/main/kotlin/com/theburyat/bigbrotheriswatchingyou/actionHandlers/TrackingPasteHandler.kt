package com.theburyat.bigbrotheriswatchingyou.actionHandlers

import com.intellij.codeInsight.editorActions.PasteHandler
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import com.theburyat.bigbrotheriswatchingyou.enums.UserAction
import java.util.logging.Logger

class TrackingPasteHandler(originalAction: EditorActionHandler?, logger: Logger) : PasteHandler(originalAction) {
    private val _logger: Logger = logger

    override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
        _logger.info(UserAction.Paste.toString())
        super.doExecute(editor, caret, dataContext)
    }
}