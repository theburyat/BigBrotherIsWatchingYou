package com.theburyat.bigbrotheriswatchingyou

import com.intellij.application.options.editor.EditorOptionsListener
import com.intellij.ide.actions.CopyAction
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.IdeActions
import com.intellij.openapi.editor.actionSystem.EditorActionManager
import com.intellij.openapi.keymap.KeymapManager
import com.intellij.openapi.keymap.KeymapManagerListener
import com.intellij.openapi.vfs.VirtualFileManager
import com.theburyat.bigbrotheriswatchingyou.ActionHandlers.MyTypedHandler

class StartAnalysisAction: AnAction() {

    override fun update(e: AnActionEvent) {
        val current = e.project
        e.presentation.isEnabledAndVisible = current != null
    }

    override fun actionPerformed(e: AnActionEvent) {
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val actionManager = EditorActionManager.getInstance()
        val actionHandler = actionManager.setActionHandler(IdeActions.ACTION_COPY, MyTypedHandler())
    }
}
