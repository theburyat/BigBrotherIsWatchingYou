package com.theburyat.bigbrotheriswatchingyou.models

import com.theburyat.bigbrotheriswatchingyou.enums.AnalysisState
import java.nio.file.Path
import java.util.logging.Logger

class AnalysisContext(
    var logger: Logger?,
    var logFile: Path?,
    var state: AnalysisState
) {
    constructor() : this(null, null, AnalysisState.DISABLED)

    fun reset() {
        logger = null
        logFile = null
        state = AnalysisState.DISABLED
    }

    fun isEmpty() = logger == null && logFile == null && state == AnalysisState.DISABLED
}