package com.nisecoder.intellij.plugins.atcoder

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.jcef.JBCefBrowser
import javax.swing.JComponent

class AtCoderWindowFactory: ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val service = project.service<AtCoderWindowService>()
        toolWindow.component.parent.add(service.window.content)
    }
}

class AtCoderWindowService(project: Project) {
    val window: AtCoderWindow = AtCoderWindow(project)
}

class AtCoderWindow(project: Project) {
    val content: JComponent = JBCefBrowser().apply {
        loadURL("https://atcoder.jp")
        Disposer.register(project, this)
    }.component
}
