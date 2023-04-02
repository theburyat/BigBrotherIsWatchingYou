@file:Suppress("DEPRECATION")

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
import com.theburyat.bigbrotheriswatchingyou.models.AnalysisProcess
import java.util.logging.Logger

object IdeEventsUtils {

    fun addActionsLogging(project: Project, actionManager: ActionManager, editorActionManager: EditorActionManager, logger: Logger) {
        saveOriginalHandlers(editorActionManager)
        saveOriginalActions(actionManager)

        subscribeOnMessagesFromBus(project, logger)
        setTrackingActions(actionManager, logger)
        setTrackingHandlers(editorActionManager, logger)
    }

    private fun subscribeOnMessagesFromBus(project: Project, logger: Logger) {
        AnalysisProcess.messageBusConnection = project.messageBus.connect()
        AnalysisProcess.messageBusConnection.subscribe(LookupManagerListener.TOPIC, TrackingLookUpManagerListener(logger))
        AnalysisProcess.messageBusConnection.subscribe(ExecutionManager.EXECUTION_TOPIC, TrackingRunManagerListener(logger))
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
        AnalysisProcess.originalHandlers[IdeActions.ACTION_EDITOR_ENTER] =
            editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_ENTER)

        AnalysisProcess.originalHandlers[IdeActions.ACTION_EDITOR_DELETE] =
            editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_DELETE)

        AnalysisProcess.originalHandlers[IdeActions.ACTION_EDITOR_BACKSPACE] =
            editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_BACKSPACE)

        AnalysisProcess.originalHandlers[IdeActions.ACTION_EDITOR_COPY] =
            editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_COPY)

        AnalysisProcess.originalHandlers[IdeActions.ACTION_EDITOR_PASTE] =
            editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_PASTE)

        AnalysisProcess.originalHandlers[IdeActions.ACTION_EDITOR_CUT] =
            editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_CUT)

        AnalysisProcess.originalHandlers[IdeActions.ACTION_EDITOR_SELECT_WORD_AT_CARET] =
            editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_SELECT_WORD_AT_CARET)

        AnalysisProcess.originalHandlers[IdeActions.ACTION_SELECT_ALL] =
            editorActionManager.getActionHandler(IdeActions.ACTION_SELECT_ALL)

        AnalysisProcess.originalHandlers[IdeActions.ACTION_EDITOR_MOVE_LINE_START_WITH_SELECTION] =
            editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_MOVE_LINE_START_WITH_SELECTION)

        AnalysisProcess.originalHandlers[IdeActions.ACTION_EDITOR_MOVE_LINE_END_WITH_SELECTION] =
            editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_MOVE_LINE_END_WITH_SELECTION)

        AnalysisProcess.originalTypeHandler = editorActionManager.typedAction.handler
    }

    private fun saveOriginalActions(actionManager: ActionManager) {
        AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_UP] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_UP)

        AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN)

        AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT)

        AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT)

        AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_UP_WITH_SELECTION] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_UP_WITH_SELECTION)

        AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN_WITH_SELECTION] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN_WITH_SELECTION)

        AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT_WITH_SELECTION] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT_WITH_SELECTION)

        AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT_WITH_SELECTION] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT_WITH_SELECTION)

        AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_UP_WITH_SELECTION] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_UP_WITH_SELECTION)

        AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_DOWN_WITH_SELECTION] =
            actionManager.getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_DOWN_WITH_SELECTION)
    }

    fun disableActionsLogging(actionManager: ActionManager, editorActionManager: EditorActionManager) {
        unsubscribeFromMessageFromBus()
        removeTrackingActions(actionManager)
        removeTrackingHandlers(editorActionManager)
    }

    private fun unsubscribeFromMessageFromBus() {
        AnalysisProcess.messageBusConnection.disconnect()
    }

    private fun removeTrackingHandlers(editorActionManager: EditorActionManager) {
        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_ENTER,
            AnalysisProcess.originalHandlers[IdeActions.ACTION_EDITOR_ENTER]!!
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_DELETE,
            AnalysisProcess.originalHandlers[IdeActions.ACTION_EDITOR_DELETE]!!
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_BACKSPACE,
            AnalysisProcess.originalHandlers[IdeActions.ACTION_EDITOR_BACKSPACE]!!
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_COPY,
            AnalysisProcess.originalHandlers[IdeActions.ACTION_EDITOR_COPY]!!
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_PASTE,
            AnalysisProcess.originalHandlers[IdeActions.ACTION_EDITOR_PASTE]!!
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_CUT,
            AnalysisProcess.originalHandlers[IdeActions.ACTION_EDITOR_CUT]!!
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_SELECT_WORD_AT_CARET,
            AnalysisProcess.originalHandlers[IdeActions.ACTION_EDITOR_SELECT_WORD_AT_CARET]!!
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_SELECT_ALL,
            AnalysisProcess.originalHandlers[IdeActions.ACTION_SELECT_ALL]!!
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_MOVE_LINE_START_WITH_SELECTION,
            AnalysisProcess.originalHandlers[IdeActions.ACTION_EDITOR_MOVE_LINE_START_WITH_SELECTION]!!
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_MOVE_LINE_END_WITH_SELECTION,
            AnalysisProcess.originalHandlers[IdeActions.ACTION_EDITOR_MOVE_LINE_END_WITH_SELECTION]!!
        )

        editorActionManager.typedAction.setupHandler(AnalysisProcess.originalTypeHandler)
    }

    private fun removeTrackingActions(actionManager: ActionManager) {
        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_UP_WITH_SELECTION,
            AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_UP_WITH_SELECTION]!!
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN_WITH_SELECTION,
            AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN_WITH_SELECTION]!!
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT_WITH_SELECTION,
            AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT_WITH_SELECTION]!!
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT_WITH_SELECTION,
            AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT_WITH_SELECTION]!!
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_UP_WITH_SELECTION,
            AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_UP_WITH_SELECTION]!!
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_DOWN_WITH_SELECTION,
            AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_DOWN_WITH_SELECTION]!!
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_UP,
            AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_UP]!!
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN,
            AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN]!!
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT,
            AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT]!!
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT,
            AnalysisProcess.originalActions[IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT]!!
        )
    }
}