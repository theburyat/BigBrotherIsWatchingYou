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
import com.theburyat.bigbrotheriswatchingyou.models.AnalysisContext
import java.util.logging.Logger

object IdeEventsUtils {

    fun setLoggerToUserActions(project: Project, actionManager: ActionManager, editorActionManager: EditorActionManager, logger: Logger) {
        saveOriginalHandlers(editorActionManager)
        saveOriginalActions(actionManager)

        subscribeOnMessagesFromBus(project, logger)
        setTrackingActions(actionManager, logger)
        setTrackingHandlers(editorActionManager, logger)
    }

    private fun subscribeOnMessagesFromBus(project: Project, logger: Logger) {
        AnalysisContext.messageBusConnection = project.messageBus.connect()
        AnalysisContext.messageBusConnection.subscribe(LookupManagerListener.TOPIC, TrackingLookUpManagerListener(logger))
        AnalysisContext.messageBusConnection.subscribe(ExecutionManager.EXECUTION_TOPIC, TrackingRunManagerListener(logger))
    }

    private fun setTrackingHandlers(editorActionManager: EditorActionManager, logger: Logger) {
        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_ENTER,
            TrackingEnterHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_ENTER), logger)
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_DELETE,
            TrackingDeleteHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_DELETE), logger)
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_BACKSPACE,
            TrackingBackspaceHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_BACKSPACE), logger)
        )

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

        val typedAction = editorActionManager.typedAction
        typedAction.setupHandler(TrackingTypedHandler(typedAction.handler, logger))
    }

    private fun setTrackingActions(actionManager: ActionManager, logger: Logger) {
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

    private fun saveOriginalHandlers(editorActionManager: EditorActionManager) {
        AnalysisContext.originalHandlers[IdeActions.ACTION_EDITOR_ENTER] =
            editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_ENTER)

        AnalysisContext.originalHandlers[IdeActions.ACTION_EDITOR_DELETE] =
            editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_DELETE)

        AnalysisContext.originalHandlers[IdeActions.ACTION_EDITOR_BACKSPACE] =
            editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_BACKSPACE)

        AnalysisContext.originalHandlers[IdeActions.ACTION_EDITOR_COPY] =
            editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_COPY)

        AnalysisContext.originalHandlers[IdeActions.ACTION_EDITOR_PASTE] =
            editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_PASTE)

        AnalysisContext.originalHandlers[IdeActions.ACTION_EDITOR_CUT] =
            editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_CUT)

        AnalysisContext.originalHandlers[IdeActions.ACTION_EDITOR_SELECT_WORD_AT_CARET] =
            editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_SELECT_WORD_AT_CARET)

        AnalysisContext.originalHandlers[IdeActions.ACTION_SELECT_ALL] =
            editorActionManager.getActionHandler(IdeActions.ACTION_SELECT_ALL)

        AnalysisContext.originalHandlers[IdeActions.ACTION_EDITOR_MOVE_LINE_START_WITH_SELECTION] =
            editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_MOVE_LINE_START_WITH_SELECTION)

        AnalysisContext.originalHandlers[IdeActions.ACTION_EDITOR_MOVE_LINE_END_WITH_SELECTION] =
            editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_MOVE_LINE_END_WITH_SELECTION)

        AnalysisContext.originalTypeHandler = editorActionManager.typedAction.handler
    }

    private fun saveOriginalActions(actionManager: ActionManager) {
        AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_UP] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_UP)

        AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN)

        AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT)

        AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT)

        AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_UP_WITH_SELECTION] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_UP_WITH_SELECTION)

        AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN_WITH_SELECTION] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN_WITH_SELECTION)

        AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT_WITH_SELECTION] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT_WITH_SELECTION)

        AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT_WITH_SELECTION] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT_WITH_SELECTION)

        AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_UP_WITH_SELECTION] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_UP_WITH_SELECTION)

        AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_DOWN_WITH_SELECTION] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_DOWN_WITH_SELECTION)
    }

    fun disableLoggerFromUserActions(actionManager: ActionManager, editorActionManager: EditorActionManager) {
        unsubscribeFromMessageFromBus()
        removeTrackingActions(actionManager)
        removeTrackingHandlers(editorActionManager)
    }

    private fun unsubscribeFromMessageFromBus() {
        AnalysisContext.messageBusConnection.disconnect()
    }

    private fun removeTrackingHandlers(editorActionManager: EditorActionManager) {
        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_ENTER,
            AnalysisContext.originalHandlers[IdeActions.ACTION_EDITOR_ENTER]!!
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_DELETE,
            AnalysisContext.originalHandlers[IdeActions.ACTION_EDITOR_DELETE]!!
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_BACKSPACE,
            AnalysisContext.originalHandlers[IdeActions.ACTION_EDITOR_BACKSPACE]!!
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_COPY,
            AnalysisContext.originalHandlers[IdeActions.ACTION_EDITOR_COPY]!!
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_PASTE,
            AnalysisContext.originalHandlers[IdeActions.ACTION_EDITOR_PASTE]!!
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_CUT,
            AnalysisContext.originalHandlers[IdeActions.ACTION_EDITOR_CUT]!!
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_SELECT_WORD_AT_CARET,
            AnalysisContext.originalHandlers[IdeActions.ACTION_EDITOR_SELECT_WORD_AT_CARET]!!
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_SELECT_ALL,
            AnalysisContext.originalHandlers[IdeActions.ACTION_SELECT_ALL]!!
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_MOVE_LINE_START_WITH_SELECTION,
            AnalysisContext.originalHandlers[IdeActions.ACTION_EDITOR_MOVE_LINE_START_WITH_SELECTION]!!
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_MOVE_LINE_END_WITH_SELECTION,
            AnalysisContext.originalHandlers[IdeActions.ACTION_EDITOR_MOVE_LINE_END_WITH_SELECTION]!!
        )

        editorActionManager.typedAction.setupHandler(AnalysisContext.originalTypeHandler)
    }

    private fun removeTrackingActions(actionManager: ActionManager) {
        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_UP_WITH_SELECTION,
            AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_UP_WITH_SELECTION]!!
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN_WITH_SELECTION,
            AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN_WITH_SELECTION]!!
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT_WITH_SELECTION,
            AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT_WITH_SELECTION]!!
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT_WITH_SELECTION,
            AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT_WITH_SELECTION]!!
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_UP_WITH_SELECTION,
            AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_UP_WITH_SELECTION]!!
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_DOWN_WITH_SELECTION,
            AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_DOWN_WITH_SELECTION]!!
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_UP,
            AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_UP]!!
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN,
            AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN]!!
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT,
            AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT]!!
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT,
            AnalysisContext.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT]!!
        )
    }
}