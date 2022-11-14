package com.theburyat.bigbrotheriswatchingyou.ActionHandlers

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import com.intellij.openapi.ui.Messages

class MyTypedHandler: EditorActionHandler() {
    override fun execute(editor: Editor, dataContext: DataContext?) {
        val project = editor.project
        Messages.showMessageDialog(project, "Sht was copied", "Empty Title", Messages.getInformationIcon())
    }
}