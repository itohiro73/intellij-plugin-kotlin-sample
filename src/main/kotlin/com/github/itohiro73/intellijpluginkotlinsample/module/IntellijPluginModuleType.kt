package com.github.itohiro73.intellijpluginkotlinsample.module

import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

class IntellijPluginModuleType : ModuleType<IntellijPluginModuleBuilder>("intellijPluginWizard") {
    companion object {
        val NAME = "IntelliJ Plugin"
        val DESCRIPTION = "IntelliJ Plugin Quickstart"
        val PLUGINS_BIG_ICON by lazy { IconLoader.findIcon("/icons/plugins.png") }
        val PLUGINS_ICON by lazy { IconLoader.findIcon("/icons/plugins-16.png") }
        val INSTANCE = IntellijPluginModuleType()
    }

    override fun createModuleBuilder(): IntellijPluginModuleBuilder = IntellijPluginModuleBuilder()
    override fun getName(): String = NAME
    override fun getDescription(): String = DESCRIPTION
    override fun getNodeIcon(isOpened: Boolean): Icon = PLUGINS_ICON!!
    override fun getIcon(): Icon = PLUGINS_BIG_ICON!!
}
