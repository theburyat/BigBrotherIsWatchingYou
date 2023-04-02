package com.theburyat.bigbrotheriswatchingyou.listeners

import com.intellij.codeInsight.lookup.Lookup
import com.intellij.codeInsight.lookup.LookupManagerListener
import com.theburyat.bigbrotheriswatchingyou.enums.UserAction
import java.util.logging.Logger

class TrackingLookUpManagerListener(logger: Logger): LookupManagerListener {
    private val _logger: Logger = logger

    override fun activeLookupChanged(oldLookup: Lookup?, newLookup: Lookup?) {
        if (oldLookup != null && (oldLookup.isSelectionTouched)) {
            _logger.info(UserAction.CompleteCode.toString())
        }
    }
}