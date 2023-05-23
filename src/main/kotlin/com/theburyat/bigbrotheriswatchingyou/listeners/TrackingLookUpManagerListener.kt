package com.theburyat.bigbrotheriswatchingyou.listeners

import com.intellij.codeInsight.lookup.Lookup
import com.intellij.codeInsight.lookup.LookupManagerListener
import com.theburyat.bigbrotheriswatchingyou.enums.UserAction
import com.theburyat.bigbrotheriswatchingyou.models.AnalysisProcess

class TrackingLookUpManagerListener: LookupManagerListener {
    override fun activeLookupChanged(oldLookup: Lookup?, newLookup: Lookup?) {
        if (oldLookup != null && (oldLookup.isSelectionTouched)) {
            AnalysisProcess.context.logger?.info(UserAction.CompleteCode.toString())
        }
    }
}