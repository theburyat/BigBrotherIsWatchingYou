package com.theburyat.bigbrotheriswatchingyou.Listeners

import com.intellij.codeInsight.lookup.Lookup
import com.intellij.codeInsight.lookup.LookupManagerListener
import com.intellij.openapi.project.Project

class TrackingLookUpManagerListener(project: Project): LookupManagerListener {

    private val _project: Project = project

    override fun activeLookupChanged(oldLookup: Lookup?, newLookup: Lookup?) {
        if (oldLookup != null && (oldLookup.isSelectionTouched)) {
            println("Code was autocompleted")
        }
    }
}