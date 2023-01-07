package com.theburyat.bigbrotheriswatchingyou.models

data class StudentInfo(
    var name: String?,
    var group: String?,
    var email: String?,
) {
    constructor() : this(null, null, null)

    fun clear() {
        name = null
        group = null
        email = null
    }

    fun isEmpty(): Boolean {
        return name == null && group == null && email == null
    }
}