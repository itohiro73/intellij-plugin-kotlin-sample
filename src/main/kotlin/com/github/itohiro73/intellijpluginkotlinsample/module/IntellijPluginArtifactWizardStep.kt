package com.github.itohiro73.intellijpluginkotlinsample.module

import com.intellij.ide.util.projectWizard.ModuleWizardStep
import javax.swing.JComponent
import javax.swing.JLabel

class IntellijPluginArtifactWizardStep: ModuleWizardStep() {
    override fun getComponent(): JComponent {
        return JLabel("Put your content here")
    }

    override fun updateDataModel() {
        TODO("Not yet implemented")
    }
}