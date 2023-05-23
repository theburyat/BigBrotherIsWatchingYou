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

object IdeEventsUtils {

    fun addActionsLogging(project: Project, actionManager: ActionManager, editorActionManager: EditorActionManager) {
        saveOriginalHandlers(editorActionManager)
        saveOriginalActions(actionManager)

        subscribeOnMessagesFromBus(project)
        setTrackingActions(actionManager)
        setTrackingHandlers(editorActionManager)
    }

    private fun subscribeOnMessagesFromBus(project: Project) {
        AnalysisProcess.messageBusConnection = project.messageBus.connect()
        AnalysisProcess.messageBusConnection.subscribe(LookupManagerListener.TOPIC, TrackingLookUpManagerListener())
        AnalysisProcess.messageBusConnection.subscribe(ExecutionManager.EXECUTION_TOPIC, TrackingRunManagerListener())
    }

    private fun setTrackingHandlers(editorActionManager: EditorActionManager) {
        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_ENTER,
            TrackingEnterHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_ENTER))
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_DELETE,
            TrackingDeleteHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_DELETE))
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_BACKSPACE,
            TrackingBackspaceHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_BACKSPACE))
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_CUT,
            TrackingCutHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_CUT))
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_SELECT_WORD_AT_CARET,
            TrackingSelectWordHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_SELECT_WORD_AT_CARET))
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_SELECT_ALL,
            TrackingSelectAllHandler(editorActionManager.getActionHandler(IdeActions.ACTION_SELECT_ALL))
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_MOVE_LINE_START_WITH_SELECTION,
            TrackingLineStartWithSelectionHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_MOVE_LINE_START_WITH_SELECTION))
        )

        editorActionManager.setActionHandler(
            IdeActions.ACTION_EDITOR_MOVE_LINE_END_WITH_SELECTION,
            TrackingLineEndWithSelectionHandler(editorActionManager.getActionHandler(IdeActions.ACTION_EDITOR_MOVE_LINE_END_WITH_SELECTION))
        )

        val typedAction = editorActionManager.typedAction
        typedAction.setupHandler(TrackingTypedHandler(typedAction.handler))
    }

    private fun setTrackingActions(actionManager: ActionManager) {
        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_UP_WITH_SELECTION,
            TrackingMoveCaretUpWithSelectionAction()
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN_WITH_SELECTION,
            TrackingMoveCaretDownWithSelectionAction()
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT_WITH_SELECTION,
            TrackingMoveCaretLeftWithSelectionAction()
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT_WITH_SELECTION,
            TrackingMoveCaretRightWithSelectionAction()
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_UP_WITH_SELECTION,
            TrackingPageUpWithSelectionAction()
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_PAGE_DOWN_WITH_SELECTION,
            TrackingPageDownWithSelectionAction()
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_UP,
            TrackingMoveCaretUpAction()
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN,
            TrackingMoveCaretDownAction()
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT,
            TrackingMoveCaretLeftAction()
        )

        actionManager.replaceAction(
            IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT,
            TrackingMoveCaretRightAction()
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