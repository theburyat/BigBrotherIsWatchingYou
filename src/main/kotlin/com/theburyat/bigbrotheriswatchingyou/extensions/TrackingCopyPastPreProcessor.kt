package com.theburyat.bigbrotheriswatchingyou.extensions

import com.intellij.codeInsight.editorActions.CopyPastePreProcessor
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.RawText
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.theburyat.bigbrotheriswatchingyou.enums.AnalysisState
import com.theburyat.bigbrotheriswatchingyou.enums.UserAction
import com.theburyat.bigbrotheriswatchingyou.models.AnalysisProcess

class TrackingCopyPastPreProcessor : CopyPastePreProcessor {
    override fun preprocessOnCopy(
        file: PsiFile?,
        startOffsets: IntArray?,
        endOffsets: IntArray?,
        text: String?
    ): String? {
        if (AnalysisProcess.context.state == AnalysisState.RUNNING && !text.isNullOrBlank()) {
            AnalysisProcess.context.copyCache.add(text)
            AnalysisProcess.context.logger?.info(UserAction.Copy.toString())
        }
        return text
    }

    override fun preprocessOnPaste(
        project: Project?,
        file: PsiFile?,
        editor: Editor?,
        text: String?,
        rawText: RawText?
    ): String {
        if (AnalysisProcess.context.state == AnalysisState.RUNNING && !text.isNullOrBlank()) {
            if (AnalysisProcess.context.copyCache.contains(text))
                AnalysisProcess.context.logger?.info(UserAction.Paste.toString())
            else
                AnalysisProcess.context.logger?.info("${UserAction.PasteFromUnknownSource} with symbols count: ${text.length}")
        }
        return text!!
    }
}
