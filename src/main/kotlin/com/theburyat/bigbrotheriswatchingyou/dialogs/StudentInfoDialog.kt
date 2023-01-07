package com.theburyat.bigbrotheriswatchingyou.dialogs

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.dsl.builder.Cell
import com.intellij.ui.dsl.builder.TopGap
import com.intellij.ui.dsl.builder.panel
import com.theburyat.bigbrotheriswatchingyou.MessageConstants
import com.theburyat.bigbrotheriswatchingyou.models.AnalysisContext
import javax.swing.JComponent
import javax.swing.JTextField

class StudentInfoDialog(project: Project): DialogWrapper(project, true) {

    var studentName: Cell<JTextField>? = null
    var studentGroup: Cell<JTextField>? = null
    var studentEmail: Cell<JTextField>? = null

    init {
        title = MessageConstants.studentInfoDialogTitle
        init()
    }

    override fun createCenterPanel(): JComponent {
        return panel {
            row(MessageConstants.studentInfoDialogNameRaw) { studentName = textField() }.topGap(TopGap.SMALL)
            row(MessageConstants.studentInfoDialogGroupRaw) { studentGroup = textField() }.topGap(TopGap.SMALL)
            row(MessageConstants.studentInfoDialogEmailRaw) { studentEmail = textField() }.topGap(TopGap.SMALL)
        }
    }

    fun isFilled(): Boolean {
        return !studentName!!.component.text.isNullOrBlank()
                && !studentGroup!!.component.text.isNullOrBlank()
                && !studentEmail!!.component.text.isNullOrBlank()
    }

    fun fillContextStudentInfo() {
        AnalysisContext.studentInfo.name = studentName!!.component.text
        AnalysisContext.studentInfo.group = studentGroup!!.component.text
        AnalysisContext.studentInfo.email = studentEmail!!.component.text
    }
}