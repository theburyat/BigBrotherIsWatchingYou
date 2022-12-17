package com.theburyat.bigbrotheriswatchingyou.ActionHandlers

import com.intellij.codeInsight.template.impl.editorActions.TypedActionHandlerBase
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.TypedActionHandler

class TrackingTypedHandler(originalHandler: TypedActionHandler) : TypedActionHandlerBase(originalHandler) {
    private val _originalHandler: TypedActionHandler = originalHandler

    override fun execute(editor: Editor, charTyped: Char, dataContext: DataContext) {
        println("Char typed: $charTyped")
        _originalHandler.execute(editor, charTyped, dataContext)
    }
}