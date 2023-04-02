package com.theburyat.bigbrotheriswatchingyou.dialogs

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.dsl.builder.Cell
import com.intellij.ui.dsl.builder.TopGap
import com.intellij.ui.dsl.builder.panel
import com.theburyat.bigbrotheriswatchingyou.MessageConstants
import com.theburyat.bigbrotheriswatchingyou.models.StudentInfo
import java.net.URI
import javax.swing.JTextField

class StudentInfoDialog(project: Project): DialogWrapper(project, true) {

    private lateinit var nameCell: Cell<JTextField>
    private lateinit var groupCell: Cell<JTextField>
    private lateinit var serverUrlCell: Cell<JTextField>

    init {
        title = MessageConstants.DIALOG_TITLE
        init()
    }

    override fun createCenterPanel() = panel {
        row(MessageConstants.DIALOG_NAME_ROW) { nameCell = textField() }.topGap(TopGap.SMALL)
        row(MessageConstants.DIALOG_GROUP_ROW) { groupCell = textField() }.topGap(TopGap.SMALL)
        row(MessageConstants.DIALOG_SERVER_URI_ROW) { serverUrlCell = textField() }.topGap(TopGap.SMALL)
    }


    fun isNameValid(): Boolean {
        val name = nameCell.component.text
        return name.isNotBlank() && name.matches(Regex("[A-Za-zА-Яа-я\\s]+"))
    }

    fun isGroupValid(): Boolean {
        val group = groupCell.component.text
        return group.isNotBlank() && group.matches(Regex("[A-Za-zА-Яа-я-/]+"))
    }

    fun isServerUrlValid(): Boolean {
        try {
            val url = URI(serverUrlCell.component.text).toURL()
            if (url.protocol == "http" || url.protocol == "https") {
                return true
            }
            return false
        } catch (e: Exception) {
            return false
        }
    }

    fun getStudentInfo(): StudentInfo = StudentInfo(
        nameCell.component.text,
        groupCell.component.text,
        serverUrlCell.component.text
    )
}
