package com.theburyat.bigbrotheriswatchingyou.actionHandlers

import com.intellij.codeInsight.editorActions.CopyHandler
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import org.slf4j.Logger


class TrackingCopyHandler(originalHandler: EditorActionHandler?, logger: Logger) : CopyHandler(originalHandler) {
    private val _logger: Logger = logger

    override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
        _logger.info("Copy")
        super.doExecute(editor, caret, dataContext)
    }
}