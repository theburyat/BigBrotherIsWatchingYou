package com.theburyat.bigbrotheriswatchingyou

import com.intellij.codeInsight.lookup.LookupManagerListener
import com.intellij.execution.ExecutionManager
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.IdeActions
import com.intellij.openapi.editor.actionSystem.EditorActionManager
import com.theburyat.bigbrotheriswatchingyou.ActionHandlers.TrackingTypedHandler
import com.theburyat.bigbrotheriswatchingyou.ActionHandlers.*
import com.theburyat.bigbrotheriswatchingyou.Actions.*
import com.theburyat.bigbrotheriswatchingyou.Listeners.TrackingLookUpManagerListener
import com.theburyat.bigbrotheriswatchingyou.Listeners.TrackingRunManagerListener

class StartAnalysisAction: AnAction() {

    override fun update(e: AnActionEvent) {
        val current = e.project
        e.presentation.isEnabledAndVisible = current != null
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project!!
        val actionManager = ActionManager.getInstance()
        val editorActionManager = EditorActionManager.getInstance()

        project.messageBus.connect().subscribe(LookupManagerListener.TOPIC, TrackingLookUpManagerListener(project))
        project.messageBus.connect().subscribe(ExecutionManager.EXECUTION_TOPIC, TrackingRunManagerListener())

        val typedAction = editorActionManager.typedAction
        typedAction.setupHandler(TrackingTypedHandler(typedAction.handler))

        SetCaretMoveActions(actionManager)
        SetTextSelectionActions(actionManager)

        SetCutCopyPasteHandlers(editorActionManager)
        SetTextSelectionHandlers(editorActionManager)
        SetOtherHandlers(editorActionManager)

    }

    private fun SetOtherHandlers(editorActionManager: EditorActionManager) {
        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_ENTER,
            TrackingEnterHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_ENTER)))

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_DELETE,
            TrackingDeleteHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_DELETE)))

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_BACKSPACE,
            TrackingBackspaceHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_BACKSPACE))
        )
    }

    private fun SetCutCopyPasteHandlers(editorActionManager: EditorActionManager) {
        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_COPY,
            TrackingCopyHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_COPY)))

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_PASTE,
            TrackingPasteHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_PASTE)))

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_CUT,
            TrackingCutHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_CUT)))
    }

    private fun SetCaretMoveActions(actionManager: ActionManager) {
        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_UP,
            TrackingMoveCaretUpAction())

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN,
            TrackingMoveCaretDownAction())

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT,
            TrackingMoveCaretLeftAction())

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT,
            TrackingMoveCaretRightAction())
    }

    private fun SetTextSelectionHandlers(editorActionManager: EditorActionManager) {
        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_SELECT_WORD_AT_CARET,
            TrackingSelectWordHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_SELECT_WORD_AT_CARET)))

        editorActionManager.setActionHandler(
            IdeActions.ACTION_SELECT_ALL,
            TrackingSelectAllHandler(editorActionManager.getActionHandler(IdeActions.ACTION_SELECT_ALL))
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_MOVE_LINE_START_WITH_SELECTION,
            TrackingLineStartWithSelectionHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_MOVE_LINE_START_WITH_SELECTION)))

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_MOVE_LINE_END_WITH_SELECTION,
            TrackingLineEndWithSelectionHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_MOVE_LINE_END_WITH_SELECTION)))
    }

    private fun SetTextSelectionActions(actionManager: ActionManager) {
        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_UP_WITH_SELECTION,
            TrackingMoveCaretUpWithSelectionAction())

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN_WITH_SELECTION,
            TrackingMoveCaretDownWithSelectionAction())

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT_WITH_SELECTION,
            TrackingMoveCaretLeftWithSelectionAction())

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT_WITH_SELECTION,
            TrackingMoveCaretRightWithSelectionAction())

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_UP_WITH_SELECTION,
            TrackingPageUpWithSelectionAction())

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_DOWN_WITH_SELECTION,
            TrackingPageDownWithSelectionAction())
    }
}
