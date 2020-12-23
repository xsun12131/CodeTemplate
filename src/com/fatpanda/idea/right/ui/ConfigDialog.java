package com.fatpanda.idea.right.ui;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.wizard.AbstractWizard;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiClass;
import org.jetbrains.annotations.Nullable;

public class ConfigDialog extends AbstractWizard<ModuleWizardStep> {

    private Project myProject;
    private PsiClass myClass;

    public ConfigDialog(Project project, PsiClass clazz) {
        super("Create code Template", project);
        myProject = project;
        myClass = clazz;
        ModuleWizardStep[] wizardSteps = createWizardSteps();
        for (ModuleWizardStep wizardStep : wizardSteps) {
            addStep(wizardStep);
        }
        init();
    }

    public ModuleWizardStep[] createWizardSteps() {
        return new ModuleWizardStep[]{
                new ResultDialog(myProject,myClass)
        };
    }

    /**
     * 点击Ok,之后去生成对应的扩展点
     */
    @Override
    protected void doOKAction() {
        ModuleWizardStep step = getCurrentStepObject();
        try {
            if (step.validate()) {
                super.doOKAction();
            }
        } catch (ConfigurationException e) {
            Messages.showErrorDialog(step.getComponent(), e.getMessage(), e.getTitle());
        }
    }

    @Override
    protected @Nullable String getHelpID() {
        return ConfigDialog.class.getName();
    }
}
