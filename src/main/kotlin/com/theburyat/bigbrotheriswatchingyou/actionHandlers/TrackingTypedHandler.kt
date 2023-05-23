package com.theburyat.bigbrotheriswatchingyou.actionHandlers

import com.intellij.codeInsight.template.impl.editorActions.TypedActionHandlerBase
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.TypedActionHandler
import com.theburyat.bigbrotheriswatchingyou.enums.UserAction
import com.theburyat.bigbrotheriswatchingyou.models.AnalysisProcess

class TrackingTypedHandler(originalHandler: TypedActionHandler) : TypedActionHandlerBase(originalHandler) {
    private val _originalHandler: TypedActionHandler = originalHandler

    override fun execute(editor: Editor, charTyped: Char, dataContext: DataContext) {
        AnalysisProcess.context.logger?.info("${UserAction.Type} \"$charTyped\"")
        _originalHandler.execute(editor, charTyped, dataContext)
    }
}