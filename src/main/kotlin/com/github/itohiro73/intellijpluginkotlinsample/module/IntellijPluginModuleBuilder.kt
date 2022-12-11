package com.github.itohiro73.intellijpluginkotlinsample.module

import com.intellij.ide.util.projectWizard.JavaModuleBuilder
import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.roots.ModifiableRootModel
import com.intellij.openapi.roots.ui.configuration.ModulesProvider

class IntellijPluginModuleBuilder : JavaModuleBuilder() {

    override fun getPresentableName() = IntellijPluginModuleType.NAME
    override fun getNodeIcon() = IntellijPluginModuleType.PLUGINS_ICON
    override fun getGroupName() = IntellijPluginModuleType.NAME
    override fun getWeight() = BUILD_SYSTEM_WEIGHT - 1
    override fun getBuilderId() = "PLUGINS_MODULE"

    override fun getDescription(): String = "Create a <b>IntelliJ Plugin</b>."

    override fun setupRootModel(modifiableRootModel: ModifiableRootModel) {

    }

    override fun createWizardSteps(
        wizardContext: WizardContext,
        modulesProvider: ModulesProvider
    ): Array<ModuleWizardStep> {
        return arrayOf(IntellijPluginArtifactWizardStep())
    }
}