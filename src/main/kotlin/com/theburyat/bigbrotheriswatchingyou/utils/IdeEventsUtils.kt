package com.theburyat.bigbrotheriswatchingyou.utils

import com.intellij.codeInsight.lookup.LookupManagerListener
import com.intellij.execution.ExecutionManager
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.IdeActions
import com.intellij.openapi.editor.actionSystem.EditorActionManager
import com.intellij.openapi.project.Project
import com.theburyat.bigbrotheriswatchingyou.actionHandlers.*
import com.theburyat.bigbrotheriswatchingyou.actions.*
import com.theburyat.bigbrotheriswatchingyou.listeners.TrackingLookUpManagerListener
import com.theburyat.bigbrotheriswatchingyou.listeners.TrackingRunManagerListener
import org.slf4j.Logger

object IdeEventsUtils {
    fun subscribeOnMessagesFromBus(project: Project, logger: Logger) {
        project.messageBus.connect().subscribe(LookupManagerListener.TOPIC, TrackingLookUpManagerListener(logger))
        project.messageBus.connect().subscribe(ExecutionManager.EXECUTION_TOPIC, TrackingRunManagerListener(logger))
    }

    fun setInsertSymbolHandlers(editorActionManager: EditorActionManager, logger: Logger) {
        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_ENTER,
            TrackingEnterHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_ENTER), logger)
        )

        val typedAction = editorActionManager.typedAction
        typedAction.setupHandler(TrackingTypedHandler(typedAction.handler, logger))
    }

    fun setDeleteHandlers(editorActionManager: EditorActionManager, logger: Logger) {
        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_DELETE,
            TrackingDeleteHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_DELETE), logger)
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_BACKSPACE,
            TrackingBackspaceHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_BACKSPACE), logger)
        )
    }

    fun setCutCopyPasteHandlers(editorActionManager: EditorActionManager, logger: Logger) {
        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_COPY,
            TrackingCopyHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_COPY), logger)
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_PASTE,
            TrackingPasteHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_PASTE), logger)
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_CUT,
            TrackingCutHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_CUT), logger)
        )
    }

    fun setCaretMoveActions(actionManager: ActionManager, logger: Logger) {
        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_UP,
            TrackingMoveCaretUpAction(logger)
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN,
            TrackingMoveCaretDownAction(logger)
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT,
            TrackingMoveCaretLeftAction(logger)
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT,
            TrackingMoveCaretRightAction(logger)
        )
    }

    fun setTextSelectionHandlers(editorActionManager: EditorActionManager, logger: Logger) {
        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_SELECT_WORD_AT_CARET,
            TrackingSelectWordHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_SELECT_WORD_AT_CARET), logger)
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_SELECT_ALL,
            TrackingSelectAllHandler(editorActionManager.getActionHandler(IdeActions.ACTION_SELECT_ALL), logger)
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_MOVE_LINE_START_WITH_SELECTION,
            TrackingLineStartWithSelectionHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_MOVE_LINE_START_WITH_SELECTION), logger)
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_MOVE_LINE_END_WITH_SELECTION,
            TrackingLineEndWithSelectionHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_MOVE_LINE_END_WITH_SELECTION), logger)
        )
    }

    fun setTextSelectionActions(actionManager: ActionManager, logger: Logger) {
        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_UP_WITH_SELECTION,
            TrackingMoveCaretUpWithSelectionAction(logger)
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN_WITH_SELECTION,
            TrackingMoveCaretDownWithSelectionAction(logger)
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT_WITH_SELECTION,
            TrackingMoveCaretLeftWithSelectionAction(logger)
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT_WITH_SELECTION,
            TrackingMoveCaretRightWithSelectionAction(logger)
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_UP_WITH_SELECTION,
            TrackingPageUpWithSelectionAction(logger)
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_DOWN_WITH_SELECTION,
            TrackingPageDownWithSelectionAction(logger)
        )
    }
}