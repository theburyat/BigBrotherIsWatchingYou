package com.theburyat.bigbrotheriswatchingyou.models

data class StudentInfo(
    var name: String?,
    var group: String?,
    var serverUrl: String?,
) {
    fun reset() {
        name = null
        group = null
        serverUrl = null
    }

    fun isEmpty(): Boolean {
        return name == null && group == null && serverUrl == null
    }
}