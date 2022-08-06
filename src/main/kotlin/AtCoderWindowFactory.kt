package com.nisecoder.intellij.plugins.atcoder

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.jcef.JBCefBrowser

class AtCoderWindowFactory: ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val browser = JBCefBrowser("https://atcoder.jp")
        val content = ContentFactory.getInstance().createContent(browser.component, "AtCoder", false)

        toolWindow.contentManager.addContent(content)
    }
}
