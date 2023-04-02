package com.theburyat.bigbrotheriswatchingyou.actionHandlers

import com.intellij.codeInsight.template.impl.editorActions.TypedActionHandlerBase
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.TypedActionHandler
import com.theburyat.bigbrotheriswatchingyou.enums.UserAction
import java.util.logging.Logger

class TrackingTypedHandler(originalHandler: TypedActionHandler, logger: Logger) : TypedActionHandlerBase(originalHandler) {
    private val _originalHandler: TypedActionHandler = originalHandler
    private val _logger: Logger = logger

    override fun execute(editor: Editor, charTyped: Char, dataContext: DataContext) {
        _logger.info("${UserAction.Type} \"$charTyped\"")
        _originalHandler.execute(editor, charTyped, dataContext)
    }
}